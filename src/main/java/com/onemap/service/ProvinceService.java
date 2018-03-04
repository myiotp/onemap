package com.onemap.service;

import com.onemap.domain.Province;

public interface ProvinceService extends BaseService<Province, Integer> {
	void updateLngLat(int seqno, double lng, double lat);
}
