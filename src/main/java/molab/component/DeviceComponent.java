package molab.component;

import molab.db.entity.TAgent;
import molab.db.entity.TDevice;
import molab.db.entity.TOwner;
import molab.db.entity.TPacket;

public class DeviceComponent {

	private TDevice device;
	private TAgent agent;
	private TOwner owner;
	private TPacket packet;

	/**
	 * @return the device
	 */
	public TDevice getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(TDevice device) {
		this.device = device;
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

}
