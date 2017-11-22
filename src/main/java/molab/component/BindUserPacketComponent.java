package molab.component;

import molab.db.entity.BindUserPacket;
import molab.db.entity.TPacket;
import molab.db.entity.TUser;

public class BindUserPacketComponent {

	private BindUserPacket bup;
	private TUser user;
	private TPacket packet;
	private String createTime;

	/**
	 * @return the bup
	 */
	public BindUserPacket getBup() {
		return bup;
	}

	/**
	 * @param bup
	 *            the bup to set
	 */
	public void setBup(BindUserPacket bup) {
		this.bup = bup;
	}

	/**
	 * @return the user
	 */
	public TUser getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(TUser user) {
		this.user = user;
	}

	/**
	 * @return the packet
	 */
	public TPacket getPacket() {
		return packet;
	}

	/**
	 * @param packet
	 *            the packet to set
	 */
	public void setPacket(TPacket packet) {
		this.packet = packet;
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
