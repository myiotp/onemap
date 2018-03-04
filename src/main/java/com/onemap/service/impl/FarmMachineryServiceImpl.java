package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.FarmMachineryDao;
import com.onemap.domain.FarmMachinery;
import com.onemap.domain.Site;
import com.onemap.service.FarmMachineryService;
import com.onemap.service.SiteService;
@Service
public class FarmMachineryServiceImpl extends BaseServiceImpl<FarmMachinery, Integer>
		implements FarmMachineryService {

	@Autowired
	private FarmMachineryDao dao;
	@Autowired
	private SiteService siteService;
	
	@Override
	public BaseDao<FarmMachinery, Integer> getDao() {
		return dao;
	}

	@Override
	public void save(FarmMachinery t) throws Exception {

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
	public void fillDetail(FarmMachinery t) throws Exception {
		if (t == null) {
			return;
		}
		
		Site site = this.siteService.get(t.getCooperativeId());

		if (site != null) {
			t.setCooperative(site);
		}
	}

	@Override
	public FarmMachinery getLicensenumberByGps(String gpsdevice)
			throws Exception {		
		return this.dao.getLicensenumberByGps(gpsdevice);
	}

//	@Override
//	public FarmMachinery getCapacityById(Integer id) throws Exception{
//		return warehouseDao.getCapacityById(id);
//	}

}
