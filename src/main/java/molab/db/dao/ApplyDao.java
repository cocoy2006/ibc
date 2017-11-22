package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import molab.db.entity.TApply;

@Repository
public interface ApplyDao extends JpaRepository<TApply, Integer> {
	
	List<TApply> findByAuditStatusAndStatus(Integer auditStatus, Integer status);
	
	@Modifying
	@Transactional
	@Query("update TApply set applyId = :applyId, auditStatus = :auditStatus, auditComment = :auditComment, status = :status where id = :id")
	public void update(@Param("applyId") int applyId, @Param("auditStatus") int auditStatus, 
			@Param("auditComment") String auditComment, @Param("status") int status, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update TApply set auditStatus = :auditStatus, auditComment = :auditComment where id = :id")
	public void update(@Param("auditStatus") int auditStatus, @Param("auditComment") String auditComment, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update TApply set errcode = :errcode, errmsg = :errmsg, status = :status where id = :id")
	public void update(@Param("errcode") int errcode, @Param("errmsg") String errmsg, 
			@Param("status") int status, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update TApply set status = :status where id = :id")
	public void update(@Param("status") int status, @Param("id") int id);
	
}
