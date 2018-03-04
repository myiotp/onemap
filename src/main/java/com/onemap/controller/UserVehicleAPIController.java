/**
 * 
 */
package com.onemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.Configuration;
import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Clause;
import com.onemap.domain.Goods;
import com.onemap.domain.Province;
import com.onemap.domain.UserFavorite;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.UserVehicleService;
import com.onemap.utl.common.CalculateDisUtil;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/uservehicle")
public class UserVehicleAPIController extends BaseController<UserVehicle, Integer> {
	@Autowired
	private UserVehicleService service;

	@Override
	BaseService<UserVehicle, Integer> getBaseService() {
		return service;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@RequestMapping("listJson")
	@ResponseBody
	public Object listJson(UserVehicle t) throws Exception {
		List<UserVehicle> tList = null;
		tList = getBaseService().list(t);
		if(tList != null) {
			for (UserVehicle userVehicle : tList) {
				String certimage = userVehicle.getCertimage();
				userVehicle.setCertimage(Configuration.getImage_url() + "/vehicle/" + certimage);
			}
		}
		return tList;
	}
	
	@RequestMapping("getJson")
	@ResponseBody
	public Object getJson(UserVehicle t) throws Exception {
		UserVehicle tInDb = null;
		tInDb = this.getBaseService().get(t.getId());
		if(tInDb != null) {
			String certimage = tInDb.getCertimage();
			tInDb.setCertimage(Configuration.getImage_url() + "/vehicle/" + certimage);
		}
		return tInDb;
	}
	
	private String getPolishedFromAddress(UserVehicle goods) {
		String s1 = goods.getFromProvinceName();
		String s2 = goods.getFromCityName();
		String s3 = goods.getFromAreaName();
		
		
		String s4 = goods.getFromAddress();
		StringBuffer sb = new StringBuffer();
		if(org.apache.commons.lang.StringUtils.isNotEmpty(s1)) {
			sb.append(s1);
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(s2)) {
			sb.append(s2);
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(s3)) {
			sb.append(s3);
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(s4)) {
			sb.append(s4);
		}
		goods.setRegistrationaddress(sb.toString());
		return sb.toString();
	}
	private boolean populateCalculation(UserVehicle goods) throws Exception {
		String s1 = goods.getFromProvinceName();
		String s2 = goods.getFromCityName();
		String s3 = goods.getFromAreaName();
		String fromname = goods.getFromname();
		if (fromname != null) {
			String[] split = fromname.split(",");
			int length = split.length;
			if(StringUtils.isEmpty(s1) && length > 0) {
				goods.setFromProvinceName(split[0]);
			}
			if(StringUtils.isEmpty(s2) && length > 1) {
				goods.setFromCityName(split[1]);
			}
			if(StringUtils.isEmpty(s3) && length > 2) {
				goods.setFromAreaName(split[2]);
			}
		}
		
		double fromlng = goods.getFromlng();
		double fromlat = goods.getFromlat();
		boolean needupdate = false;
		if(fromlng <= 0 || fromlat <=0) {
			String fromAddress = getPolishedFromAddress(goods);
			double[] lngLat = CalculateDisUtil.getLngLat(fromAddress);
			goods.setFromlng(lngLat[0]);
			goods.setFromlat(lngLat[1]);
			needupdate = true;
		}
		
		return needupdate;
	}
	
	@RequestMapping(value = "username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsername(@PathVariable("username") String username) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UserVehicle> auths = this.service.getByUsername(username);
			System.out.println(auths);
			result.setData(auths);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
	@RequestMapping(value = "username/{username}/count", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject countByUsername(@PathVariable("username") String username) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			UserVehicle t = new UserVehicle();
			List<Clause> whereClause = new ArrayList<Clause>();
			Clause c2 = new Clause();
			c2.setColumn("username");
			c2.setOperator("=");
			c2.setValue(username);
			whereClause.add(c2);
			t.setWhereClause(whereClause);
			int count = this.service.count(t);
			result.setData(count);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "username/{username}/licenseplate", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject licenseplate(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<UserVehicle> tList = service.getByUsername(username);
		List<Map<String,Object>> data = new ArrayList<>();
		if (tList != null) {
			for (UserVehicle userVehicle : tList) {
				if (userVehicle.getAuthresult() == 1) {
					String licenseplate = userVehicle.getLicenseplate();
					Map<String,Object> m = new HashMap<String, Object>();
					m.put("id", ""+userVehicle.getId());
					m.put("name", licenseplate);
					data.add(m);
				}
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping(value = "id/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public APIResponseBaseObject updateStatus(@PathVariable("id") Integer id, UserVehicle t) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			UserVehicle userVehicle = this.service.get(id);
			System.out.println(userVehicle);
			if (userVehicle == null) {
				result.setInfo("This vehicle does not exist.");
				result.setStatus(-1);
				return result;
			}

			userVehicle.setAuthresult(t.getAuthresult());
			userVehicle.setComment(t.getComment());
			this.service.update(userVehicle);

			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "{username}/license/{license}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndLicense(@PathVariable("username") String username,
			@PathVariable("license") String license) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UserVehicle> auths = this.service.getByUsernameAndLicense(username, license);
			System.out.println(auths);
			if (auths.size() > 0) {
				result.setData(auths.get(0));
			}
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody UserVehicle t) {
		APIResponseBaseObject result = new APIResponseBaseObject();

		try {
			populateCalculation(t);
			if (t.getId() == null) {
				this.getBaseService().save(t);
			} else {
				this.getBaseService().update(t);
			}

			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
	
	@RequestMapping(value = "username/{username}/id/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public APIResponseBaseObject deleteById(@PathVariable("username") String username, @PathVariable("id") Integer id) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			UserVehicle userVehicle = service.get(id);
			if(userVehicle == null) {
				result.setInfo("Not Found");
				result.setStatus(0);
				return result;
			}
			if(userVehicle.getUsername() == null || !userVehicle.getUsername().equals(username)) {
				result.setInfo("This id["+id+"] does not belong to " + username);
				result.setStatus(0);
				return result;
			}
			
			UserVehicle t = new UserVehicle();
			t.setId(id);
			service.delete(t);
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
