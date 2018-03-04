/**
 * 
 */
package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.UserFavoriteDao;
import com.onemap.domain.UserFavorite;
import com.onemap.service.UserFavoriteService;

/**
 * @author junpingwang
 * 
 */
@Service
public class UserFavoriteServiceImpl extends BaseServiceImpl<UserFavorite, Integer> implements UserFavoriteService  {
	@Autowired
	private UserFavoriteDao dao;

	@Override
	public List<UserFavorite> getByUsername(String username)
			throws Exception {
		return dao.getByUsername(username);
	}

	@Override
	public BaseDao<UserFavorite, Integer> getDao() {
		return dao;
	}

	@Override
	public int countByUsernameAndVehicle(String username) throws Exception {
		return dao.countByUsernameAndVehicle(username);
	}

	@Override
	public int countByUsernameAndCargo(String username) throws Exception {
		return dao.countByUsernameAndCargo(username);
	}

	@Override
	public void deleteByUsernameAndVehicle(String username, int vehicleinfoid) throws Exception {
		dao.deleteByUsernameAndVehicle(username, vehicleinfoid);
	}

	@Override
	public void deleteByUsernameAndCargo(String username, int cargoinfoid) throws Exception {
		dao.deleteByUsernameAndCargo(username, cargoinfoid);
	}

	@Override
	public List<UserFavorite> getByUsernameAndVehicle(String username, int vehicleinfoid) throws Exception {
		return dao.getByUsernameAndVehicle(username, vehicleinfoid);
	}

	@Override
	public List<UserFavorite> getByUsernameAndCargo(String username, int cargoinfoid) throws Exception {
		return dao.getByUsernameAndCargo(username, cargoinfoid);
	}


}
