package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.RegionDao;
import com.onemap.domain.Region;
import com.onemap.domain.Landblock;
import com.onemap.service.RegionService;
import com.onemap.service.SiteService;
import com.onemap.service.LandblockService;

@Service
public class RegionServiceImpl extends BaseServiceImpl<Region, Integer>
		implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private LandblockService  warehouseService;
	@Override
	public BaseDao<Region, Integer> getDao() {
		return regionDao;
	}

	@Override
	public void fillDetail(Region region) throws Exception {
		if(region == null)
			return;
		Landblock warehouse = this.warehouseService.get(region.getWarehouseId());
		region.setWarehouse(warehouse);
		Region regionCapacity = getCapacityById(region.getId());
		if (regionCapacity == null) {
			region.setCommonCapacity(0);
			region.setCoreCapacity(0);
			region.setRelatedCapacity(0);
			return;
		}
		region.setCoreCapacity(regionCapacity.getCoreCapacity() != null ? regionCapacity
				.getCoreCapacity() : 0);
		region.setRelatedCapacity(regionCapacity.getRelatedCapacity() != null ? regionCapacity
				.getRelatedCapacity() : 0);
		region.setCommonCapacity(regionCapacity.getCommonCapacity() != null ? regionCapacity
				.getCommonCapacity() : 0);
	}

	@Override
	public Region getCapacityById(Integer id) throws Exception {
		return this.regionDao.getCapacityById(id);
	}
}
