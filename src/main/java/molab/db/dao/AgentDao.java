package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TAgent;

@Repository
public interface AgentDao extends JpaRepository<TAgent, Integer> {
	
	public TAgent findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
	
	public TAgent findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query("update TAgent set status = :status where id = :id")
	public void update(@Param("id") int id, @Param("status") int status);
	
	@Modifying
	@Transactional
	@Query("update TAgent set amountTotal = :amountTotal where id = :id")
	public void update(@Param("id") int id, @Param("amountTotal") float amountTotal);
	
}
