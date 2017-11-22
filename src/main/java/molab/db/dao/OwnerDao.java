package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TOwner;

@Repository
public interface OwnerDao extends JpaRepository<TOwner, Integer> {

	public TOwner findByUsernameAndPasswordAndStatus(String username, String password, Integer status);

	public TOwner findByUsername(String username);

	public List<TOwner> findByAgentId(Integer agentId);
	
	@Modifying
	@Transactional
	@Query("update TOwner set status = :status where id = :id")
	public void update(@Param("id") int id, @Param("status") int status);
	
	@Modifying
	@Transactional
	@Query("update TOwner set amountTotal = :amountTotal where id = :id")
	public void update(@Param("id") int id, @Param("amountTotal") float amountTotal);

}
