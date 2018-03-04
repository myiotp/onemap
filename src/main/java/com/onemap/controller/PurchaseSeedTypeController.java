package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.Configuration;
import com.onemap.domain.PurchaseSeedType;
import com.onemap.domain.SeedType;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.PurchaseSeedTypeService;
import com.onemap.service.SeedTypeService;
import com.onemap.service.UserVehicleService;

@Controller
@RequestMapping("/purchaseseedtype")
public class PurchaseSeedTypeController extends BaseController<UserVehicle, Integer> {
	@Autowired
	private UserVehicleService service;

	@Override
	BaseService<UserVehicle, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public UserVehicle getJSONObject(@PathVariable("id") Integer id) throws Exception {
		UserVehicle tInDb = this.getBaseService().get(id);
		// if (tInDb != null) {
		// SeedType type = typeService.get(tInDb.getSeedTypeId());
		// tInDb.setSeedType(type.getSeedType());
		// }
		if (tInDb != null) {
			String certimage = tInDb.getCertimage();
			tInDb.setCertimage(Configuration.getImage_url() + "/vehicle/" + certimage);
		}
		return tInDb;
	}

	@RequestMapping("approve")
	public String approve(UserVehicle t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			UserVehicle userVehicle = service.get(t.getId());
			userVehicle.setComment(t.getComment());
			userVehicle.setAuthresult(1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}

	@RequestMapping("reject")
	public String reject(UserVehicle t, @RequestParam("id2") Integer myid, @RequestParam("comment2") String comment)
			throws Exception {
		if (myid == null) {
			//
		} else {
			UserVehicle userVehicle = service.get(myid);
			userVehicle.setComment(comment);
			userVehicle.setAuthresult(-1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}
}
