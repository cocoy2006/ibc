package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_device")
@DynamicUpdate(true)
public class TDevice implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "apply_id")
	private Integer applyId;

	@Column(name = "device_id")
	private Integer deviceId;

	@Column
	private String uuid;

	@Column
	private Integer major;

	@Column
	private Integer minor;

	@Column(name = "last_active_time")
	private Integer lastActiveTime;

	@Column(name = "poi_appid")
	private String poiAppid;

	@Column(name = "poi_id")
	private Integer poiId;

	@Column
	private String comment;

	@Column(name = "create_time")
	private Integer createTime;

	@Column(nullable = false)
	private Integer status;

	public TDevice() {
	};

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
	 * @return the applyId
	 */
	public Integer getApplyId() {
		return applyId;
	}

	/**
	 * @param applyId
	 *            the applyId to set
	 */
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the major
	 */
	public Integer getMajor() {
		return major;
	}

	/**
	 * @param major
	 *            the major to set
	 */
	public void setMajor(Integer major) {
		this.major = major;
	}

	/**
	 * @return the minor
	 */
	public Integer getMinor() {
		return minor;
	}

	/**
	 * @param minor
	 *            the minor to set
	 */
	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	/**
	 * @return the lastActiveTime
	 */
	public Integer getLastActiveTime() {
		return lastActiveTime;
	}

	/**
	 * @param lastActiveTime
	 *            the lastActiveTime to set
	 */
	public void setLastActiveTime(Integer lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	/**
	 * @return the poiAppid
	 */
	public String getPoiAppid() {
		return poiAppid;
	}

	/**
	 * @param poiAppid
	 *            the poiAppid to set
	 */
	public void setPoiAppid(String poiAppid) {
		this.poiAppid = poiAppid;
	}

	/**
	 * @return the poiId
	 */
	public Integer getPoiId() {
		return poiId;
	}

	/**
	 * @param poiId
	 *            the poiId to set
	 */
	public void setPoiId(Integer poiId) {
		this.poiId = poiId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	}

}
