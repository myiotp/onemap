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
import org.springframework.web.bind.annotation.ResponseBody;

import com.onemap.domain.City;
import com.onemap.domain.Site;
import com.onemap.service.CityService;
import com.onemap.service.SiteService;

@Controller
@RequestMapping("/city")
public class CityInfoController {
	@Autowired
	private CityService cityService;
	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "{parent}/cities", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Object listChildren(City t, @PathVariable("parent") String parent) throws Exception {
		if(t == null){
			t = new City();
		}
		t.setParent(parent);
		List<City> tList = this. cityService.list(t);

		return tList;
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET, produces = "application/json")
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

		return array;
	}
	
	@RequestMapping(value = "{id}/sites", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Object listSites(Site t, @PathVariable("id") String id) throws Exception {
		if(t == null){
			t = new Site();
		}
		t.setZipcode(id);
		List<Site> tList = this.siteService.list(t);

		return tList;
	}
}
