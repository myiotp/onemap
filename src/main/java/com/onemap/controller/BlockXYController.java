package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.BlockXY;
import com.onemap.service.BaseService;
import com.onemap.service.BlockXYService;

@Controller
@RequestMapping("/landblock/xy")
public class BlockXYController extends BaseController<BlockXY, Integer> {
	@Autowired
	private BlockXYService service;

	@Override
	BaseService<BlockXY, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
