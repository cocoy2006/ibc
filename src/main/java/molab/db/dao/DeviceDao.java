package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TDevice;

@Repository
public interface DeviceDao extends JpaRepository<TDevice, Integer> {
	
	public List<TDevice> findByStatus(Integer status);
	
	@Query("from TDevice where status = :status and id in (select deviceId from BindDeviceOwner where ownerId = :ownerId and status = 1)")
	public List<TDevice> findByOwnerIdAndStatus(@Param("ownerId") Integer ownerId, @Param("status") Integer status);
	
	@Query("from TDevice where status >= :status")
	public List<TDevice> findByLargeThanAndEqualToStatus(@Param("status") Integer status);
	
	@Query("from TDevice where status >= 3 and id in (select deviceId from BindDeviceAgent where agentId = :agentId and status = 1)")
	public List<TDevice> findForAgent(@Param("agentId") Integer agentId);
	
	@Query("from TDevice where status >= 4 and id in (select deviceId from BindDeviceOwner where ownerId = :ownerId and status = 1)")
	public List<TDevice> findForOwner(@Param("ownerId") Integer ownerId);
	
	public TDevice findByMajorAndMinor(Integer major, Integer minor);
	
	@Modifying
	@Transactional
	@Query("update TDevice set status = :status where id = :id")
	public void update(@Param("status") int status, @Param("id") int id);
	
}
