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

import com.onemap.domain.CropType;
import com.onemap.domain.LandType;
import com.onemap.service.BaseService;
import com.onemap.service.CropTypeService;

@Controller
@RequestMapping("/api/carlength")
public class CarLengthController extends BaseController<CropType, Integer> {
	@Autowired
	private CropTypeService service;

	@Override
	BaseService<CropType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("")
	@ResponseBody
	public List<Map<String,Object>> listJson(CropType t) throws Exception {
		List<CropType> tList = getBaseService().list(t);
		List<Map<String,Object>> result = new ArrayList<>();
		if(tList != null) {
			for (CropType a : tList) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("id", "" + a.getId());
				m.put("name", a.getCropType());
				result.add(m);
			}
		}
		return result;
	}
}
