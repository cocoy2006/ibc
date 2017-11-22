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
@Table(name = "bind_user_packet")
@DynamicUpdate(true)
public class BindUserPacket implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "packet_id", nullable = false)
	private Integer packetId;

	@Column(nullable = false)
	private Float amount;

	@Column(name = "create_time", nullable = false)
	private Integer createTime;

	@Column(name = "invalid_time")
	private Integer invalidTime;

	@Column(nullable = false)
	private Integer status;

	public BindUserPacket() {
	};

	public BindUserPacket(int userId, int packetId, float amount) {
		this.userId = userId;
		this.packetId = packetId;
		this.amount = amount;
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
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
