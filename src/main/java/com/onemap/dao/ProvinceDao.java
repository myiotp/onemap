package com.onemap.dao;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Province;

public interface ProvinceDao extends BaseDao<Province, Integer> {
	void updateLngLat(@Param("seqno") int seqno, @Param("lng") double lng, @Param("lat") double lat);
}
