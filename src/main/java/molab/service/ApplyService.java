package molab.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.component.ApplyComponent;
import molab.db.dao.ApplyDao;
import molab.db.entity.TApply;
import molab.util.Molab;
import molab.util.Status;
import weixin.popular.api.ShakeAroundAPI;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyId;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyIdResult;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyIdResultData;
import weixin.popular.support.TokenManager;

@Service
public class ApplyService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplyDao ad;
	
	public List<ApplyComponent> findAll() {
		List<TApply> applyList = ad.findAll();
		List<ApplyComponent> acList = new ArrayList<ApplyComponent>();
		for(TApply apply : applyList) {
			ApplyComponent ac = new ApplyComponent();
			ac.setApply(apply);
			ac.setCreateTime(Molab.parseDate(apply.getCreateTime()));
			acList.add(ac);
		}
		return acList;
	}
	
	public TApply add(Integer quantity, String reason, String comment) {
		TApply apply = new TApply();
		apply.setQuantity(quantity);
		apply.setReason(reason);
		apply.setComment(comment);
		apply.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
		apply.setStatus(Status.Common.OPEN.getInt());
		apply.setAuditStatus(Status.AuditState.INIT.getInt());
		// post request to weixin server
//		String postJson = 
//				"{"
//				+ "\"quantity\":" + quantity + ", "
//				+ "\"apply_reason\":\"" + reason + "\","
//				+ "\"comment\":\"" + comment + "\","
//				+ "}";
//		String postJson = String.format(Constant.WX_SHAKEAROUND_DEVICE_APPLYID, 
//				quantity, reason, comment);
//		JsonObject param = new JsonObject();
//		param.addProperty("quantity", quantity);
//		param.addProperty("apply_reason", reason);
//		param.addProperty("comment", comment);
//		String postJson = param.toString();
		
//		DeviceApplyId deviceApplyId = new DeviceApplyId();
//    	deviceApplyId.setQuantity(quantity);
//    	deviceApplyId.setApplyReason(reason);
//    	deviceApplyId.setComment(comment);
		
//		DeviceApplyIdResult result = ShakeAroundAPI.deviceApplyId(TokenManager.getDefaultToken(), deviceApplyId);
//		if(result != null) {
//			if("0".equals(result.getErrcode())) { // 返回成功
//				DeviceApplyIdResultData data = result.getData();
//				if(data != null) {
//					apply.setApplyId(data.getApplyId());
//					int auditStatus = data.getAuditStatus();
//					// 如果审核状态为‘审核未通过’，关闭本次申请，否则在全局定时任务中拉取设备数据
//					if(auditStatus == Status.AuditState.NOTPASS.getInt()) {
//						apply.setStatus(Status.ApplyState.CLOSE.getInt());
//					}
//					apply.setAuditStatus(auditStatus);
//					apply.setAuditComment(data.getAuditComment());
//					
//					
//				}
//			} else { // 返回失败，记录错误代码和错误原因，关闭本次申请
//				apply.setErrcode(Integer.parseInt(result.getErrcode()));
//				apply.setErrmsg(result.getErrmsg());
//				apply.setStatus(Status.ApplyState.CLOSE.getInt());
//			}
//		} else {
//			log.error("Connection error.");
//		}
		return ad.save(apply);
	}
	
}
