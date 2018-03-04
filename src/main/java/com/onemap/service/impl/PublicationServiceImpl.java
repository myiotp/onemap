package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.PublicationDao;
import com.onemap.domain.Publication;
import com.onemap.domain.OutOrderDetail;
import com.onemap.service.OutOrderDetailService;
import com.onemap.service.PublicationService;

@Service
public class PublicationServiceImpl extends BaseServiceImpl<Publication, Integer>
		implements PublicationService {
	@Autowired
	private PublicationDao dao;
	
	@Override
	public BaseDao<Publication, Integer> getDao() {
		return dao;
	}
	
//	@Override
//	protected void fillDetail(Publication outOrder) throws Exception {
//		OutOrderDetail condition = new OutOrderDetail();
//		condition.setOutOrderId(outOrder.getNumber());
//		List<OutOrderDetail> details = outOrderDetailService.list(condition);
//		
//		outOrder.setDetails(details);
//	}
}
