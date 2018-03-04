package com.onemap.dao;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Landblock;

public interface LandblockDao extends BaseDao<Landblock, Integer> {

	Landblock getCapacityById(@Param("id")Integer id);

}
