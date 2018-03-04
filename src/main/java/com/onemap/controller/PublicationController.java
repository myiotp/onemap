package com.onemap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Publication;
import com.onemap.service.BaseService;
import com.onemap.service.PublicationService;

@Controller
@RequestMapping("/publication")
public class PublicationController extends BaseController<Publication, Integer> {

	@Autowired
	private PublicationService service;
	
	@Override
	BaseService<Publication, Integer> getBaseService() {
		return service;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {

	}
	
	@RequestMapping(value = "view",  method = RequestMethod.GET)
	public ModelAndView view(Publication t) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
//		System.out.println(t);
//		System.out.println(modelAndView);
		if (t.getId() == null) {
			return modelAndView;
		}
		Publication tInDb = this.getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping("views")
	public ModelAndView views(Publication t) throws Exception {
		List<Publication> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage", (int)Math.ceil((double)totalCount/t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("id/{id}")
	@ResponseBody
	public Object getPublicationJson(@PathVariable("id") Integer id) throws Exception {
		Publication tInDb = null;
		tInDb = this.getBaseService().get(id);
		return tInDb;
	}
}
