package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.SiteDao;
import com.onemap.domain.Site;
import com.onemap.service.SiteService;
@Service
public class SiteServiceImpl extends BaseServiceImpl<Site, Integer>
		implements SiteService {

	@Autowired
	private SiteDao siteDao;
	@Override
	public BaseDao<Site, Integer> getDao() {
		return siteDao;
	}


	@Override
	public void fillDetail(Site site) throws Exception {
	    if(site==null){
	        return;
	    }
		Site siteCapacity = getCapacityById(site.getId());
		if(siteCapacity==null){
			site.setCommonCapacity(0);
			site.setCoreCapacity(0);
			site.setRelatedCapacity(0);
			return;
		}
		site.setCoreCapacity(siteCapacity.getCoreCapacity()!=null?siteCapacity.getCoreCapacity():0);
		site.setRelatedCapacity(siteCapacity.getRelatedCapacity()!=null?siteCapacity.getRelatedCapacity():0);
		site.setCommonCapacity(siteCapacity.getCommonCapacity()!=null?siteCapacity.getCommonCapacity():0);
	}

	@Override
	public Site getCapacityById(Integer id) throws Exception{
		//return this.siteDao.getCapacityById(id);
		return null;
	}

}
