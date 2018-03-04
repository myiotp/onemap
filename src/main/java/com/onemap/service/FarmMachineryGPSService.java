package com.onemap.service;

import java.util.List;

import com.onemap.domain.FarmMachineryGPS;

public interface FarmMachineryGPSService extends BaseService<FarmMachineryGPS, Integer> {

	List<FarmMachineryGPS> listByRecent(FarmMachineryGPS t) throws Exception;
	List<FarmMachineryGPS> listBefore1W(FarmMachineryGPS t) throws Exception;
	List<FarmMachineryGPS> listBefore1M(FarmMachineryGPS t) throws Exception;
	List<FarmMachineryGPS> listBefore6M(FarmMachineryGPS t) throws Exception;
}
