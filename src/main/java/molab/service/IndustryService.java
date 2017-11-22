package molab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.db.dao.IndustryDao;
import molab.db.entity.TIndustry;

@Service
public class IndustryService {
	
	@Autowired
	private IndustryDao id;
	
	public List<TIndustry> findAll() {
		return id.findAll();
	}
	
}
