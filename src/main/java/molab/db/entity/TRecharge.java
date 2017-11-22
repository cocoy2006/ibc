package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_recharge")
@DynamicUpdate(true)
public class TRecharge implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "owner_id", nullable = false)
	private Integer ownerId;

	@Column(nullable = false)
	private Float amount;

	@Column(name = "agent_fee", nullable = false)
	private Float agentFee;

	@Column(name = "admin_fee", nullable = false)
	private Float adminFee;

	@Column(name = "create_time", nullable = false)
	private Integer createTime;

	@Column(nullable = false)
	private Integer status;

	public TRecharge() {
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
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
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
	 * @return the agentFee
	 */
	public Float getAgentFee() {
		return agentFee;
	}

	/**
	 * @param agentFee
	 *            the agentFee to set
	 */
	public void setAgentFee(Float agentFee) {
		this.agentFee = agentFee;
	}

	/**
	 * @return the adminFee
	 */
	public Float getAdminFee() {
		return adminFee;
	}

	/**
	 * @param adminFee
	 *            the adminFee to set
	 */
	public void setAdminFee(Float adminFee) {
		this.adminFee = adminFee;
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
	};

}
