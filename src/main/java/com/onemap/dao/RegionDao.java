package com.onemap.dao;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Region;

public interface RegionDao extends BaseDao<Region, Integer> {

	Region getCapacityById(@Param("id")Integer id);

}
