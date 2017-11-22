package molab.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import molab.db.entity.TOwner;
import molab.properties.WebProperties;
import molab.service.BindPacketDeviceService;
import molab.service.DeviceService;
import molab.service.OwnerService;
import molab.service.PacketService;
import molab.service.RechargeService;
import molab.util.Status;

@Controller
@RequestMapping(value = "/owner")
public class OwnerWeb {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String PREFIX = "owner"; 
	
	@Autowired
	private DeviceService ds;
	
	@Autowired
	private OwnerService os;
	
	@Autowired
	private PacketService ps;
	
	@Autowired
	private RechargeService rs;
	
	@Autowired
	private BindPacketDeviceService bpds;
	
	@Autowired
	private WebProperties wp;
	
	@RequestMapping(value = "")
	public String _(Model model) {
		return PREFIX + "/index";
	}
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		return PREFIX + "/index";
	}

	@RequestMapping(value = "/signin")
	public String signin(Model model) {
		return PREFIX + "/signin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/signinAction", method = RequestMethod.POST)
	public String signinAction(Model model, HttpSession session,
			@RequestParam String username, @RequestParam String password) {
//		log.info("Owner(username=" + username + "&password=" + password + ") is signin.");
		TOwner owner = os.signin(username, password);
		if(owner != null) {
			session.setAttribute(PREFIX, owner);
			return JSON.toJSONString(Status.Common.SUCCESS.getInt());
		}
		return JSON.toJSONString(Status.Common.FAILURE.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/signoutAction")
	public String signoutAction(HttpSession session) {
		Object obj = session.getAttribute(PREFIX);
		if(obj != null) {
			session.removeAttribute(PREFIX);
		}
		return JSON.toJSONString(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/device")
	public String device(Model model, HttpSession session) {
		TOwner owner = (TOwner) session.getAttribute(PREFIX);
		model.addAttribute("dcList", ds.findByLargeThanAndEqualToStatusForOwner(owner.getId()))
			.addAttribute("packetList", ps.findByStatus(Status.PacketState.UNBOUND.getInt()));
		return PREFIX + "/device/home";
	}
	
	@RequestMapping(value = "/packet")
	public String packet(Model model, HttpSession session) {
		TOwner owner = (TOwner) session.getAttribute(PREFIX);
		model.addAttribute("packetList", ps.findByOwnerId(owner.getId()))
			.addAttribute("deviceList", ds.findByOwnerIdAndStatus(owner.getId(), Status.DeviceState.UNBOUND.getInt()));
		return PREFIX + "/packet/home";
	}
	
	@RequestMapping(value = "/packet/add")
	public String packetAdd(Model model) {
		return PREFIX + "/packet/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/packet/addAction", method = RequestMethod.POST)
	public String packetAddAction(Model model, HttpSession session, 
			@RequestParam String title, @RequestParam String description,
			@RequestParam Float amountTotal, @RequestParam Integer numTotal,
			@RequestParam Integer type, 
			@RequestParam String startTime, @RequestParam String endTime, 
			MultipartHttpServletRequest request,
			@RequestParam("pic") MultipartFile file,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {
		
		if (!file.isEmpty()) {  
            try {  
                Files.copy(file.getInputStream(), Paths.get(wp.getUploadPath(), file.getOriginalFilename()));  
                redirectAttributes.addFlashAttribute("message",  
                        "You successfully uploaded " + file.getOriginalFilename() + "!");  
            } catch (IOException|RuntimeException e) {  
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());  
            }  
        } else {  
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");  
        }
		
		
//		TOwner owner = (TOwner) session.getAttribute(PREFIX);
//		MultipartFile pic = request.getFile("pic");
//		TPacket packet = ps.add(owner, title, description, pic, amountTotal, numTotal, type, startTime, endTime);
//		if(packet != null && packet.getId() > 0) {
//			log.info("Packet(title=" + title + ",description=" + description + ") saved successfully.");
//			return String.valueOf(packet.getId());
//		}
		return String.valueOf(Status.Common.FAILURE.getInt());
	}
	
	@RequestMapping(value = "/packet/{id}")
	public String packetDetail(Model model, @PathVariable Integer id) {
		model.addAttribute("packet", ps.findOne(id))
			.addAttribute("bupcList", ps.detail(id));
		return PREFIX + "/packet/detail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	public String bind(Model model, @RequestParam Integer packetId, 
			@RequestParam String deviceIds) {
		bpds.bind(packetId, deviceIds);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/unbound", method = RequestMethod.POST)
	public String unbound(Model model, @RequestParam Integer deviceId) {
		bpds.unbound(deviceId);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/recharge")
	public String recharge(Model model, HttpSession session) {
		model.addAttribute("fee", os.rechargeFee());
		return PREFIX + "/recharge/recharge";
	}
	
	@ResponseBody
	@RequestMapping(value = "/rechargeAction", method = RequestMethod.POST)
	public String rechargeAction(Model model, HttpSession session,
			@RequestParam Float amountRecharge, @RequestParam Float amountPayment) {
		TOwner owner = (TOwner) session.getAttribute(PREFIX);
		os.recharge(owner, amountRecharge, amountPayment);
		session.setAttribute(PREFIX, owner);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/recharge/history")
	public String rechargeHistory(Model model, HttpSession session) {
		TOwner owner = (TOwner) session.getAttribute(PREFIX);
		model.addAttribute("rcList", rs.findAll(owner.getId()));
		return PREFIX + "/recharge/history";
	}
	
}
