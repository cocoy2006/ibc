package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TPacket;

@Repository
public interface PacketDao extends JpaRepository<TPacket, Integer> {

	public List<TPacket> findByOwnerId(Integer ownerId);
	
	public List<TPacket> findByStatus(Integer status);
	
	@Modifying
	@Transactional
	@Query("update TPacket set amountLeft = :amountLeft, numLeft = :numLeft where id = :id")
	public void update(@Param("amountLeft") float amountLeft, @Param("numLeft") int numLeft, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update TPacket set status = :status where id = :id")
	public void update(@Param("status") int status, @Param("id") int id);

	@Query("from TPacket where id = (select packetId from BindPacketDevice where deviceId = (select id from TDevice where major = :major and minor = :minor)) and status = :status")
	public TPacket findAvailable(@Param("major") int major, @Param("minor") int minor, @Param("status") int status);

	@Modifying
	@Transactional
	@Query("update TPacket set status = 0 where endTime <= :endTime and status in (1,2)")
	public void update(@Param("endTime") int endTime);
	
}
