package molab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.db.dao.SubIndustryDao;
import molab.db.entity.TSubIndustry;

@Service
public class SubIndustryService {
	
	@Autowired
	private SubIndustryDao sid;
	
	public List<TSubIndustry> findAll(int industryId) {
		return sid.findByIndustryId(industryId);
	}
	
}
