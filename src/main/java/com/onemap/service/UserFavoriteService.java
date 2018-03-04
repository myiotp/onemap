package com.onemap.service;

import java.util.List;

import com.onemap.domain.UserFavorite;

public interface UserFavoriteService extends BaseService<UserFavorite, Integer> {
	List<UserFavorite> getByUsername(String username) throws Exception;

	List<UserFavorite> getByUsernameAndVehicle(String username,int vehicleinfoid) throws Exception;
	List<UserFavorite> getByUsernameAndCargo(String username,int cargoinfoid) throws Exception;
	
	int countByUsernameAndVehicle(String username) throws Exception;

	int countByUsernameAndCargo(String username) throws Exception;

	void deleteByUsernameAndVehicle(String username, int vehicleinfoid) throws Exception;

	void deleteByUsernameAndCargo(String username, int cargoinfoid) throws Exception;
}
