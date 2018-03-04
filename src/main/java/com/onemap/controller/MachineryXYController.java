package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.MachineryXY;
import com.onemap.service.BaseService;
import com.onemap.service.MachineryXYService;

@Controller
@RequestMapping("/machinery/xy")
public class MachineryXYController extends BaseController<MachineryXY, Integer> {
	@Autowired
	private MachineryXYService service;

	@Override
	BaseService<MachineryXY, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
