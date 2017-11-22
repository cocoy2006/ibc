package molab.web;

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

import com.alibaba.fastjson.JSON;

import molab.db.entity.TAgent;
import molab.db.entity.TOwner;
import molab.service.AgentService;
import molab.service.DeviceService;
import molab.service.IndustryService;
import molab.service.OwnerService;
import molab.util.Status;

@Controller
@RequestMapping(value = "/agent")
public class AgentWeb {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String PREFIX = "agent"; 

	@Autowired
	private AgentService as;
	
	@Autowired
	private OwnerService os;
	
	@Autowired
	private DeviceService ds;
	
	@Autowired
	private IndustryService is;
	
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
//		log.info("Agent(username=" + username + "&password=" + password + ") is signin.");
		TAgent agent = as.signin(username, password);
		if(agent != null) {
			session.setAttribute(PREFIX, agent);
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
	
	@RequestMapping(value = "/owner")
	public String owner(Model model, HttpSession session) {
		TAgent agent = (TAgent) session.getAttribute(PREFIX);
		model.addAttribute("ocList", os.findAll(agent.getId()));
		return PREFIX + "/owner/home";
	}
	
	@RequestMapping(value = "/owner/add")
	public String ownerAdd(Model model) {
		model.addAttribute("industryList", is.findAll());
		return PREFIX + "/owner/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/owner/addAction", method = RequestMethod.POST)
	public String ownerAddAction(Model model, @RequestParam String name, 
			@RequestParam String username, @RequestParam String password, 
			@RequestParam String contact, @RequestParam String phone, 
			@RequestParam String expireTime, @RequestParam Integer subIndustryId) {
		Integer agentId = 1;
		// TODO use real agentId instead when session is ready
		TOwner owner = os.add(agentId, subIndustryId, name, username, password, contact, phone, expireTime);
		if(owner != null && owner.getId() > 0) {
			log.info("Owner(name=" + name + ",username=" + username + ") saved successfully.");
			return String.valueOf(owner.getId());
		} else {
			log.warn("Owner(username=" + username + ") exist already.");
		}
		return String.valueOf(Status.Common.FAILURE.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/owner/allowAction/{id}")
	public String allow(@PathVariable Integer id) {
		os.update(id, Status.Common.OPEN.getInt());
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/owner/forbidAction/{id}")
	public String forbid(@PathVariable Integer id) {
		os.update(id, Status.Common.CLOSE.getInt());
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/device")
	public String device(Model model, HttpSession session) {
		TAgent agent = (TAgent) session.getAttribute(PREFIX);
		model.addAttribute("dcList", ds.findByLargeThanAndEqualToStatusForAgent(agent.getId()))
			.addAttribute("ocList", os.findAll(agent.getId()));
		return PREFIX + "/device/home";
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/assignAction", method = RequestMethod.POST)
	public String deviceAssignAction(Model model, 
			@RequestParam Integer deviceId, @RequestParam Integer ownerId) {
		ds.assignOwner(deviceId, ownerId);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
		
}
