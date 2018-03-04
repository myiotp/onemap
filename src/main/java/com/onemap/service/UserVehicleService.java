package com.onemap.service;

import java.util.List;

import com.onemap.domain.UserVehicle;

public interface UserVehicleService extends BaseService<UserVehicle, Integer> {
	List<UserVehicle> getByUsername(String username) throws Exception;

	List<UserVehicle> getByUsernameAndLicense(String username, String licenseplate) throws Exception;
}
