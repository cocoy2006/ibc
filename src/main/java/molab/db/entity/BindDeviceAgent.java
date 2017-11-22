package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "bind_device_agent")
@DynamicUpdate(true)
public class BindDeviceAgent implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "agent_id", nullable = false)
	private Integer agentId;

	@Column(name = "device_id", nullable = false)
	private Integer deviceId;

	@Column(name = "create_time", nullable = false)
	private Integer createTime;

	@Column(name = "invalid_time")
	private Integer invalidTime;

	@Column(nullable = false)
	private Integer status;

	public BindDeviceAgent() {
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
	 * @return the agentId
	 */
	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId
	 *            the agentId to set
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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
	}

}
