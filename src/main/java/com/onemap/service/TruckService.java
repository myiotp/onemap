package com.onemap.service;

import java.util.List;

import com.onemap.domain.Truck;

public interface TruckService extends BaseService<Truck, Integer>{
	List<Truck> getByUsername(String username,Truck t) throws Exception;
	List<Truck> query(Truck t) throws Exception;
	int countByUsername(String username) throws Exception;
	void updateStatus(Truck t);
	List<Truck> getByUsernameAndStatus(String username, int status,Truck t) throws Exception;
	List<Truck> getByUsernameAndNonStatus(String username, int status,Truck t) throws Exception;
}
