package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import molab.util.Molab;
import molab.util.Status;

@Entity
@Table(name = "bind_packet_device")
@DynamicUpdate(true)
public class BindPacketDevice implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "packet_id", nullable = false)
	private Integer packetId;

	@Column(name = "device_id", nullable = false)
	private Integer deviceId;

	@Column(name = "create_time", nullable = false)
	private Integer createTime;

	@Column(name = "invalid_time")
	private Integer invalidTime;

	@Column(nullable = false)
	private Integer status;

	public BindPacketDevice() {
	};

	public BindPacketDevice(int packetId, int deviceId) {
		this.packetId = packetId;
		this.deviceId = deviceId;
		this.createTime = Molab.parseInt(System.currentTimeMillis());
		this.status = Status.Common.YES.getInt();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the packetId
	 */
	public Integer getPacketId() {
		return packetId;
	}

	/**
	 * @param packetId
	 *            the packetId to set
	 */
	public void setPacketId(Integer packetId) {
		this.packetId = packetId;
	}

	/**
	 * @return the deviceId
	 */
	public Integer getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the createTime
	 */
	public Integer getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the invalidTime
	 */
	public Integer getInvalidTime() {
		return invalidTime;
	}

	/**
	 * @param invalidTime
	 *            the invalidTime to set
	 */
	public void setInvalidTime(Integer invalidTime) {
		this.invalidTime = invalidTime;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	};

}
