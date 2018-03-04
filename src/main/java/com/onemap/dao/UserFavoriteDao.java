package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.UserFavorite;

public interface UserFavoriteDao extends BaseDao<UserFavorite, Integer> {

	List<UserFavorite> getByUsername(@Param("username") String username) throws Exception;

	int countByUsernameAndVehicle(@Param("username") String username) throws Exception;

	int countByUsernameAndCargo(@Param("username") String username) throws Exception;

	void deleteByUsernameAndVehicle(@Param("username") String username, @Param("vehicleinfoid") int vehicleinfoid)
			throws Exception;

	void deleteByUsernameAndCargo(@Param("username") String username, @Param("cargoinfoid") int cargoinfoid)
			throws Exception;

	List<UserFavorite> getByUsernameAndVehicle(@Param("username") String username,  @Param("vehicleinfoid") int vehicleinfoid);

	List<UserFavorite> getByUsernameAndCargo(@Param("username") String username, @Param("cargoinfoid") int cargoinfoid);

}
