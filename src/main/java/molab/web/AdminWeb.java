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

import molab.db.entity.TAdmin;
import molab.db.entity.TAgent;
import molab.db.entity.TApply;
import molab.db.entity.TDevice;
import molab.service.AdminService;
import molab.service.AgentService;
import molab.service.ApplyService;
import molab.service.DeviceService;
import molab.service.OwnerService;
import molab.util.Status;

@Controller
@RequestMapping(value = "/admin")
public class AdminWeb {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String PREFIX = "admin"; 

	@Autowired
	private AdminService service;
	
	@Autowired
	private AgentService as;
	
	@Autowired
	private ApplyService aps;
	
	@Autowired
	private OwnerService os;
	
	@Autowired
	private DeviceService ds;
	
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
//		log.info("Admin(username=" + username + "&password=" + password + ") is signin.");
		TAdmin admin = service.signin(username, password);
		if(admin != null) {
			session.setAttribute(PREFIX, admin);
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
	
	@RequestMapping(value = "/agent")
	public String agent(Model model) {
		model.addAttribute("acList", as.findAll());
		return PREFIX + "/agent/home";
	}
	
	@RequestMapping(value = "/agent/add")
	public String agentAdd(Model model) {
		return PREFIX + "/agent/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/addAction", method = RequestMethod.POST)
	public String agentAddAction(Model model, HttpSession session,
			@RequestParam String name, 
			@RequestParam String username, @RequestParam String password, 
			@RequestParam String contact, @RequestParam String phone, 
			@RequestParam String expireTime, @RequestParam Integer ownerAllowed,
			@RequestParam Float settlementRatio) {
		TAdmin admin = (TAdmin) session.getAttribute(PREFIX);
		TAgent agent = as.add(admin, name, username, password, contact, phone, 
				expireTime, ownerAllowed, settlementRatio);
		if(agent != null && agent.getId() > 0) {
			log.info("Agent(name=" + name + ",username=" + username + ") saved successfully.");
			return String.valueOf(agent.getId());
		} else {
			log.warn("Agent(username=" + username + ") exist already.");
		}
		return String.valueOf(Status.Common.FAILURE.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/allowAction/{id}")
	public String allow(@PathVariable Integer id) {
		as.update(id, Status.Common.OPEN.getInt());
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/agent/forbidAction/{id}")
	public String forbid(@PathVariable Integer id) {
		as.update(id, Status.Common.CLOSE.getInt());
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/owner")
	public String owner(Model model) {
		model.addAttribute("ocList", os.findAll());
		return PREFIX + "/owner/home";
	}
	
	@RequestMapping(value = "/device")
	public String device(Model model) {
		model.addAttribute("dcList", ds.findByLargeThanAndEqualToStatusForAdmin())
			.addAttribute("acList", as.findAll());
		return PREFIX + "/device/home";
	}
	
	@RequestMapping(value = "/device/add")
	public String deviceAdd(Model model) {
		return PREFIX + "/device/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/addAction", method = RequestMethod.POST)
	public String deviceAddAction(Model model,
			@RequestParam(required = false) Integer applyId,
			@RequestParam(required = false) Integer deviceId, 
			@RequestParam(required = false) String uuid,
			@RequestParam Integer major, 
			@RequestParam Integer minor,
			@RequestParam(required = false) String comment, 
			@RequestParam(required = false) Integer poiId) {
		TDevice device = ds.add(applyId, deviceId, uuid, major, minor, comment);
		if(device != null && device.getId() > 0) {
			log.info("Device(major=" + major + ",minor=" + minor + ") saved successfully.");
			return String.valueOf(device.getId());
		} else {
			log.warn("Device(major=" + major + ",minor=" + minor + ") exist already.");
		}
		return String.valueOf(Status.Common.FAILURE.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/confAction/{id}")
	public String deviceConfAction(Model model, @PathVariable Integer id) {
		ds.conf(id);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/confAction", method = RequestMethod.POST)
	public String deviceBatchConfAction(Model model, @RequestParam String ids) {
		ds.batchConf(ids);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/assignAction", method = RequestMethod.POST)
	public String deviceAssignAction(Model model, 
			@RequestParam Integer deviceId, @RequestParam Integer agentId) {
		ds.assignAgent(deviceId, agentId);
		return String.valueOf(Status.Common.SUCCESS.getInt());
	}
	
	@RequestMapping(value = "/device/apply")
	public String deviceApply(Model model) {
		return PREFIX + "/device/apply";
	}
	
	@ResponseBody
	@RequestMapping(value = "/device/applyAction", method = RequestMethod.POST)
	public String deviceApplyAction(Model model, @RequestParam Integer quantity, 
			@RequestParam String reason, @RequestParam String comment) {
		TApply apply = aps.add(quantity, reason, comment);
		if(apply != null && apply.getId() > 0) {
			log.info("Apply(reason=" + reason + ",quantity=" + quantity + ") saved successfully.");
			return String.valueOf(apply.getId());
		}
		return String.valueOf(Status.Common.FAILURE.getInt());
	}
	
	@RequestMapping(value = "/device/history")
	public String deviceHistory(Model model) {
		model.addAttribute("acList", aps.findAll());
		return PREFIX + "/device/history";
	}
		
}
