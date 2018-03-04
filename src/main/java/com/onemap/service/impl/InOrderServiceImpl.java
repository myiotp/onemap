package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.InOrderDao;
import com.onemap.domain.InOrder;
import com.onemap.service.InOrderService;

@Service
public class InOrderServiceImpl extends BaseServiceImpl<InOrder, Integer>
		implements InOrderService {
	@Autowired
	private InOrderDao inOrderDao;
	@Override
	public BaseDao<InOrder, Integer> getDao() {
		return inOrderDao;
	}

}
