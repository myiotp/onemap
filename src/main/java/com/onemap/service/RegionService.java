package com.onemap.service;

import com.onemap.domain.Region;

public interface RegionService extends BaseService<Region, Integer> {

	Region getCapacityById(Integer id) throws Exception;

}
