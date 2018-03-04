package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.LandType;
import com.onemap.service.BaseService;
import com.onemap.service.LandTypeService;

@Controller
@RequestMapping("/landtype")
public class LandTypeController extends BaseController<LandType, Integer> {
	@Autowired
	private LandTypeService service;

	@Override
	BaseService<LandType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
