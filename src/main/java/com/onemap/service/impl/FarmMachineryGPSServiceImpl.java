package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.FarmMachineryGPSDao;
import com.onemap.domain.FarmMachineryGPS;
import com.onemap.service.FarmMachineryGPSService;
import com.onemap.service.SiteService;
@Service
public class FarmMachineryGPSServiceImpl extends BaseServiceImpl<FarmMachineryGPS, Integer>
		implements FarmMachineryGPSService {

	@Autowired
	private FarmMachineryGPSDao dao;
	@Autowired
	private SiteService siteService;
	
	@Override
	public BaseDao<FarmMachineryGPS, Integer> getDao() {
		return dao;
	}

	@Override
	public List<FarmMachineryGPS> listByRecent(FarmMachineryGPS t)
			throws Exception {
		
		return this.dao.listByRecent(t);
	}

	@Override
	public List<FarmMachineryGPS> listBefore1W(FarmMachineryGPS t)
			throws Exception {
		
		return this.dao.listBefore1W(t);
	}

	@Override
	public List<FarmMachineryGPS> listBefore1M(FarmMachineryGPS t)
			throws Exception {
		return this.dao.listBefore1M(t);
	}

	@Override
	public List<FarmMachineryGPS> listBefore6M(FarmMachineryGPS t)
			throws Exception {
		return this.dao.listBefore6M(t);
	}

	
	

}
