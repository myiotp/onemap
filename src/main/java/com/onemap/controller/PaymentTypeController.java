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
import com.onemap.domain.SeedType;
import com.onemap.service.BaseService;
import com.onemap.service.LandTypeService;
import com.onemap.service.SeedTypeService;

@Controller
@RequestMapping("/api/payment")
public class PaymentTypeController extends BaseController<SeedType, Integer> {
	@Autowired
	private SeedTypeService service;

	@Override
	BaseService<SeedType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("")
	@ResponseBody
	public List<Map<String,Object>> listJson(SeedType t) throws Exception {
		List<SeedType> tList = getBaseService().list(t);
		List<Map<String,Object>> result = new ArrayList<>();
		if(tList != null) {
			for (SeedType item : tList) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("id", "" + item.getId());
				m.put("name", item.getSeedType());
				result.add(m);
			}
		}
		return result;
	}
}
