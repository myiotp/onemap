package com.onemap.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.Landblock;
import com.onemap.domain.Publication;
import com.onemap.domain.Site;
import com.onemap.domain.SiteCert;
import com.onemap.service.BaseService;
import com.onemap.service.SiteCertService;
import com.onemap.service.SiteService;

@Controller
@RequestMapping("/sitecert")
public class SiteCertController extends BaseController<SiteCert, Integer> {
	@Autowired
	private SiteCertService siteCertService;

	@Autowired
	private SiteService siteService;

	@Override
	BaseService<SiteCert, Integer> getBaseService() {
		return this.siteCertService;
	}

	@Override
    public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "editBySite",  method = RequestMethod.GET)
	public ModelAndView addOrUpdate(SiteCert t, @RequestParam("cooperativeId") Integer cooperativeId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(t);
		System.out.println(cooperativeId);
		if (t.getId() == null) {
			return modelAndView;
		}
		SiteCert tInDb = this.getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping("editlistBySite")	
	public ModelAndView editlist(SiteCert t, @RequestParam("id") Integer cooperativeId) throws Exception {
		List<SiteCert> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if(t == null){
			t = new SiteCert();
		}
		t.setCooperativeId(cooperativeId);
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage", (int)Math.ceil((double)totalCount/t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("save")
	public String save(SiteCert t) throws Exception {
		if (t.getId() == null) {
			this.getBaseService().save(t);
		} else {
			this.getBaseService().update(t);
		}
		return "redirect:./editlistBySite?id="+t.getCooperativeId();
	}
	@RequestMapping("delete")
	public String delete(SiteCert t) throws Exception {
		this.getBaseService().delete(t);

		return "redirect:./editlistBySite?id="+t.getCooperativeId();
	}
	
	@RequestMapping("id/{id}")
	@ResponseBody
	public SiteCert getCertJson(@PathVariable("id") Integer id) throws Exception {
		SiteCert tInDb = this.getBaseService().get(id);
		return tInDb;
	}
//	@RequestMapping(value = "editli")
//	public ModelAndView editlist(Landblock t,
//			@RequestParam("id") Integer cooperativeId) throws Exception {
//		List<Landblock> tList = null;
//		ModelAndView modelAndView = new ModelAndView();
//		System.out.println("cooperativeId:" + cooperativeId);
//		t = new Landblock();
//		t.setCooperativeId(cooperativeId);
//		tList = this.landblockService.listByLimit(t);
//		int totalCount = this.landblockService.count(t);
//		modelAndView.addObject("totalCount", totalCount);
//		modelAndView.addObject("totalPage",
//				(int) Math.ceil((double) totalCount / t.getPageSize()));
//		modelAndView.addObject("resultList", tList);
//		return modelAndView;
//	}
}
