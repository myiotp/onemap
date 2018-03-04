package com.onemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.LandType;
import com.onemap.service.BaseService;
import com.onemap.service.LandTypeService;

@Controller
@RequestMapping("/api/cartype")
public class CarTypeController extends BaseController<LandType, Integer> {
	@Autowired
	private LandTypeService service;

	@Override
	BaseService<LandType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("")
	@ResponseBody
	public List<Map<String,Object>> listJson(LandType t) throws Exception {
		List<LandType> tList = getBaseService().list(t);
		List<Map<String,Object>> result = new ArrayList<>();
		if(tList != null) {
			for (LandType landType : tList) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("id", "" + landType.getId());
				m.put("name", landType.getLandType());
				result.add(m);
			}
		}
		return result;
	}
}
