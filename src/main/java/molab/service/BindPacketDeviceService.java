package molab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.db.dao.BindPacketDeviceDao;
import molab.db.dao.DeviceDao;
import molab.db.dao.PacketDao;
import molab.db.entity.BindPacketDevice;
import molab.db.entity.TPacket;
import molab.util.Molab;
import molab.util.Status;

@Service
public class BindPacketDeviceService {
	
	@Autowired
	private DeviceDao dd;
	
	@Autowired
	private PacketDao pd;
	
	@Autowired
	private BindPacketDeviceDao bpdd;
	
	public void bind(int packetId, String deviceIds) {
		// update packet
		pd.update(Status.PacketState.BINDING.getInt(), packetId);
		// bind packet and devices one by one
		String[] deviceIdArray = deviceIds.split(",");
		for(String deviceIdStr : deviceIdArray) {
			int deviceId = Integer.parseInt(deviceIdStr);
			// update device
			dd.update(Status.DeviceState.BINDING.getInt(), deviceId);
			BindPacketDevice bpd = new BindPacketDevice();
			bpd.setPacketId(packetId);
			bpd.setDeviceId(deviceId);
			bpd.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
			bpd.setStatus(Status.Common.OPEN.getInt());
			bpdd.save(bpd);
		}
	}
	
	public void unbound(int deviceId) {
		// update device
		dd.update(Status.DeviceState.UNBOUND.getInt(), deviceId);
		// unbound packet and device
		BindPacketDevice bpd = bpdd.findByDeviceId(deviceId);
		if(bpd != null) {
			// update packet
			TPacket packet = pd.findOne(bpd.getPacketId());
			if(packet != null) {
				pd.update(Status.PacketState.UNBOUND.getInt(), packet.getId());
			}
			bpdd.update(Status.Common.NO.getInt(), bpd.getId());
		}
	}
	
}
