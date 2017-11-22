package molab.component;

import molab.db.entity.TApply;

public class ApplyComponent {

	private TApply apply;
	private String createTime;

	/**
	 * @return the apply
	 */
	public TApply getApply() {
		return apply;
	}

	/**
	 * @param apply
	 *            the apply to set
	 */
	public void setApply(TApply apply) {
		this.apply = apply;
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
