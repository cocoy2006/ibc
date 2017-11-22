package molab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.db.dao.ConfigDao;
import molab.db.entity.TConfig;

@Service
public class ConfigService {
	
	@Autowired
	private ConfigDao cd;
	
	public TConfig findByKey(String key) {
		return cd.findByKey(key);
	} 
	
}
