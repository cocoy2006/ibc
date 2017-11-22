package molab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import molab.db.dao.ApplyDao;
import molab.db.dao.DeviceDao;
import molab.db.dao.PacketDao;
import molab.db.entity.TApply;
import molab.db.entity.TDevice;
import molab.util.Molab;
import molab.util.Status;
import weixin.popular.api.ShakeAroundAPI;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyId;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyIdResult;
import weixin.popular.bean.shakearound.device.applyid.DeviceApplyIdResultData;
import weixin.popular.bean.shakearound.device.applystatus.DeviceApplyStatus;
import weixin.popular.bean.shakearound.device.applystatus.DeviceApplyStatusResult;
import weixin.popular.bean.shakearound.device.applystatus.DeviceApplyStatusResultData;
import weixin.popular.bean.shakearound.device.search.DeviceSearch;
import weixin.popular.bean.shakearound.device.search.DeviceSearchResult;
import weixin.popular.bean.shakearound.device.search.DeviceSearchResultData;
import weixin.popular.bean.shakearound.device.search.DeviceSearchResultDataDevice;
import weixin.popular.support.TokenManager;

@Configuration
@EnableScheduling
public class Timer {
	
	@Autowired
	private ApplyDao ad;
	
	@Autowired
	private DeviceDao dd;
	
	@Autowired
	private PacketDao pd;
	
	@Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
    public void deviceApplyId() {
        List<TApply> applyList = ad.findByAuditStatusAndStatus(
				Status.AuditState.INIT.getInt(), Status.Common.OPEN.getInt());
        for(TApply apply : applyList) {
        	DeviceApplyId deviceApplyId = new DeviceApplyId();
        	deviceApplyId.setQuantity(apply.getQuantity());
        	deviceApplyId.setApplyReason(apply.getReason());
        	deviceApplyId.setComment(apply.getComment());
        	// 获取申请设备ID
    		DeviceApplyIdResult result = ShakeAroundAPI.deviceApplyId(TokenManager.getDefaultToken(), deviceApplyId);
    		if(result != null) {
    			if("0".equals(result.getErrcode())) { // 返回成功
    				DeviceApplyIdResultData data = result.getData();
    				if(data != null) {
    					// !!获取批次ID
//    					apply.setApplyId(data.getApplyId());
    					int auditStatus = data.getAuditStatus();
    					if(auditStatus == Status.AuditState.NOT_PASS.getInt()) {
    						// 审核状态为‘审核未通过’，本次申请结束
//    						apply.setStatus(Status.ApplyState.CLOSE.getInt());
    						ad.update(data.getApplyId(), auditStatus, data.getAuditComment(), 
    								Status.Common.CLOSE.getInt(), apply.getId());
    					} else {
    						// 审核状态为‘审核中’或‘审核已通过’，等待deviceApplyStatus()调度
//    						apply.setStatus(Status.ApplyState.OPEN.getInt());
    						ad.update(data.getApplyId(), auditStatus, data.getAuditComment(), 
    								Status.Common.OPEN.getInt(), apply.getId());
    					}
//    					apply.setAuditStatus(auditStatus);
//    					apply.setAuditComment(data.getAuditComment());
    				}
    			} else { // 返回失败，记录错误代码和错误原因，关闭本次申请
//    				apply.setErrcode(Integer.parseInt(result.getErrcode()));
//    				apply.setErrmsg(result.getErrmsg());
//    				apply.setStatus(Status.ApplyState.CLOSE.getInt());
    				ad.update(Integer.parseInt(result.getErrcode()), result.getErrmsg(), 
    						Status.Common.CLOSE.getInt(), apply.getId());
    			}
    			
    		}
        }
    }
	
	@Scheduled(cron = "0/15 * * * * ?") // 每15秒执行一次
	public void deviceApplyStatus() {
		// 查询审核状态为‘审核中’且申请状态为‘开放OPEN’的工单
		List<TApply> applyList = ad.findByAuditStatusAndStatus(
				Status.AuditState.ING.getInt(), Status.Common.OPEN.getInt());
        for(TApply apply : applyList) {
        	DeviceApplyStatus deviceApplyStatus = new DeviceApplyStatus();
        	deviceApplyStatus.setApplyId(apply.getApplyId());
        	// 获取审核状态
    		DeviceApplyStatusResult result = ShakeAroundAPI.deviceApplyStatus(TokenManager.getDefaultToken(), deviceApplyStatus);
    		if(result != null) {
    			if("0".equals(result.getErrcode())) { // 返回成功
    				DeviceApplyStatusResultData data = result.getData();
    				if(data != null) {
    					int auditStatus = data.getAuditStatus();
    					if(auditStatus == Status.AuditState.PAST.getInt()) {
    						// 审核状态为‘审核已通过’，进入可获取数据状态
//    						apply.setAuditStatus(auditStatus);
//        					apply.setAuditComment(data.getAuditComment());
//        					ad.update(apply.getStatus(), apply.getId());
        					ad.update(auditStatus, data.getAuditComment(), apply.getId());
    					}
    				}
    			} else { // 返回失败，记录错误代码和错误原因，关闭本次申请
//    				apply.setErrcode(Integer.parseInt(result.getErrcode()));
//    				apply.setErrmsg(result.getErrmsg());
//    				apply.setStatus(Status.ApplyState.CLOSE.getInt());
    				ad.update(Integer.parseInt(result.getErrcode()), result.getErrmsg(), 
    						Status.Common.CLOSE.getInt(), apply.getId());
    			}
    		}
        }
	}
	
	@Scheduled(cron = "0/25 * * * * ?") // 每25秒执行一次
	public void deviceSearch() {
		// 查询审核状态为‘审核已通过’且申请状态为‘开放OPEN’的工单
		List<TApply> applyList = ad.findByAuditStatusAndStatus(
				Status.AuditState.PAST.getInt(), Status.Common.OPEN.getInt());
		for(TApply apply : applyList) {
			DeviceSearch deviceSearch = new DeviceSearch();
			deviceSearch.setApplyId(apply.getApplyId());
			deviceSearch.setType(3);
			deviceSearch.setLastSeen(0);
			deviceSearch.setCount(50);
        	// 查询设备列表
    		DeviceSearchResult result = ShakeAroundAPI.deviceSearch(TokenManager.getDefaultToken(), deviceSearch);
    		if(result != null) {
    			if("0".equals(result.getErrcode())) { // 返回成功
    				DeviceSearchResultData data = result.getData();
    				if(data != null) {
    					List<DeviceSearchResultDataDevice> deviceList = data.getDevices();
    					for(DeviceSearchResultDataDevice wxDevice : deviceList) {
    						TDevice device = new TDevice();
    						device.setApplyId(apply.getApplyId());
    						device.setDeviceId(wxDevice.getDeviceId());
    						device.setUuid(wxDevice.getUuid());
    						device.setMajor(wxDevice.getMajor());
    						device.setMinor(wxDevice.getMinor());
    						device.setLastActiveTime(Integer.parseInt(String.valueOf(wxDevice.getLastActiveTime())));
    						device.setPoiAppid(wxDevice.getPoiAppId());
    						device.setPoiId(wxDevice.getPoiId());
    						device.setComment(wxDevice.getComment());
    						device.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
    						device.setStatus(Status.DeviceState.UN_CONF.getInt());
    						dd.save(device);
    					}
    					ad.update(Status.Common.CLOSE.getInt(), apply.getId());
    				}
    			} else { // 返回失败，记录错误代码和错误原因，关闭本次申请
//    				apply.setErrcode(Integer.parseInt(result.getErrcode()));
//    				apply.setErrmsg(result.getErrmsg());
//    				apply.setStatus(Status.ApplyState.CLOSE.getInt());
    				ad.update(Integer.parseInt(result.getErrcode()), result.getErrmsg(), 
    						Status.Common.CLOSE.getInt(), apply.getId());
    			}
    		}
		}
	}
	
	@Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次，关闭过期红包
	public void closePacket() {
		pd.update(Molab.parseInt(System.currentTimeMillis()));
	}

}
