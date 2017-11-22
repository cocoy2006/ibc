package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_owner")
@DynamicUpdate(true)
public class TOwner implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "agent_id", nullable = false)
	private Integer agentId;

	@Column(name = "sub_industry_id", nullable = false)
	private Integer subIndustryId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String contact;

	@Column(nullable = false)
	private String phone;

	@Column(name = "amount_total")
	private Float amountTotal;

	@Column(name = "amount_consumed")
	private Float amountConsumed;

	@Column(name = "amount_out")
	private Float amountOut;

	@Column(name = "amount_in")
	private Float amountIn;

	@Column(name = "create_time")
	private Integer createTime;

	@Column(name = "expire_time")
	private Integer expireTime;

	@Column(nullable = false)
	private Integer status;

	public TOwner() {
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
	 * @return the subIndustryId
	 */
	public Integer getSubIndustryId() {
		return subIndustryId;
	}

	/**
	 * @param subIndustryId
	 *            the subIndustryId to set
	 */
	public void setSubIndustryId(Integer subIndustryId) {
		this.subIndustryId = subIndustryId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the amountTotal
	 */
	public Float getAmountTotal() {
		return amountTotal;
	}

	/**
	 * @param amountTotal
	 *            the amountTotal to set
	 */
	public void setAmountTotal(Float amountTotal) {
		this.amountTotal = amountTotal;
	}

	/**
	 * @return the amountConsumed
	 */
	public Float getAmountConsumed() {
		return amountConsumed;
	}

	/**
	 * @param amountConsumed
	 *            the amountConsumed to set
	 */
	public void setAmountConsumed(Float amountConsumed) {
		this.amountConsumed = amountConsumed;
	}

	/**
	 * @return the amountOut
	 */
	public Float getAmountOut() {
		return amountOut;
	}

	/**
	 * @param amountOut
	 *            the amountOut to set
	 */
	public void setAmountOut(Float amountOut) {
		this.amountOut = amountOut;
	}

	/**
	 * @return the amountIn
	 */
	public Float getAmountIn() {
		return amountIn;
	}

	/**
	 * @param amountIn
	 *            the amountIn to set
	 */
	public void setAmountIn(Float amountIn) {
		this.amountIn = amountIn;
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
	 * @return the expireTime
	 */
	public Integer getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime
	 *            the expireTime to set
	 */
	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
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
