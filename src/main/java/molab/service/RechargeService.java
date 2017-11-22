package molab.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.component.RechargeComponent;
import molab.db.dao.RechargeDao;
import molab.db.entity.TRecharge;
import molab.util.Molab;

@Service
public class RechargeService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RechargeDao rd;
	
	public List<RechargeComponent> findAll(int ownerId) {
		List<TRecharge> rechargeList = rd.findByOwnerId(ownerId);
		List<RechargeComponent> rcList = new ArrayList<RechargeComponent>();
		for(TRecharge recharge : rechargeList) {
			RechargeComponent rc = new RechargeComponent();
			rc.setRecharge(recharge);
			rc.setCreateTime(Molab.parseDate(recharge.getCreateTime()));
			rcList.add(rc);
		}
		return rcList;
	}
	
}
