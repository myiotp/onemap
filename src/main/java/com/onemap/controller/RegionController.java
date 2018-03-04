package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Region;
import com.onemap.service.BaseService;
import com.onemap.service.RegionService;

@Controller
@RequestMapping("/region")
public class RegionController extends BaseController<Region, Integer> {
	@Autowired
	private RegionService regionService;

	@Override
	BaseService<Region, Integer> getBaseService() {
		return this.regionService;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		
	}

}
