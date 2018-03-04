package com.onemap.service;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Site;
import com.onemap.domain.SiteCert;

public interface SiteCertService extends BaseService<SiteCert, Integer> {

	Site getCapacityById(@Param("id")Integer id) throws Exception;

}
