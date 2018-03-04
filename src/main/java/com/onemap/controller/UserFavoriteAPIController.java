/**
 * 
 */
package com.onemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Clause;
import com.onemap.domain.Goods;
import com.onemap.domain.Truck;
import com.onemap.domain.UserFavorite;
import com.onemap.service.BaseService;
import com.onemap.service.GoodsService;
import com.onemap.service.TruckService;
import com.onemap.service.UserFavoriteService;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/userfavorite")
public class UserFavoriteAPIController extends BaseController<UserFavorite, Integer> {
	@Autowired
	private UserFavoriteService service;
	@Autowired
	private TruckService truckService;
	@Autowired
	private GoodsService cargoService;
	
	@Override
	BaseService<UserFavorite, Integer> getBaseService() {
		return service;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsername(@PathVariable("username") String username) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UserFavorite> auths = this.service.getByUsername(username);
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

	@RequestMapping(value = "username/{username}/vehicle", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndVehicle(@PathVariable("username") String username,UserFavorite t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		//UserFavorite t = new UserFavorite();
		List<Clause> whereClause = new ArrayList<Clause>();
		Clause c1 = new Clause();
		c1.setColumn("username");
		c1.setOperator("=");
		c1.setValue(username);
		whereClause.add(c1);
		Clause c2 = new Clause();
		c2.setColumn("vehicleinfoid");
		c2.setOperator(">");
		c2.setValue(0);
		whereClause.add(c2);
		t.setWhereClause(whereClause);
		List<UserFavorite> list = service.listByLimit(t);
		List<Truck> data = new ArrayList<>();
		if(list != null) {
			for (UserFavorite userFavorite : list) {
				Truck truck = truckService.get(userFavorite.getVehicleinfoid());
				data.add(truck);
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value = "username/{username}/cargo", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndCargo(@PathVariable("username") String username,UserFavorite t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		//UserFavorite t = new UserFavorite();
		List<Clause> whereClause = new ArrayList<Clause>();
		Clause c1 = new Clause();
		c1.setColumn("username");
		c1.setOperator("=");
		c1.setValue(username);
		whereClause.add(c1);
		Clause c2 = new Clause();
		c2.setColumn("cargoinfoid");
		c2.setOperator(">");
		c2.setValue(0);
		whereClause.add(c2);
		t.setWhereClause(whereClause);
		List<UserFavorite> list = service.listByLimit(t);
		List<Goods> data = new ArrayList<>();
		if(list != null) {
			for (UserFavorite userFavorite : list) {
				Goods goods = cargoService.get(userFavorite.getCargoinfoid());
				data.add(goods);
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping(value = "username/{username}/mycount", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject countByUsernameAndVehicleAndCargo(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		int countByUsernameAndVehicle = service.countByUsernameAndVehicle(username);
		int countByUsernameAndCargo = service.countByUsernameAndCargo(username);
		int countByUsername = truckService.countByUsername(username);
		int countByUsername2 = cargoService.countByUsername(username);
		Map<String,Integer> map = new HashMap<>();
		map.put("1", countByUsernameAndVehicle);
		map.put("2", countByUsernameAndCargo);
		map.put("3", countByUsername);
		map.put("4", countByUsername2);
		
		result.setData(map);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping(value = "username/{username}/vehicle/count", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject countByUsernameAndVehicle(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		int countByUsernameAndVehicle = service.countByUsernameAndVehicle(username);
		result.setData(countByUsernameAndVehicle);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value = "username/{username}/cargo/count", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject countByUsernameAndCargo(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		int countByUsernameAndCargo = service.countByUsernameAndCargo(username);
		result.setData(countByUsernameAndCargo);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping(value = "username/{username}/vehicle/{vehicleinfoid}", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject saveByUsernameAndVehicle(@PathVariable("username") String username, @PathVariable("vehicleinfoid") Integer vehicleinfoid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UserFavorite> byUsernameAndVehicle = service.getByUsernameAndVehicle(username, vehicleinfoid);
			if (byUsernameAndVehicle == null || byUsernameAndVehicle.size() ==0) {
				UserFavorite t = new UserFavorite();
				t.setUsername(username);
				t.setVehicleinfoid(vehicleinfoid);
				service.save(t);
				result.setInfo("OK");
				result.setStatus(1);
			} else {
				result.setInfo("duplicate");
				result.setStatus(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
	@RequestMapping(value = "username/{username}/cargo/{cargoinfoid}", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject saveByUsernameAndCargo(@PathVariable("username") String username, @PathVariable("cargoinfoid") Integer cargoinfoid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UserFavorite> byUsernameAndCargo = service.getByUsernameAndCargo(username, cargoinfoid);
			if (byUsernameAndCargo == null || byUsernameAndCargo.size() ==0) {
				UserFavorite t = new UserFavorite();
				t.setUsername(username);
				t.setCargoinfoid(cargoinfoid);
				service.save(t);
				result.setInfo("OK");
				result.setStatus(1);
			} else {
				result.setInfo("duplicate");
				result.setStatus(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
	@RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public APIResponseBaseObject deleteById(@PathVariable("id") Integer id) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			UserFavorite t = new UserFavorite();
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
	@RequestMapping(value = "username/{username}/vehicle/{vehicleinfoid}", method = RequestMethod.DELETE)
	@ResponseBody
	public APIResponseBaseObject deleteByUsernameAndVehicle(@PathVariable("username") String username, @PathVariable("vehicleinfoid") Integer vehicleinfoid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			service.deleteByUsernameAndVehicle(username, vehicleinfoid);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
	@RequestMapping(value = "username/{username}/cargo/{cargoinfoid}", method = RequestMethod.DELETE)
	@ResponseBody
	public APIResponseBaseObject deleteByUsernameAndCargo(@PathVariable("username") String username, @PathVariable("cargoinfoid") Integer cargoinfoid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			service.deleteByUsernameAndCargo(username, cargoinfoid);
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
