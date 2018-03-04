package com.onemap.service;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.FarmMachinery;

public interface FarmMachineryService extends
		BaseService<FarmMachinery, Integer> {

	public FarmMachinery getLicensenumberByGps(@Param("gpsdevice") String gpsdevice)
			throws Exception;

	public void fillDetail(FarmMachinery t) throws Exception;
}
