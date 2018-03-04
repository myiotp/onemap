package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.YieldPredictionDao;
import com.onemap.domain.YieldPrediction;
import com.onemap.domain.YieldPrediction;
import com.onemap.service.YieldPredictionService;
@Service
public class YieldPredictionServiceImpl extends BaseServiceImpl<YieldPrediction, Integer>
		implements YieldPredictionService {

	@Autowired
	private YieldPredictionDao dao;
	@Override
	public BaseDao<YieldPrediction, Integer> getDao() {
		return dao;
	}

	
	@Override
	public void fillDetail(YieldPrediction obj) throws Exception {
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
	public List<YieldPrediction> listByCropType(YieldPrediction t)
			throws Exception {
		return this.dao.listByCropType(t);
	}

//	@Override
//	public Site getCapacityById(Integer id) throws Exception{
//		//return this.siteDao.getCapacityById(id);
//		return null;
//	}

}
