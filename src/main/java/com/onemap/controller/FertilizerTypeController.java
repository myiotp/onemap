package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.FertilizerType;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.FertilizerTypeService;

@Controller
@RequestMapping("/fertilizertype")
public class FertilizerTypeController extends BaseController<FertilizerType, Integer> {
	@Autowired
	private FertilizerTypeService service;

	@Override
	BaseService<FertilizerType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
