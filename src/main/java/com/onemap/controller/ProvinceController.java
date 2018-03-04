package com.onemap.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Province;
import com.onemap.domain.Province2;
import com.onemap.domain.Province2City;
import com.onemap.service.BaseService;
import com.onemap.service.ProvinceService;
import com.onemap.utl.common.CalculateDisUtil;

@Controller
@RequestMapping("/api/province")
public class ProvinceController extends BaseController<Province, Integer> {
	public final static Map<String, List<Province2>> localcache = new ConcurrentHashMap<>();
	public final static String KEY_ALL_CITIES = "allcities";

	@Autowired
	private ProvinceService provinceService;

	@Override
	BaseService<Province, Integer> getBaseService() {
		return this.provinceService;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {

	}

	@RequestMapping("cities")
	@ResponseBody
	public APIResponseBaseObject listAllCitiesJson() throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Province2> allCities = localcache.get(KEY_ALL_CITIES);
		if (allCities == null) {
			Province t = new Province();
			t.setParent("0");
			List<Province> tList = getBaseService().list(t);

			List<Province2> data = new ArrayList<Province2>();
			for (Province province : tList) {
				Province2 p = new Province2();
				p.setProvince_id(province.getProvince_id());
				p.setProvince_name(province.getProvince_name());
				p.setProvince_simple(province.getProvince_simple());

				Map<String, Province2City> cityList = new HashMap<>();
				Province t2 = new Province();
				t2.setParent(province.getProvince_id());
				List<Province> citites = getBaseService().list(t2);
				for (Province province2 : citites) {
					Province2City p2c = new Province2City();
					p2c.setName(province2.getProvince_name());
					Map<String, String> list = new HashMap<>();
					Province t3 = new Province();
					t3.setParent(province2.getProvince_id());
					List<Province> subcitites = getBaseService().list(t3);
					for (Province province3 : subcitites) {
						list.put(province3.getProvince_id(), province3.getProvince_name());
					}

					p2c.setList(list);
					cityList.put(province2.getProvince_id(), p2c);
				}
				p.setCityList(cityList);
				data.add(p);
			}
			
			localcache.put(KEY_ALL_CITIES, data);
			result.setData(data);
		} else {
			result.setData(allCities);
		}
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping("cities/tree/city/{cityId}")
	@ResponseBody
	public APIResponseBaseObject listAllCitieIdsTreeJson(@PathVariable("cityId") String cityId) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Province t3 = new Province();
		t3.setParent(cityId);
		List<Province> subcitites = getBaseService().list(t3);
		JSONArray array = new JSONArray();
		for (Province province2 : subcitites) {
			JSONObject p = new JSONObject();
			p.put("id", "city_" + province2.getProvince_id());
			p.put("parent", "0".equals(province2.getParent()) ? "#" : "city_" + province2.getParent());
			p.put("text", province2.getProvince_name());
			p.put("gpsx", province2.getLng());
			p.put("gpsy", province2.getLat());
			array.put(p);
		}
		result.setData(array);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping("cities/tree")
	@ResponseBody
	public APIResponseBaseObject listAllCitiesTreeJson() throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Province t = new Province();
		t.setParent("0");
		List<Province> tList = getBaseService().list(t);

		JSONArray array = new JSONArray();
		// List<Province2> data = new ArrayList<Province2>();
		for (Province province : tList) {
			// Province2 p = new Province2();
			// p.setProvince_id(province.getProvince_id());
			// p.setProvince_name(province.getProvince_name());
			// p.setProvince_simple(province.getProvince_simple());

			Map<String, Province2City> cityList = new HashMap<>();
			Province t2 = new Province();
			t2.setParent(province.getProvince_id());
			List<Province> citites = getBaseService().list(t2);
			for (Province province2 : citites) {
				// Province2City p2c = new Province2City();
				// p2c.setName(province2.getProvince_name());
				// Map<String, String> list = new HashMap<>();
				// Province t3 = new Province();
				// t3.setParent(province2.getProvince_id());
				// List<Province> subcitites = getBaseService().list(t3);
				// for (Province province3 : subcitites) {
				// list.put(province3.getProvince_id(), province3.getProvince_name());
				// }
				//
				// p2c.setList(list);
				// cityList.put(province2.getProvince_id(), p2c);

				JSONObject p = new JSONObject();
				p.put("id", "city_" + province2.getProvince_id());
				p.put("parent", "0".equals(province2.getParent()) ? "#" : "city_" + province2.getParent());
				p.put("text", province2.getProvince_name());
				array.put(p);
			}
			// p.setCityList(cityList);

			JSONObject p = new JSONObject();
			p.put("id", "city_" + province.getProvince_id());
			p.put("parent", "0".equals(province.getParent()) ? "#" : "city_" + province.getParent());
			p.put("text", province.getProvince_name());
			array.put(p);
		}
		result.setData(array);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping("parent/{parent}")
	@ResponseBody
	public APIResponseBaseObject listJson(@PathVariable("parent") String parent) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Province t = new Province();
		t.setParent(parent);
		List<Province> tList = getBaseService().list(t);
		System.out.println(tList);
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping("populategeolocation")
	@ResponseBody
	public APIResponseBaseObject populategeolocation(@RequestParam(value = "parent", required = false) String parent)
			throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Province t = new Province();
		t.setParent("0");
		List<Province> tList = getBaseService().list(t);

		List<Province2> data = new ArrayList<Province2>();
		Map<String, String> list = new HashMap<>();
		for (Province province : tList) {
			// province 省1级
			if ("123".equals(parent) && (province.getLat() == 0 && province.getLng() == 0)) {
				double[] lngLat = CalculateDisUtil.getLngLat(province.getProvince_name());
				provinceService.updateLngLat(province.getSeqno(), lngLat[0], lngLat[1]);
				Thread.sleep(200);
			}

			// Province2 p = new Province2();
			// p.setProvince_id(province.getProvince_id());
			// p.setProvince_name(province.getProvince_name());
			// p.setProvince_simple(province.getProvince_simple());

			Map<String, Province2City> cityList = new HashMap<>();
			Province t2 = new Province();
			t2.setParent(province.getProvince_id());
			List<Province> citites = getBaseService().list(t2);
			for (Province province2 : citites) {
				if ("1234".equals(parent) && (province2.getLat() == 0 && province2.getLng() == 0)) {
					double[] lngLat = CalculateDisUtil.getLngLat(province2.getProvince_name());
					provinceService.updateLngLat(province2.getSeqno(), lngLat[0], lngLat[1]);
					Thread.sleep(200);
				}
				Province2City p2c = new Province2City();
				p2c.setName(province2.getProvince_name());

				Province t3 = new Province();
				t3.setParent(province2.getProvince_id());
				List<Province> subcitites = getBaseService().list(t3);
				for (Province province3 : subcitites) {
					if (province3.getLng() > 0 && province3.getLat() > 0) {
						continue;
					} else {
						if ("12345".equals(parent) && (province3.getLat() == 0 && province3.getLng() == 0)) {
							double[] lngLat = CalculateDisUtil.getLngLat(province3.getProvince_name());
							provinceService.updateLngLat(province3.getSeqno(), lngLat[0], lngLat[1]);
							list.put(province3.getProvince_id(), province3.getProvince_name());
							Thread.sleep(200);
						}
					}
				}

				p2c.setList(list);
				cityList.put(province2.getProvince_id(), p2c);
			}
			// p.setCityList(cityList);
			// data.add(p);
		}
		result.setData(list);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping("parent/{parent}/simple")
	@ResponseBody
	public List<Map<String, String>> listAsIdNamePairs(@PathVariable("parent") String parent) throws Exception {
		List<Map<String, String>> result = new ArrayList<>();
		Province t = new Province();
		t.setParent(parent);
		List<Province> tList = getBaseService().list(t);
		if (tList != null) {
			for (Province province : tList) {
				Map<String, String> map = new HashMap<>();
				map.put("id", province.getProvince_id());
				map.put("name", province.getProvince_name());
				result.add(map);
			}
		}

		return result;
	}

	@RequestMapping("init")
	@ResponseBody
	public APIResponseBaseObject init() throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		String str = IOUtils.toString(new FileInputStream(new File(
				"/Users/xiningwang/Documents/workspace-sts-3.9.0.RELEASE/onemap/src/com/onemap/allcities.json")));
		System.out.println(str);
		JSONObject root = new JSONObject(str);
		JSONArray data = root.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			JSONObject jsonObject = data.getJSONObject(i);
			Province p = new Province();
			p.setProvince_id(jsonObject.getString("province_id"));
			p.setProvince_name(jsonObject.getString("province_name"));
			p.setProvince_simple(jsonObject.getString("province_simple"));
			p.setParent("0");
			this.getBaseService().save(p);

			JSONObject cityList = jsonObject.getJSONObject("cityList");
			Iterator keys = cityList.keys();
			while (keys.hasNext()) {
				String key = keys.next().toString();
				JSONObject cityObject = cityList.getJSONObject(key);
				String name = cityObject.getString("name");

				Province p1 = new Province();
				p1.setProvince_id(key);
				p1.setProvince_name(name);
				p1.setParent(p.getProvince_id());
				this.getBaseService().save(p1);

				if (cityObject.has("list")) {

					JSONObject subCities = cityObject.getJSONObject("list");
					Iterator keys2 = subCities.keys();
					while (keys2.hasNext()) {
						String _key = keys2.next().toString();
						String _value = subCities.getString(_key);
						Province p2 = new Province();
						p2.setProvince_id(_key);
						p2.setProvince_name(_value);
						p2.setParent(p1.getProvince_id());
						this.getBaseService().save(p2);
					}
				}
			}
		}
		// List<Province> tList = getBaseService().list(t);
		// System.out.println(tList);
		// result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getById(@PathVariable("id") Integer id) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Province item = getBaseService().get(id);
		System.out.println(item);
		result.setData(item);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody Province t) throws Exception {
		System.out.println(t);

		if (t.getId() == null) {
			this.getBaseService().save(t);
		} else {
			this.getBaseService().update(t);
		}

		APIResponseBaseObject result = new APIResponseBaseObject();

		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
}
