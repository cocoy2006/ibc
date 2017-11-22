package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.TConfig;

@Repository
public interface ConfigDao extends JpaRepository<TConfig, Integer> {
	
	public TConfig findByKey(String key);
}
