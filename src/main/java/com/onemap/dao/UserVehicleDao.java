package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.UserVehicle;

public interface UserVehicleDao extends BaseDao<UserVehicle, Integer>{

	List<UserVehicle> getByUsername(@Param("username") String username) throws Exception;
	List<UserVehicle> getByUsernameAndLicense(@Param("username") String username, @Param("licenseplate") String licenseplate) throws Exception;
}
