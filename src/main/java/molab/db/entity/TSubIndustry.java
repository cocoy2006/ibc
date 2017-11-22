package molab.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sub_industry")
@DynamicUpdate(true)
public class TSubIndustry implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "industry_id", nullable = false)
	private Integer industryId;

	@Column(nullable = false)
	private String name;

	public TSubIndustry() {
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
	 * @return the industryId
	 */
	public Integer getIndustryId() {
		return industryId;
	}

	/**
	 * @param industryId
	 *            the industryId to set
	 */
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
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

}
