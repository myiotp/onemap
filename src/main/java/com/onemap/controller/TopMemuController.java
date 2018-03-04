package com.onemap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/topmemu")
public class TopMemuController{
	@RequestMapping("list")
	public ModelAndView list(@RequestParam("memuid") Integer memuid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("memuid", memuid);
		return modelAndView;
	}
	
}
