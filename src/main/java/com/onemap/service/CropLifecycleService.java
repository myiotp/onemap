package com.onemap.service;

import com.onemap.domain.CropLifecycle;


public interface CropLifecycleService extends BaseService<CropLifecycle, Integer> {

//	BlockType getCapacityById(@Param("id")Integer id) throws Exception;
	CropLifecycle getLatest(CropLifecycle t) throws Exception;
}
