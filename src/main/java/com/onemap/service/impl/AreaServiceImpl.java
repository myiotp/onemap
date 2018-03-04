package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.AreaDao;
import com.onemap.dao.BaseDao;
import com.onemap.domain.Area;
import com.onemap.domain.Region;
import com.onemap.service.AreaService;
import com.onemap.service.RegionService;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, Integer> implements
		AreaService {

	@Autowired
	private AreaDao areaDao;
	@Autowired
	private RegionService regionService;

	@Override
	public BaseDao<Area, Integer> getDao() {
		return areaDao;
	}

	@Override
	public void fillDetail(Area area) throws Exception {
		Region region = this.regionService.get(area.getRegionId());
		area.setRegion(region);
	}

}
