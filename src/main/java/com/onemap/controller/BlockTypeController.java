package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.BlockType;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.BlockTypeService;

@Controller
@RequestMapping("/blocktype")
public class BlockTypeController extends BaseController<BlockType, Integer> {
	@Autowired
	private BlockTypeService service;

	@Override
	BaseService<BlockType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	
}
