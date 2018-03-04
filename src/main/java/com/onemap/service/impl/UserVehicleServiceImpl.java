/**
 * 
 */
package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.UserVehicleDao;
import com.onemap.domain.UserVehicle;
import com.onemap.service.UserVehicleService;

/**
 * @author junpingwang
 * 
 */
@Service
public class UserVehicleServiceImpl extends BaseServiceImpl<UserVehicle, Integer> implements UserVehicleService  {
	@Autowired
	private UserVehicleDao dao;

	@Override
	public List<UserVehicle> getByUsername(String username)
			throws Exception {
		return dao.getByUsername(username);
	}

	@Override
	public BaseDao<UserVehicle, Integer> getDao() {
		return dao;
	}

	@Override
	public List<UserVehicle> getByUsernameAndLicense(String username, String licenseplate) throws Exception {
		return dao.getByUsernameAndLicense(username, licenseplate);
	}


}
