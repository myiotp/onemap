package com.onemap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.MachineryXY;
import com.onemap.domain.MachineryXY2;
import com.onemap.service.BaseService;
import com.onemap.service.MachineryXYService;

@Controller
@RequestMapping("/api/machinery/xy")
public class MachineryXYController extends BaseController<MachineryXY, Integer> {
	@Autowired
	private MachineryXYService service;

	@Override
	BaseService<MachineryXY, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "txid/{txid}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsername(@PathVariable("txid") Integer txid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			MachineryXY t = new MachineryXY();
			t.setMachineryOperationId(txid);
			List<MachineryXY> records = this.service.list(t);
			List<MachineryXY2> data = new ArrayList<>();
			if(records != null) {
				for (MachineryXY machineryXY : records) {
					MachineryXY2 e = new MachineryXY2();
					e.setLongitude(machineryXY.getPositionX());
					e.setLatitude(machineryXY.getPositionY());
					data.add(e );
				}
			}
			result.setData(data);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
}
