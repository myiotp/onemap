package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.PesticideType;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.PesticideTypeService;

@Controller
@RequestMapping("/pesticidetype")
public class PesticideTypeController extends BaseController<PesticideType, Integer> {
	@Autowired
	private PesticideTypeService service;

	@Override
	BaseService<PesticideType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
