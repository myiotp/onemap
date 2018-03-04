package com.onemap.dao;

import com.onemap.domain.CropLifecycle;

public interface CropLifecycleDao extends BaseDao<CropLifecycle, Integer> {

	//Site getCapacityById(@Param("id")Integer id);
	CropLifecycle getLatest(CropLifecycle t);
}
