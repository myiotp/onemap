package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.CropTypeDao;
import com.onemap.domain.CropType;
import com.onemap.domain.CropType;
import com.onemap.service.CropTypeService;
@Service
public class CropTypeServiceImpl extends BaseServiceImpl<CropType, Integer>
		implements CropTypeService {

	@Autowired
	private CropTypeDao dao;
	@Override
	public BaseDao<CropType, Integer> getDao() {
		return dao;
	}

	
	@Override
	public void fillDetail(CropType obj) throws Exception {
//		Site siteCapacity = getCapacityById(site.getId());
//		if(siteCapacity==null){
//			site.setCommonCapacity(0);
//			site.setCoreCapacity(0);
//			site.setRelatedCapacity(0);
//			return;
//		}
//		site.setCoreCapacity(siteCapacity.getCoreCapacity()!=null?siteCapacity.getCoreCapacity():0);
//		site.setRelatedCapacity(siteCapacity.getRelatedCapacity()!=null?siteCapacity.getRelatedCapacity():0);
//		site.setCommonCapacity(siteCapacity.getCommonCapacity()!=null?siteCapacity.getCommonCapacity():0);
	}

//	@Override
//	public Site getCapacityById(Integer id) throws Exception{
//		//return this.siteDao.getCapacityById(id);
//		return null;
//	}

}
