package molab.component;

import molab.db.entity.TOwner;
import molab.db.entity.TRecharge;

public class RechargeComponent {

	private TRecharge recharge;
	private TOwner owner;
	private String createTime;

	/**
	 * @return the recharge
	 */
	public TRecharge getRecharge() {
		return recharge;
	}

	/**
	 * @param recharge
	 *            the recharge to set
	 */
	public void setRecharge(TRecharge recharge) {
		this.recharge = recharge;
	}

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

}
