package molab.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import molab.component.BindUserPacketComponent;
import molab.db.dao.BindPacketDeviceDao;
import molab.db.dao.BindUserPacketDao;
import molab.db.dao.DeviceDao;
import molab.db.dao.PacketDao;
import molab.db.dao.UserDao;
import molab.db.entity.BindPacketDevice;
import molab.db.entity.BindUserPacket;
import molab.db.entity.TDevice;
import molab.db.entity.TOwner;
import molab.db.entity.TPacket;
import molab.db.entity.TUser;
import molab.properties.WebProperties;
import molab.util.MD5Util;
import molab.util.Molab;
import molab.util.PathUtil;
import molab.util.Status;
import weixin.popular.bean.shakearound.user.getshakeinfo.UserGetShakeInfoResultData;
import weixin.popular.bean.shakearound.user.getshakeinfo.UserGetShakeInfoResultDataBeaconInfo;

@Service
public class PacketService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeviceDao dd;
	
	@Autowired
	private PacketDao pd;
	
	@Autowired
	private UserDao ud;
	
	@Autowired
	private BindPacketDeviceDao bpdd;
	
	@Autowired
	private BindUserPacketDao bupd;
	
	@Autowired
	private WebProperties wp;
	
	public List<TPacket> findByOwnerId(Integer ownerId) {
		return pd.findByOwnerId(ownerId);
	}
	
	public List<TPacket> findByStatus(Integer status) {
		return pd.findByStatus(status);
	}
	
	public TPacket findOne(Integer id) {
		return pd.findOne(id);
	}
	
	public TPacket findOne(UserGetShakeInfoResultData data) {
		if(data != null) { // 存在设备及用户信息
			UserGetShakeInfoResultDataBeaconInfo beaconInfo = data.getBeaconInfo(); // 获取设备信息（major和minor）
			if(beaconInfo != null) {
				return pd.findAvailable(beaconInfo.getMajor(), beaconInfo.getMinor(), Status.PacketState.BINDING.getInt());
			}
		}
		return null;
	}
	
	public double open(String openid, int packetId, int major, int minor) {
		float amount = 0;
		TUser user = ud.findByOpenid(openid);
		TPacket packet = pd.findOne(packetId);
		if(packet != null) { // 当前红包可用
			BindUserPacket bup = bupd.findByUserIdAndPacketId(user.getId(), packet.getId());
			if(bup == null) { // 当前用户没有抢过当前红包
				boolean lastOne = false;
				if(packet.getNumLeft() == 1) { // 如果是最后一个红包，则全部分给当前用户
					amount = packet.getAmountLeft();
					lastOne = true;
				} else {
					if(packet.getType() == Status.PacketType.FIXED.getInt()) { // 固定金额红包
						amount = Molab.rescale(packet.getAmountTotal() / packet.getNumTotal());
					} else { // 随机金额红包
						amount = Molab.rescale(Math.random() * (packet.getAmountLeft() - packet.getNumLeft() * 0.01));
					}
				}
				// 更新数据库
				packet.setAmountLeft(packet.getAmountLeft() - amount);
				packet.setNumLeft(packet.getNumLeft() - 1);
				if(lastOne) {
					packet.setStatus(Status.PacketState.UNBOUND.getInt());
				}
				pd.update(packet.getAmountLeft(), packet.getNumLeft(), packet.getId()); // 更新红包数据
				bupd.save(new BindUserPacket(user.getId(), packet.getId(), amount)); // 新增抢红包数据
				user.setAmount(user.getAmount() + amount);
				ud.update(amount, user.getId()); // 更新用户数据
				if(lastOne) { // 解除红包和设备的绑定关系
					TDevice device = dd.findByMajorAndMinor(major, minor);
					if(device != null) {
						BindPacketDevice bpd = bpdd.findByDeviceId(device.getId());
						bpdd.delete(bpd);
					}
				}
			}
		} else { // 红包已抢完
			log.warn("Packet has already done.");
		}
		return amount;
	}
	
	public String pic(String name) {
		return wp.getUploadPath() + name;
//		return sp.getUploadPath() + name;
	}
	
	public TPacket add(TOwner owner, String title, String description, MultipartFile pic, 
			float amountTotal, int numTotal, int type,
			String startTime, String endTime) {
		TPacket packet = new TPacket();
		packet.setTitle(title);
		packet.setOwnerId(owner.getId());
		packet.setAmountTotal(amountTotal);
		packet.setAmountLeft(amountTotal);
		packet.setNumTotal(numTotal);
		packet.setNumLeft(numTotal);
		packet.setType(type);
		packet.setDescription(description);
		// pic
//		File temp = new File(PathUtil.upload(pic.getOriginalFilename()));
//		String picUrl = null;
//		try {
//			pic.transferTo(temp);
//			picUrl = Molab.rename(temp.getName(), MD5Util.getFileMD5(temp));
//			File dest = new File(PathUtil.upload(picUrl));
//			if(!dest.exists()) {
//				FileCopyUtils.copy(temp, dest);
//			}
//			temp.deleteOnExit();
//		} catch (IllegalStateException | IOException e) {
//			log.error(e.getMessage());
//		}
//		packet.setPicUrl(picUrl);
		if(!pic.isEmpty()) {
            try {
            	String picUrl = Molab.rename(pic.getOriginalFilename(), MD5Util.getBytesMD5(pic.getBytes()));
                Files.copy(pic.getInputStream(), Paths.get(wp.getUploadPath(), pic.getOriginalFilename()));  
            } catch (IOException|RuntimeException e) {}
		}
//		File temp = new File(sp.getUploadPath() + pic.getOriginalFilename());
//		String picUrl = null;
//		try {
//			pic.transferTo(temp);
//			picUrl = Molab.rename(temp.getName(), MD5Util.getFileMD5(temp));
//			File dest = new File(sp.getUploadPath() + picUrl);
//			if(!dest.exists()) {
//				FileCopyUtils.copy(temp, dest);
//			}
//			temp.deleteOnExit();
//		} catch (IllegalStateException | IOException e) {
//			log.error(e.getMessage());
//		}
//		packet.setPicUrl(picUrl);
		// pic end
		packet.setStartTime(Molab.parseTime(startTime));
		packet.setEndTime(Molab.parseTime(endTime));
		packet.setCreateTime(Molab.parseInt(System.currentTimeMillis()));
		packet.setStatus(Status.PacketState.UNBOUND.getInt());
		return pd.save(packet);
	}
	
	public List<BindUserPacketComponent> detail(int id) {
		List<BindUserPacket> bupList = bupd.findByPacketId(id);
		List<BindUserPacketComponent> bupcList = new ArrayList<BindUserPacketComponent>();
		for(BindUserPacket bup : bupList) {
			BindUserPacketComponent bupc = new BindUserPacketComponent();
			bupc.setBup(bup);
			bupc.setUser(ud.findOne(bup.getUserId()));
//			bupc.setPacket(pd.findOne(bup.getPacketId()));
			bupc.setCreateTime(Molab.parseDate(bup.getCreateTime()));
			bupcList.add(bupc);
		}
		return bupcList;
	}
	
}
