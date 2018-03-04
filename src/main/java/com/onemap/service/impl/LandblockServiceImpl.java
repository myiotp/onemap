package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.LandblockDao;
import com.onemap.domain.Landblock;
import com.onemap.domain.Site;
import com.onemap.service.LandblockService;
import com.onemap.service.SiteService;

@Service
public class LandblockServiceImpl extends BaseServiceImpl<Landblock, Integer>
		implements LandblockService {

	@Autowired
	private LandblockDao dao;
	@Autowired
	private SiteService siteService;

	@Override
	public BaseDao<Landblock, Integer> getDao() {
		return dao;
	}

	@Override
	public void save(Landblock t) throws Exception {

		if (t == null) {
			return;
		}

		Site site = this.siteService.get(t.getCooperativeId());
		if (site != null) {
			t.setZipcode(site.getZipcode());
		}

		this.getDao().save(t);
	}

	@Override
	public void fillDetail(Landblock t) throws Exception {
		if (t == null) {
			return;
		}
		
		Site site = this.siteService.get(t.getCooperativeId());

		if (site != null) {
			t.setCooperative(site);
		}

		// Landblock warehouseCapacity = getCapacityById(warehouse.getId());
		// if(warehouseCapacity==null){
		// warehouse.setCommonCapacity(0);
		// warehouse.setCoreCapacity(0);
		// warehouse.setRelatedCapacity(0);
		// return;
		// }
		// warehouse.setCoreCapacity(warehouseCapacity.getCoreCapacity()!=null?warehouseCapacity.getCoreCapacity():0);
		// warehouse.setRelatedCapacity(warehouseCapacity.getRelatedCapacity()!=null?warehouseCapacity.getRelatedCapacity():0);
		// warehouse.setCommonCapacity(warehouseCapacity.getCommonCapacity()!=null?warehouseCapacity.getCommonCapacity():0);
	}

	// @Override
	// public Landblock getCapacityById(Integer id) throws Exception{
	// return warehouseDao.getCapacityById(id);
	// }

}
