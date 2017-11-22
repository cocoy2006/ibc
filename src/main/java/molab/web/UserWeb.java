package molab.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import molab.db.entity.TPacket;
import molab.service.IndexService;
import molab.service.PacketService;
import weixin.popular.bean.shakearound.user.getshakeinfo.UserGetShakeInfoResultData;

@Controller
public class UserWeb {

	private final String PREFIX = "user";
	
	@Autowired
	private IndexService is;
	
	@Autowired
	private PacketService ps;
	
	@RequestMapping(value = "/")
	public String root() {
		return "index";
	}
	
	@RequestMapping(value = "/hello")
	public String hello(Model model, HttpServletRequest request) {
		String ticket = request.getParameter("ticket");
		UserGetShakeInfoResultData data = is.shake(ticket);
		TPacket packet = ps.findOne(data);
		if(packet == null) {
			return "user/done";
		}
		String params = String.format("openid=%s&packetId=%d&major=%d&minor=%d", 
				data.getOpenId(), packet.getId(), data.getBeaconInfo().getMajor(), data.getBeaconInfo().getMinor());
		model.addAttribute("packet", packet)
				.addAttribute("params", params);
		return PREFIX + "/hello";
	}
	
	@RequestMapping(value = "/packet")
	public String packet(Model model, 
			@Param(value="openid") String openid, @Param(value="packetId") int packetId, 
			@Param(value="major") int major, @Param(value="minor") int minor) {
		double amount = ps.open(openid, packetId, major, minor);
		model.addAttribute("amount", amount);
		return PREFIX + "/packet";
	}
	
	@RequestMapping(value = "/my/homepage")
	public String homepage(Model model) {
		return PREFIX + "/my/homepage";
	}
	
}
