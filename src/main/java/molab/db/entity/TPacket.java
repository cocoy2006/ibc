package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_packet")
@DynamicUpdate(true)
public class TPacket implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "owner_id", nullable = false)
	private Integer ownerId;

	@Column(name = "amount_total", nullable = false)
	private Float amountTotal;

	@Column(name = "amount_left", nullable = false)
	private Float amountLeft;

	@Column(name = "num_total", nullable = false)
	private Integer numTotal;

	@Column(name = "num_left", nullable = false)
	private Integer numLeft;

	@Column(nullable = false)
	private Integer type;

	@Column
	private String title;

	@Column
	private String description;

	@Column(name = "pic_url")
	private String picUrl;

	@Column(name = "start_time")
	private Integer startTime;

	@Column(name = "end_time")
	private Integer endTime;

	@Column(name = "create_time")
	private Integer createTime;

	@Column(nullable = false)
	private Integer status;

	public TPacket() {
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
	 * @return the amountLeft
	 */
	public Float getAmountLeft() {
		return amountLeft;
	}

	/**
	 * @param amountLeft
	 *            the amountLeft to set
	 */
	public void setAmountLeft(Float amountLeft) {
		this.amountLeft = amountLeft;
	}

	/**
	 * @return the numTotal
	 */
	public Integer getNumTotal() {
		return numTotal;
	}

	/**
	 * @param numTotal
	 *            the numTotal to set
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}

	/**
	 * @return the numLeft
	 */
	public Integer getNumLeft() {
		return numLeft;
	}

	/**
	 * @param numLeft
	 *            the numLeft to set
	 */
	public void setNumLeft(Integer numLeft) {
		this.numLeft = numLeft;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the startTime
	 */
	public Integer getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Integer getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
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
