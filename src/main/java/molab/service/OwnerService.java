package molab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import molab.component.OwnerComponent;
import molab.db.dao.AdminDao;
import molab.db.dao.AgentDao;
import molab.db.dao.ConfigDao;
import molab.db.dao.IndustryDao;
import molab.db.dao.OwnerDao;
import molab.db.dao.RechargeDao;
import molab.db.dao.SubIndustryDao;
import molab.db.entity.TAdmin;
import molab.db.entity.TAgent;
import molab.db.entity.TConfig;
import molab.db.entity.TOwner;
import molab.db.entity.TRecharge;
import molab.db.entity.TSubIndustry;
import molab.util.Constant;
import molab.util.Molab;
import molab.util.Status;

@Service
public class OwnerService {
	
	@Autowired
	private AdminDao add;
	
	@Autowired
	private AgentDao ad;

	@Autowired
	private ConfigDao cd;
	
	@Autowired
	private OwnerDao od;
	
	@Autowired
	private IndustryDao id;
	
	@Autowired
	private RechargeDao rd;
	
	@Autowired
	private SubIndustryDao sid;
	
	public TOwner signin(String username, String password) {
		return od.findByUsernameAndPasswordAndStatus(username, password, Status.Common.YES.getInt());
	}
	
	public List<OwnerComponent> findAll(int agentId) {
		List<TOwner> ownerList = od.findByAgentId(agentId);
		return ocList(ownerList);
	}
	
	public List<OwnerComponent> findAll() {
		List<TOwner> ownerList = od.findAll();
		return ocList(ownerList);
	}
	
	private List<OwnerComponent> ocList(List<TOwner> ownerList) {
		List<OwnerComponent> ocList = new ArrayList<OwnerComponent>();
		for(TOwner owner : ownerList) {
			OwnerComponent oc = new OwnerComponent();
			oc.setOwner(owner);
			oc.setAgent(ad.findOne(owner.getAgentId()));
			TSubIndustry subIndustry = sid.findOne(owner.getSubIndustryId());
			if(subIndustry != null) {
				oc.setSubIndustry(subIndustry);
				oc.setIndustry(id.findOne(subIndustry.getIndustryId()));
			}
			oc.setCreateTime(Molab.parseDate(owner.getCreateTime()));
			oc.setExpireTime(Molab.parseDate(owner.getExpireTime()));
			ocList.add(oc);
		}
		return ocList;
	}
	
	public TOwner add(Integer agentId, Integer subIndustryId, 
			String name, String username, String password,
			String contact, String phone, String expireTime) {
		TOwner owner = od.findByUsername(username);
		if(owner != null) {
			return null;
		}
		owner = new TOwner();
		owner.setAgentId(agentId);
		owner.setSubIndustryId(subIndustryId);
		owner.setName(name);
		owner.setUsername(username);
		owner.setPassword(password);
		owner.setContact(contact);
		owner.setPhone(phone);
		owner.setAmountTotal(0f);
		owner.setAmountConsumed(0f);
		owner.setAmountOut(0f);
		owner.setAmountIn(0f);
		owner.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
		owner.setExpireTime(Molab.parseDate(expireTime));
		owner.setStatus(Status.Common.OPEN.getInt());
		return od.save(owner);
	}
	
	public void update(Integer id, Integer status) {
		ad.update(id, status);
	}
	
	@Transactional
	public void recharge(TOwner owner, Float amountRecharge, Float amountPayment) {
		float amountTotal = owner.getAmountTotal() + amountRecharge;
		owner.setAmountTotal(amountTotal);
		od.update(owner.getId(), amountTotal);
		// settlement
		float settlementRatio = settlementRatio();
		TAgent agent = ad.findOne(owner.getAgentId());
		if(agent != null) {
			TAdmin admin = add.findOne(agent.getAdminId());
			if(admin != null) {
				float settlementFee = amountPayment - amountRecharge;
				float agentFee = Molab.rescale(settlementFee * settlementRatio);
				ad.update(agent.getId(), agent.getAmountTotal() + agentFee);
				float adminFee = Molab.rescale(settlementFee - agentFee);
				add.update(admin.getId(), admin.getAmountTotal() + adminFee);
				// recharge log
				TRecharge recharge = new TRecharge();
				recharge.setOwnerId(owner.getId());
				recharge.setAmount(amountRecharge);
				recharge.setAgentFee(agentFee);
				recharge.setAdminFee(adminFee);
				recharge.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
				recharge.setStatus(Status.Common.SUCCESS.getInt());
				rd.save(recharge);
			}
		}
	}
	
	private float settlementRatio() {
		TConfig config = cd.findByKey(Constant.CONFIG_AGENT_SETTLEMENT_RATIO);
		float ratio = Constant.DEFAULT_AGENT_SETTLEMENT_RATIO;
		if(config != null) {
			ratio = Float.parseFloat(config.getValue());
		}
		return ratio;
	}
	
	public float rechargeFee() {
		TConfig config = cd.findByKey(Constant.CONFIG_OWNER_RECHARGE_FEE);
		float fee = Constant.DEFAULT_OWNER_RECHARGE_FEE;
		if(config != null) {
			fee = Float.parseFloat(config.getValue());
		}
		return fee;
	}
	
}
