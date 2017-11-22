package molab.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import molab.db.entity.BindDeviceOwner;

@Repository
public interface BindDeviceOwnerDao extends JpaRepository<BindDeviceOwner, Integer> {
	
	public BindDeviceOwner findByDeviceIdAndStatus(Integer deviceId, Integer status);
	
}
