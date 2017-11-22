package molab.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.TSubIndustry;

@Repository
public interface SubIndustryDao extends JpaRepository<TSubIndustry, Integer> {
	
	public List<TSubIndustry> findByIndustryId(Integer industryId);
}
