package molab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.component.AgentComponent;
import molab.db.dao.AgentDao;
import molab.db.dao.BindDeviceAgentDao;
import molab.db.dao.ConfigDao;
import molab.db.entity.TAdmin;
import molab.db.entity.TAgent;
import molab.db.entity.TConfig;
import molab.util.Constant;
import molab.util.Molab;
import molab.util.Status;

@Service
public class AgentService {
	
	@Autowired
	private AgentDao ad;
	
	@Autowired
	private BindDeviceAgentDao bdad;
	
	@Autowired
	private ConfigDao cd;
	
	public TAgent signin(String username, String password) {
		return ad.findByUsernameAndPasswordAndStatus(username, password, Status.Common.YES.getInt());
	}
	
	public List<AgentComponent> findAll() {
		List<TAgent> agentList = ad.findAll();
		List<AgentComponent> acList = new ArrayList<AgentComponent>();
		for(TAgent agent : agentList) {
			AgentComponent ac = new AgentComponent();
			ac.setAgent(agent);
			ac.setCreateTime(Molab.parseDate(agent.getCreateTime()));
			ac.setExpireTime(Molab.parseDate(agent.getExpireTime()));
			ac.setDeviceList(bdad.findByAgentId(agent.getId()));
			acList.add(ac);
		}
		return acList;
	}
	
	public TAgent add(TAdmin admin, String name, String username, String password, 
			String contact, String phone, String expireTime, Integer ownerAllowed, 
			Float settlementRatio) {
		TAgent agent = ad.findByUsername(username);
		if(agent != null) {
			return null;
		}
		agent = new TAgent();
		agent.setAdminId(admin.getId());
		agent.setName(name);
		agent.setUsername(username);
		agent.setPassword(password);
		agent.setContact(contact);
		agent.setPhone(phone);
		agent.setAmountTotal(0f);
		agent.setAmountFreezed(0f);
		agent.setAmountOut(0f);
		agent.setAmountIn(0f);
		agent.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
		agent.setExpireTime(Molab.parseDate(expireTime));
		if(ownerAllowed == null) {
			ownerAllowed = 999;
		}
		agent.setOwnerAllowed(ownerAllowed);
		agent.setOwnerCount(0);
		if(settlementRatio == null) {
			settlementRatio = settlementRatio();
		}
		agent.setSettlementRatio(settlementRatio);
		agent.setStatus(Status.Common.CLOSE.getInt());
		return ad.save(agent);
	}
	
	public void update(Integer id, Integer status) {
		ad.update(id, status);
	}
	
	private float settlementRatio() {
		TConfig config = cd.findByKey(Constant.CONFIG_AGENT_SETTLEMENT_RATIO);
		float ratio = Constant.DEFAULT_AGENT_SETTLEMENT_RATIO;
		if(config != null) {
			ratio = Float.parseFloat(config.getValue());
		}
		return ratio;
	}
	
}
