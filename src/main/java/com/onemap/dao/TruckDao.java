package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Truck;

public interface TruckDao extends BaseDao<Truck, Integer> {
	List<Truck> getByUsername(@Param("username") String username);
	List<Truck> query(Truck t);
	int countByUsername(@Param("username") String username) throws Exception;
	void updateStatus(Truck t);
	List<Truck> getByUsernameAndStatus(@Param("username") String username, @Param("status") int status);
	List<Truck> getByUsernameAndNonStatus(@Param("username") String username, @Param("status") int status);
}
