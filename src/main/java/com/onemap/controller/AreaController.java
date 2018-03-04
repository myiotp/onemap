package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Area;
import com.onemap.service.AreaService;
import com.onemap.service.BaseService;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController<Area, Integer> {
	@Autowired
	private AreaService areaService;

	@Override
	BaseService<Area, Integer> getBaseService() {
		return this.areaService;
	}

	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
	}

}
