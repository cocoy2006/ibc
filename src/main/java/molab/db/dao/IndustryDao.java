package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.TIndustry;

@Repository
public interface IndustryDao extends JpaRepository<TIndustry, Integer> {
	
}
