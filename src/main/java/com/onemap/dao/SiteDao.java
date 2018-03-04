package com.onemap.dao;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Site;

public interface SiteDao extends BaseDao<Site, Integer> {

	//Site getCapacityById(@Param("id")Integer id);

}
