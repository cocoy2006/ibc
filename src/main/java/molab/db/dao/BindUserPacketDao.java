package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.BindUserPacket;

@Repository
public interface BindUserPacketDao extends JpaRepository<BindUserPacket, Integer> {

	public BindUserPacket findByUserIdAndPacketId(Integer userId, Integer packetId);
	
	public List<BindUserPacket> findByPacketId(Integer packetId);
	
}
