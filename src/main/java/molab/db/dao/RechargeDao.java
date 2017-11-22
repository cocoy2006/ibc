package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.TRecharge;

@Repository
public interface RechargeDao extends JpaRepository<TRecharge, Integer> {

	public List<TRecharge> findByOwnerId(Integer ownerId);
}
