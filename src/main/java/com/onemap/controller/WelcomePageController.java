package com.onemap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Landblock;

@Controller
@RequestMapping("/welcomePage")
public class WelcomePageController {

	@RequestMapping("view")
	public ModelAndView list(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/view");
		
		return modelAndView;
	}
	
	@RequestMapping("newview")
	public ModelAndView newview(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/newview");
		
		return modelAndView;
	}
	
	@RequestMapping("view2")
	public ModelAndView view2(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/view2");
		
		return modelAndView;
	}
	
	@RequestMapping("vie")
	public ModelAndView vie(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/vie");
		
		return modelAndView;
	}
	
	@RequestMapping("yaogan")
	public ModelAndView yaogan(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/yaogan");
		
		return modelAndView;
	}
	@RequestMapping("nongjifenbu")
	public ModelAndView nongjifenbu(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("welcomePage/nongjifenbu");
		
		return modelAndView;
	}
	@RequestMapping("zuowufenbu")
	public ModelAndView zuowufenbu() {
		ModelAndView modelAndView = new ModelAndView("welcomePage/zuowufenbu");
		
		return modelAndView;
	}
}
