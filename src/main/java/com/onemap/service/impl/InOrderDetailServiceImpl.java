package com.onemap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.InOrderDetailDao;
import com.onemap.domain.Goods;
import com.onemap.domain.InOrderDetail;
import com.onemap.domain.Truck;
import com.onemap.service.GoodsService;
import com.onemap.service.InOrderDetailService;
import com.onemap.service.TruckService;

@Service
public class InOrderDetailServiceImpl extends BaseServiceImpl<InOrderDetail, Integer>
		implements InOrderDetailService {
	@Autowired
	private InOrderDetailDao inOrderDetailDao;
	@Autowired
	private TruckService truckService;
	@Autowired
	private GoodsService goodsService;
	@Override
	public BaseDao<InOrderDetail, Integer> getDao() {
		return inOrderDetailDao;
	}
	@Override
	protected void fillDetail(InOrderDetail inOrderDetail) throws Exception {
		Truck truck = truckService.get(inOrderDetail.getTruckId());
		inOrderDetail.setTruck(truck);
		Goods goods=goodsService.get(inOrderDetail.getGoodsId());
		inOrderDetail.setGoods(goods);
	}

}
