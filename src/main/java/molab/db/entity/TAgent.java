package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_agent")
@DynamicUpdate(true)
public class TAgent implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "admin_id", nullable = false)
	private Integer adminId;

	@Column
	private String name;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private String contact;

	@Column
	private String phone;

	@Column(name = "settlement_ratio")
	private Float settlementRatio;

	@Column(name = "amount_total")
	private Float amountTotal;

	@Column(name = "amount_freezed")
	private Float amountFreezed;

	@Column(name = "amount_out")
	private Float amountOut;

	@Column(name = "amount_in")
	private Float amountIn;

	@Column(name = "create_time")
	private Integer createTime;

	@Column(name = "expire_time")
	private Integer expireTime;

	@Column(name = "owner_allowed")
	private Integer ownerAllowed;

	@Column(name = "owner_count")
	private Integer ownerCount;

	@Column(nullable = false)
	private Integer status;

	public TAgent() {
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
	 * @return the adminId
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 *            the adminId to set
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
	 * @return the settlementRatio
	 */
	public Float getSettlementRatio() {
		return settlementRatio;
	}

	/**
	 * @param settlementRatio
	 *            the settlementRatio to set
	 */
	public void setSettlementRatio(Float settlementRatio) {
		this.settlementRatio = settlementRatio;
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
	 * @return the amountFreezed
	 */
	public Float getAmountFreezed() {
		return amountFreezed;
	}

	/**
	 * @param amountFreezed
	 *            the amountFreezed to set
	 */
	public void setAmountFreezed(Float amountFreezed) {
		this.amountFreezed = amountFreezed;
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
	 * @return the ownerAllowed
	 */
	public Integer getOwnerAllowed() {
		return ownerAllowed;
	}

	/**
	 * @param ownerAllowed
	 *            the ownerAllowed to set
	 */
	public void setOwnerAllowed(Integer ownerAllowed) {
		this.ownerAllowed = ownerAllowed;
	}

	/**
	 * @return the ownerCount
	 */
	public Integer getOwnerCount() {
		return ownerCount;
	}

	/**
	 * @param ownerCount
	 *            the ownerCount to set
	 */
	public void setOwnerCount(Integer ownerCount) {
		this.ownerCount = ownerCount;
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
