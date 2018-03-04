package com.onemap.service;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Site;

public interface SiteService extends BaseService<Site, Integer> {

	Site getCapacityById(@Param("id")Integer id) throws Exception;

}
