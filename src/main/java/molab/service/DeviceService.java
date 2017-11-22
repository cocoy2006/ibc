package molab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.component.DeviceComponent;
import molab.db.dao.AgentDao;
import molab.db.dao.BindDeviceAgentDao;
import molab.db.dao.BindDeviceOwnerDao;
import molab.db.dao.BindPacketDeviceDao;
import molab.db.dao.DeviceDao;
import molab.db.dao.OwnerDao;
import molab.db.dao.PacketDao;
import molab.db.entity.BindDeviceAgent;
import molab.db.entity.BindDeviceOwner;
import molab.db.entity.BindPacketDevice;
import molab.db.entity.TAgent;
import molab.db.entity.TDevice;
import molab.db.entity.TOwner;
import molab.db.entity.TPacket;
import molab.util.Molab;
import molab.util.Status;

@Service
public class DeviceService {
	
	@Autowired
	private AgentDao ad;
	
	@Autowired
	private DeviceDao dd;
	
	@Autowired
	private OwnerDao od;
	
	@Autowired
	private PacketDao pd;
	
	@Autowired
	private BindDeviceAgentDao bdad;
	
	@Autowired
	private BindDeviceOwnerDao bdod;
	
	@Autowired
	private BindPacketDeviceDao bpdd;
	
	public List<TDevice> findByOwnerIdAndStatus(int ownerId, int status) {
		return dd.findByOwnerIdAndStatus(ownerId, status);
	}
	
	public List<DeviceComponent> findByLargeThanAndEqualToStatusForAdmin() {
		List<TDevice> deviceList = dd.findByLargeThanAndEqualToStatus(Status.DeviceState.CLOSED.getInt());
		List<DeviceComponent> dcList = new ArrayList<DeviceComponent>();
		for(TDevice device : deviceList) {
			DeviceComponent dc = new DeviceComponent();
			dc.setDevice(device);
			if(device.getStatus() > Status.DeviceState.UN_AGENT.getInt()) { // 已经分配代理商
				BindDeviceAgent bind = bdad.findByDeviceIdAndStatus(device.getId(), Status.Common.YES.getInt());
				if(bind != null) {
					TAgent agent = ad.findOne(bind.getAgentId());
					if(agent != null) {
						dc.setAgent(agent);
					}
				}
			}
			dcList.add(dc);
		}
		return dcList;
	}
	
	public List<DeviceComponent> findByLargeThanAndEqualToStatusForAgent(int agentId) {
//		List<TDevice> deviceList = dd.findByLargeThanAndEqualToStatus(Status.DeviceState.UN_OWNER.getInt());
		List<TDevice> deviceList = dd.findForAgent(agentId);
		List<DeviceComponent> dcList = new ArrayList<DeviceComponent>();
		for(TDevice device : deviceList) {
			DeviceComponent dc = new DeviceComponent();
			dc.setDevice(device);
			if(device.getStatus() > Status.DeviceState.UN_OWNER.getInt()) { // 已经分配商家
				BindDeviceOwner bind = bdod.findByDeviceIdAndStatus(device.getId(), Status.Common.YES.getInt());
				if(bind != null) {
					TOwner owner = od.findOne(bind.getOwnerId());
					if(owner != null) {
						dc.setOwner(owner);
					}
				}
			}
			dcList.add(dc);
		}
		return dcList;
	}
	
	public List<DeviceComponent> findByLargeThanAndEqualToStatusForOwner(int ownerId) {
//		List<TDevice> deviceList = dd.findByLargeThanAndEqualToStatus(Status.DeviceState.UNBOUND.getInt());
		List<TDevice> deviceList = dd.findForOwner(ownerId);
		List<DeviceComponent> dcList = new ArrayList<DeviceComponent>();
		for(TDevice device : deviceList) {
			DeviceComponent dc = new DeviceComponent();
			dc.setDevice(device);
			if(device.getStatus() > Status.DeviceState.UNBOUND.getInt()) { // 已经绑定红包
				BindPacketDevice bind = bpdd.findByDeviceIdAndStatus(device.getId(), Status.Common.YES.getInt());
				if(bind != null) {
					TPacket packet = pd.findOne(bind.getPacketId());
					if(packet != null) {
						dc.setPacket(packet);
					}
				}
			}
			dcList.add(dc);
		}
		return dcList;
	}
	
	public void conf(int id) {
		dd.update(Status.DeviceState.UN_AGENT.getInt(), id);
	}
	
	public void batchConf(String ids) {
		String[] idArray = ids.split(",");
		for(String idStr : idArray) {
			int id = Integer.parseInt(idStr);
			dd.update(Status.DeviceState.UN_AGENT.getInt(), id);
		}
	}
	
	public void assignAgent(int deviceId, int agentId) {
		TDevice device = dd.findOne(deviceId);
		if(device != null && device.getStatus() == Status.DeviceState.UN_AGENT.getInt()) {
			// 新增绑定关系
			BindDeviceAgent bind = new BindDeviceAgent();
			bind.setDeviceId(deviceId);
			bind.setAgentId(agentId);
			bind.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
			bind.setStatus(Status.Common.YES.getInt());
			bdad.save(bind);
			// 更新设备状态
			dd.update(Status.DeviceState.UN_OWNER.getInt(), deviceId);
		}
	}
	
	public void assignOwner(int deviceId, int ownerId) {
		TDevice device = dd.findOne(deviceId);
		if(device != null && device.getStatus() == Status.DeviceState.UN_OWNER.getInt()) {
			// 新增绑定关系
			BindDeviceOwner bind = new BindDeviceOwner();
			bind.setDeviceId(deviceId);
			bind.setOwnerId(ownerId);
			bind.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
			bind.setStatus(Status.Common.YES.getInt());
			bdod.save(bind);
			// 更新设备状态
			dd.update(Status.DeviceState.UNBOUND.getInt(), deviceId);
		}
	}
	
	public TDevice add(Integer applyId, Integer deviceId, String uuid, 
			Integer major, Integer minor, String comment) {
		TDevice device = dd.findByMajorAndMinor(major, minor);
		if(device != null) {
			return null;
		}
		device = new TDevice();
		Integer _applyId = applyId == null ? 0 : applyId;
		device.setApplyId(_applyId);
		Integer _deviceId = deviceId == null ? 0 : deviceId;
		device.setDeviceId(_deviceId);
		String _uuid = uuid == null ? "FDA50693-A4E2-4FB1-AFCF-C6EB07647825" : uuid;
		device.setUuid(_uuid);
		device.setMajor(major);
		device.setMinor(minor);
		device.setComment(comment);
		device.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
		device.setStatus(Status.DeviceState.UN_AGENT.getInt());
		return dd.save(device);
	}
	
}
