package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Truck;
import com.onemap.service.BaseService;
import com.onemap.service.TruckService;

@Controller
@RequestMapping("/truckInfo")
public class TruckInfoController extends BaseController<Truck, Integer> {
	@Autowired
	private TruckService truckService;

	@Override
	BaseService<Truck, Integer> getBaseService() {
		return this.truckService;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {

	}

}
