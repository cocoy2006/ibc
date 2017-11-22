package molab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import molab.db.dao.AdminDao;
import molab.db.entity.TAdmin;
import molab.util.Status;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao ad;
	
	public TAdmin signin(String username, String password) {
		return ad.findByUsernameAndPasswordAndStatus(username, password, Status.Common.YES.getInt());
	}
	
}
