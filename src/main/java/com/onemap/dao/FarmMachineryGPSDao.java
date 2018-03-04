package com.onemap.dao;

import java.util.List;

import com.onemap.domain.FarmMachineryGPS;

public interface FarmMachineryGPSDao extends BaseDao<FarmMachineryGPS, Integer> {

	List<FarmMachineryGPS> listByRecent(FarmMachineryGPS t);

	List<FarmMachineryGPS> listBefore1W(FarmMachineryGPS t);

	List<FarmMachineryGPS> listBefore1M(FarmMachineryGPS t);

	List<FarmMachineryGPS> listBefore6M(FarmMachineryGPS t);

}
