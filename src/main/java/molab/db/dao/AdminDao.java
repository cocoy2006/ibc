package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TAdmin;

@Repository
public interface AdminDao extends JpaRepository<TAdmin, Integer> {
	
	public TAdmin findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
	
	@Modifying
	@Transactional
	@Query("update TAdmin set amountTotal = :amountTotal where id = :id")
	public void update(@Param("id") int id, @Param("amountTotal") float amountTotal);
	
}
