package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TUser;

@Repository
public interface UserDao extends JpaRepository<TUser, Integer> {

	public TUser findByOpenid(String openid);
	
	@Modifying
	@Transactional
	@Query("update TUser set amount = :amount where id = :id")
	public void update(@Param("amount") float amount, @Param("id") int id);
	
}
