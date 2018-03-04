package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.SiteCertDao;
import com.onemap.domain.Site;
import com.onemap.domain.SiteCert;
import com.onemap.service.SiteCertService;
@Service
public class SiteCertServiceImpl extends BaseServiceImpl<SiteCert, Integer>
		implements SiteCertService {

	@Autowired
	private SiteCertDao siteCertDao;
	@Override
	public BaseDao<SiteCert, Integer> getDao() {
		return siteCertDao;
	}


	@Override
	public void fillDetail(SiteCert siteCert) throws Exception {
	    if(siteCert==null){
	        return;
	    }
		
	}

	@Override
	public Site getCapacityById(Integer id) throws Exception{
		//return this.siteDao.getCapacityById(id);
		return null;
	}

}
