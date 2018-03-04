package com.onemap.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.wink.json4j.JSON;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.City;
import com.onemap.domain.FarmMachinery;
import com.onemap.domain.Landblock;
import com.onemap.domain.Site;
import com.onemap.domain.SiteCert;
import com.onemap.service.CityService;
import com.onemap.service.FarmMachineryService;
import com.onemap.service.SiteCertService;
import com.onemap.service.SiteService;
import com.onemap.utl.common.CityCategory;

@Controller
@RequestMapping("/siteinfo")
public class SiteInfoController {
	@Autowired
	private CityService cityService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private SiteCertService siteCertService;
	@Autowired
	private FarmMachineryService machineryService;

	@RequestMapping("list")
	public ModelAndView list(Landblock b) {
		ModelAndView modelAndView = new ModelAndView("siteinfo/list");

		return modelAndView;
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ModelAndView show(Site t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		Site tInDb = this.siteService.get(t.getId());
		modelAndView.addObject("currentObj", tInDb);

		SiteCert sc = new SiteCert();
		sc.setCooperativeId(t.getId());
		List<SiteCert> tList = this.siteCertService.listByLimit(sc);
		int totalCount = this.siteService.count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
//		addDataForEditView(modelAndView);

		FarmMachinery fm = new FarmMachinery();
		fm.setCooperativeId(t.getId());
		fm.setPageSize(100);
		List<FarmMachinery> tList2 = this.machineryService.listByLimit(fm);
		modelAndView.addObject("resultList2", tList2);
		
		return modelAndView;
	}

	

	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONArray getTreeDataByAll() throws Exception {
		JSONArray array = new JSONArray();

		List<City> cities = cityService.list(null);
		if (cities != null) {
			for (Iterator<City> iterator = cities.iterator(); iterator
					.hasNext();) {
				City city = (City) iterator.next();
				JSONObject province = new JSONObject();
				province.put("id", "city_"+city.getId());
				province.put("parent", "#".equals(city.getParent())?"#":"city_"+city.getParent());
				province.put("text", city.getText());

				array.add(province);
			}
		}

		Site _t = new Site();
		List<Site> sites = this.siteService.list(_t);
		if (sites != null) {
			for (Iterator<Site> siteIterator = sites.iterator(); siteIterator
					.hasNext();) {
				Site site = siteIterator.next();
				JSONObject item = new JSONObject();
				item.put("id", site.getId());
				item.put("_id", site.getCooperativeNumber());
				item.put("parent", "city_"+site.getZipcode());
				item.put("text", site.getCooperativeName());
				item.put("icon", "../js/jstree/themes/tree-icon.png"); // "icon"
																		// :
																		// "tree-icon.png"

				array.add(item);
			}// end for root loop

		}

		JSONArray infoArray = (JSONArray) JSON.parse(CityCategory.data, true);
		array.addAll(infoArray);

		return array;
	}
}
