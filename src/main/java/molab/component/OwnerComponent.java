package molab.component;

import molab.db.entity.TAgent;
import molab.db.entity.TIndustry;
import molab.db.entity.TOwner;
import molab.db.entity.TSubIndustry;

public class OwnerComponent {

	private TOwner owner;
	private TAgent agent;
	private TIndustry industry;
	private TSubIndustry subIndustry;
	private String createTime;
	private String expireTime;

	/**
	 * @return the owner
	 */
	public TOwner getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(TOwner owner) {
		this.owner = owner;
	}

	/**
	 * @return the agent
	 */
	public TAgent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(TAgent agent) {
		this.agent = agent;
	}

	/**
	 * @return the industry
	 */
	public TIndustry getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(TIndustry industry) {
		this.industry = industry;
	}

	/**
	 * @return the subIndustry
	 */
	public TSubIndustry getSubIndustry() {
		return subIndustry;
	}

	/**
	 * @param subIndustry
	 *            the subIndustry to set
	 */
	public void setSubIndustry(TSubIndustry subIndustry) {
		this.subIndustry = subIndustry;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime
	 *            the expireTime to set
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

}
