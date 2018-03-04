package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.CropType;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.CropTypeService;

@Controller
@RequestMapping("/croptype")
public class CropTypeController extends BaseController<CropType, Integer> {
	@Autowired
	private CropTypeService service;

	@Override
	BaseService<CropType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
