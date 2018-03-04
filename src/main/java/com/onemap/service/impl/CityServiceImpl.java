/**
 * 
 */
package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.CityDao;
import com.onemap.domain.City;
import com.onemap.service.CityService;

/**
 * @author junpingwang
 * 
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City, Integer> implements
		CityService {
	@Autowired
	private CityDao cityDao;

	@Override
	public BaseDao<City, Integer> getDao() {
		return cityDao;
	}

}
