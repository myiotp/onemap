package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.OutOrderDetailDao;
import com.onemap.domain.Area;
import com.onemap.domain.OutOrderDetail;
import com.onemap.service.AreaService;
import com.onemap.service.OutOrderDetailService;

@Service
public class OutOrderDetailServiceImpl extends BaseServiceImpl<OutOrderDetail, Integer>
		implements OutOrderDetailService {
	@Autowired
	private OutOrderDetailDao outOrderDetailDao;
	@Autowired
	private AreaService areaService;

	@Override
	public BaseDao<OutOrderDetail, Integer> getDao() {
		return outOrderDetailDao;
	}
	@Override
	protected void fillDetail(OutOrderDetail outOrderDetail) throws Exception {
		Area area = areaService.get(outOrderDetail.getAreaId());
		outOrderDetail.setArea(area);
		outOrderDetail.setAreaName(area.getName());
		
	}

}
