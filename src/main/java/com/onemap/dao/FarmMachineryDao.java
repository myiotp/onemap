package com.onemap.dao;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.FarmMachinery;

public interface FarmMachineryDao extends BaseDao<FarmMachinery, Integer> {

	public FarmMachinery getLicensenumberByGps(@Param("gpsdevice")String gpsdevice);

}
