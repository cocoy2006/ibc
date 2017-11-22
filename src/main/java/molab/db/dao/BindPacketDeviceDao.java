package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.BindPacketDevice;

@Repository
public interface BindPacketDeviceDao extends JpaRepository<BindPacketDevice, Integer> {

	public BindPacketDevice findByDeviceId(Integer deviceId);
	
	public BindPacketDevice findByDeviceIdAndStatus(Integer deviceId, Integer status);
	
	@Modifying
	@Transactional
	@Query("update BindPacketDevice set status = :status where id = :id")
	public void update(@Param("status") int status, @Param("id") int id);
	
}
