package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.CropLifecycleDao;
import com.onemap.domain.CropLifecycle;
import com.onemap.service.CropLifecycleService;
@Service
public class CropLifecycleServiceImpl extends BaseServiceImpl<CropLifecycle, Integer>
		implements CropLifecycleService {

	@Autowired
	private CropLifecycleDao dao;
	@Override
	public BaseDao<CropLifecycle, Integer> getDao() {
		return dao;
	}

	
	@Override
	public void fillDetail(CropLifecycle obj) throws Exception {
//		Date operationTime = obj.getOperationTime();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
//		String date = sdf.format(operationTime.getTime());
		
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


	@Override
	public CropLifecycle getLatest(CropLifecycle t) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.getLatest(t);		
	}

//	@Override
//	public Site getCapacityById(Integer id) throws Exception{
//		//return this.siteDao.getCapacityById(id);
//		return null;
//	}

}
