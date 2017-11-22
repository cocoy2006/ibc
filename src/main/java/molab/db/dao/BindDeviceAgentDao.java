package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.BindDeviceAgent;

@Repository
public interface BindDeviceAgentDao extends JpaRepository<BindDeviceAgent, Integer> {

	public List<BindDeviceAgent> findByAgentId(Integer agentId);
	
	public BindDeviceAgent findByDeviceIdAndStatus(Integer deviceId, Integer status);
	
}
