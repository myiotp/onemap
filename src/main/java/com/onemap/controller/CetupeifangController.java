package com.onemap.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.wink.json4j.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.ManagementRecord;
import com.onemap.service.BaseService;
import com.onemap.service.LandblockService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.SiteService;

@Controller
@RequestMapping("/c")
public class CetupeifangController extends
		BaseController<ManagementRecord, Integer> {
	@Autowired
	private ManagementRecordService service;
	@Autowired
	private LandblockService landblockService;
	@Autowired
	private SiteService siteService;

	@Override
	BaseService<ManagementRecord, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "v", method = RequestMethod.GET)
	public ModelAndView cetupeifang(HttpServletRequest request,
			ManagementRecord t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
//		if (t.getVar8() == null) {
//			return modelAndView;
//		}

//		ManagementRecord tInDb = this.service.getByUrl(t.getVar8());

		String[] template = new String[17];
		template[0] = "<tr id=\"DemoGridJsData_1\"><td>有机质</td><td>OM</td><td class=\"number\">%s</td><td>%%</td><td>%s</td><td></td><td class=\"number\">%s</td></tr>";
		template[1] = "<tr id=\"DemoGridJsData_2\"><td>铵态氮</td><td>NH4-N</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td></td><td class=\"number\">%s</td></tr>";
		template[2] = "<tr id=\"DemoGridJsData_3\"><td>硝态氮</td><td>N03-N</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>氮</td><td class=\"number\">%s</td></tr>";
		template[3] = "<tr id=\"DemoGridJsData_4\"><td>磷</td><td>P</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>磷(P205)</td><td class=\"number\">%s</td></tr>";
		template[4] = "<tr id=\"DemoGridJsData_5\"><td>钾</td><td>K</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>钾(K20)</td><td class=\"number\">%s</td></tr>";
		template[5] = "<tr id=\"DemoGridJsData_6\"><td>钙</td><td>Ca</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>钙(CaC03)</td><td class=\"number\">%s</td></tr>";
		template[6] = "<tr id=\"DemoGridJsData_7\"><td>镁</td><td>Mg</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>镁(MgC03)</td><td class=\"number\">%s</td></tr>";
		template[7] = "<tr id=\"DemoGridJsData_8\"><td>硫</td><td>S</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>硫</td><td class=\"number\">%s</td></tr>";
		template[8] = "<tr id=\"DemoGridJsData_9\"><td>铁</td><td>Fe</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>铁</td><td class=\"number\">%s</td></tr>";
		template[9] = "<tr id=\"DemoGridJsData_10\"><td>铜</td><td>Cu</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>铜</td><td class=\"number\">%s</td></tr>";
		template[10] = "<tr id=\"DemoGridJsData_11\"><td>锰</td><td>Mn</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>锰</td><td class=\"number\">%s</td></tr>";
		template[11] = "<tr id=\"DemoGridJsData_12\"><td>锌</td><td>Zn</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>锌</td><td class=\"number\">%s</td></tr>";
		template[12] = "<tr id=\"DemoGridJsData_13\"><td>硼</td><td>B</td><td class=\"number\">%s</td><td>mg/L</td><td>%s</td><td>硼</td><td class=\"number\">%s</td></tr>";
		template[13] = "<tr id=\"DemoGridJsData_14\"><td>酸碱度</td><td>pH</td><td class=\"number\">%s</td><td></td><td>%s</td><td></td><td class=\"number\">%s</td></tr>";
		template[14] = "<tr id=\"DemoGridJsData_15\"><td>交换性酸</td><td>AA</td><td class=\"number\">%s</td><td>cmol/L</td><td>%s</td><td></td><td class=\"number\">%s</td></tr>";
		template[15] = "<tr id=\"DemoGridJsData_16\"><td>钙镁比</td><td>Ca/Mg</td><td class=\"number\">%s</td><td></td><td>%s</td><td>石灰</td><td class=\"number\">%s</td></tr>";
		template[16] = "<tr id=\"DemoGridJsData_17\"><td>镁钾比</td><td>Mg/K</td><td class=\"number\">%s</td><td></td><td>%s</td><td></td><td class=\"number\">%s</td></tr>";

		// var testvar1={'test':[{'index':0,
		// 'v2':100.01,'v4':'l','v6':11},{'index':1,
		// 'v2':200.02,'v4':'m','v6':22},{'index':2,
		// 'v2':300.03,'v4':'h','v6':333},{'index':3,
		// 'v2':0,'v4':'l','v6':0},{'index':4,
		// 'v2':0,'v4':'l','v6':0},{'index':5,
		// 'v2':0,'v4':'l','v6':0},{'index':6,
		// 'v2':0,'v4':'l','v6':0},{'index':7,
		// 'v2':0,'v4':'l','v6':0},{'index':8,
		// 'v2':0,'v4':'l','v6':0},{'index':9,
		// 'v2':0,'v4':'l','v6':0},{'index':10,
		// 'v2':0,'v4':'l','v6':0},{'index':11,
		// 'v2':0,'v4':'l','v6':0},{'index':12,
		// 'v2':0,'v4':'l','v6':0},{'index':13,
		// 'v2':0,'v4':'l','v6':0},{'index':14,
		// 'v2':0,'v4':'l','v6':0},{'index':15,
		// 'v2':0,'v4':'l','v6':0},{'index':16, 'v2':0,'v4':'m','v6':0}]};
		StringBuffer sb = new StringBuffer();
//		org.apache.wink.json4j.JSONObject json = new org.apache.wink.json4j.JSONObject(
//				tInDb.getVar1());
////		System.out.println(json);
//		if (json != null) {
//			JSONArray testArray = json.getJSONArray("test");
//			if (testArray != null) {
//				for (int i = 0; i < testArray.length(); i++) {
//					org.apache.wink.json4j.JSONObject child = testArray
//							.getJSONObject(i);
//					String v2 = child.getString("v2");
//					String v4 = getLevelString(child.getString("v4"));
//					String v6 = child.getString("v6");
////					System.out.println(i+ template[i]);
//					sb.append(String.format(template[i], v2, v4, v6));
////					System.out.println(i+ sb.toString());
//				}
//			}
//		}
////		System.out.println(t);
//		modelAndView.addObject("t", sb.toString());
//		addDataForEditView(modelAndView);

		return modelAndView;
	}

	private String getLevelString(String s) {
		if (s == null)
			return "";
		else if (s.equals("h"))
			return "高";
		else if (s.equals("m"))
			return "中";
		else
			return "低";
	}
}
