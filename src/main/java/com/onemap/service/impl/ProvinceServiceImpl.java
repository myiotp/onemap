/**
 * 
 */
package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.ProvinceDao;
import com.onemap.domain.Province;
import com.onemap.service.ProvinceService;

/**
 * 
 */
@Service
public class ProvinceServiceImpl extends BaseServiceImpl<Province, Integer> implements ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;

	@Override
	public BaseDao<Province, Integer> getDao() {
		return provinceDao;
	}

	@Override
	public void updateLngLat(int seqno, double lng, double lat) {
		this.provinceDao.updateLngLat(seqno, lng, lat);
	}

}
