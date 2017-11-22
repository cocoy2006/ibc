package molab.component;

import java.util.List;

import molab.db.entity.BindDeviceAgent;
import molab.db.entity.TAgent;

public class AgentComponent {

	private TAgent agent;
	private String createTime;
	private String expireTime;
	private List<BindDeviceAgent> deviceList;

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

	/**
	 * @return the deviceList
	 */
	public List<BindDeviceAgent> getDeviceList() {
		return deviceList;
	}

	/**
	 * @param deviceList
	 *            the deviceList to set
	 */
	public void setDeviceList(List<BindDeviceAgent> deviceList) {
		this.deviceList = deviceList;
	}

}
