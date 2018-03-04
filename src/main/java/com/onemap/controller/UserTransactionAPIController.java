/**
 * 
 */
package com.onemap.controller;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
import com.onemap.domain.ManagementRecord;
import com.onemap.domain.Truck;
import com.onemap.domain.User;
import com.onemap.service.BaseService;
import com.onemap.service.GoodsService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.TruckService;
import com.onemap.service.UserService;
import com.onemap.utl.common.SerialNumber;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/usertransaction")
public class UserTransactionAPIController extends BaseController<ManagementRecord, Integer> {

	@Autowired
	private TruckService truckService;
	@Autowired
	private GoodsService cargoService;
	@Autowired
	private ManagementRecordService service;
	@Autowired
	private UserService userService;

	@Override
	BaseService<ManagementRecord, Integer> getBaseService() {
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
			List<ManagementRecord> auths = this.service.getByUsername(username);
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
	public APIResponseBaseObject getByUsernameAndVehicle(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		List<Clause> whereClause = new ArrayList<Clause>();
		Clause c2 = new Clause();
		c2.setColumn("truckId");
		c2.setOperator(">");
		c2.setValue(0);
		whereClause.add(c2);
		t.setWhereClause(whereClause);
		List<ManagementRecord> list = service.list(t);
		List<Truck> data = new ArrayList<>();
		if (list != null) {
			for (ManagementRecord item : list) {
				Truck truck = truckService.get(item.getTruckId());
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
	public APIResponseBaseObject getByUsernameAndCargo(@PathVariable("username") String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		List<Clause> whereClause = new ArrayList<Clause>();
		Clause c2 = new Clause();
		c2.setColumn("cargoId");
		c2.setOperator(">");
		c2.setValue(0);
		whereClause.add(c2);
		t.setWhereClause(whereClause);
		List<ManagementRecord> list = service.list(t);
		List<Goods> data = new ArrayList<>();
		if (list != null) {
			for (ManagementRecord item : list) {
				Goods goods = cargoService.get(item.getCargoId());
				data.add(goods);
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	@RequestMapping(value = "username/{username}/cargo/{cargoId}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndCargo(@PathVariable("username") String username,
			@PathVariable("cargoId") Integer cargoId) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setCargoId(cargoId);

		List<ManagementRecord> list = service.list(t);
		List<Truck> data = new ArrayList<>();
		if (list != null) {
			for (ManagementRecord item : list) {
				Truck truck = truckService.get(item.getTruckId());
				if(truck != null) {
					truck.setTxId(item.getId());
					truck.setStatus(item.getStatus());
					data.add(truck);
				}
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping("id/{id}")
	@ResponseBody
	public APIResponseBaseObject getJsonById(@PathVariable("id") Integer id)
			throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord managementRecord = service.get(id);
		if (managementRecord != null) {
			Goods goods = cargoService.get(managementRecord.getCargoId());
			if(goods.getUsername() != null) {
				User user = userService.getByUsername(goods.getUsername());
				if(user != null) {
					goods.setUsername(user.getCompany());
				}
			}
			managementRecord.setCargoObj(goods);
			Truck truck = truckService.get(managementRecord.getTruckId());
			if(truck.getUsername() != null) {
				User user = userService.getByUsername(truck.getUsername());
				if(user != null) {
					truck.setUsername(user.getIdcard());
				}
			}
			managementRecord.setTruckObj(truck);
			result.setData(managementRecord);
			result.setInfo("OK");
			result.setStatus(1);
		} else {
			result.setData(null);
			result.setInfo("404");
			result.setStatus(-1);
		}
		
		return result;
	}
	
	@RequestMapping(value = "username/{username}/truck/{truckId}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndTruck(@PathVariable("username") String username,
			@PathVariable("truckId") Integer truckId) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setTruckId(truckId);

		List<ManagementRecord> list = service.list(t);
		List<Goods> data = new ArrayList<>();
		if (list != null) {
			for (ManagementRecord item : list) {
				Goods goods = cargoService.get(item.getCargoId());
				if(goods != null) {
					goods.setTxId(item.getId());
					goods.setStatus(item.getStatus());
					data.add(goods);
				}
			}
		}
		result.setData(data);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	// @RequestMapping(value = "username/{username}/mycount", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public APIResponseBaseObject
	// countByUsernameAndVehicleAndCargo(@PathVariable("username") String username)
	// throws Exception {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// int countByUsernameAndVehicle = service.countByUsernameAndVehicle(username);
	// int countByUsernameAndCargo = service.countByUsernameAndCargo(username);
	// int countByUsername = truckService.countByUsername(username);
	// int countByUsername2 = cargoService.countByUsername(username);
	// Map<String,Integer> map = new HashMap<>();
	// map.put("1", countByUsernameAndVehicle);
	// map.put("2", countByUsernameAndCargo);
	// map.put("3", countByUsername);
	// map.put("4", countByUsername2);
	//
	// result.setData(map);
	// result.setInfo("OK");
	// result.setStatus(1);
	// return result;
	// }

	// @RequestMapping(value = "username/{username}/vehicle/count", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public APIResponseBaseObject
	// countByUsernameAndVehicle(@PathVariable("username") String username) throws
	// Exception {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// ManagementRecord t = new ManagementRecord();
	// t.setop
	// int countByUsernameAndVehicle = service.count(t);
	// result.setData(countByUsernameAndVehicle);
	// result.setInfo("OK");
	// result.setStatus(1);
	// return result;
	// }
	// @RequestMapping(value = "username/{username}/cargo/count", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public APIResponseBaseObject
	// countByUsernameAndCargo(@PathVariable("username") String username) throws
	// Exception {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// int countByUsernameAndCargo = service.countByUsernameAndCargo(username);
	// result.setData(countByUsernameAndCargo);
	// result.setInfo("OK");
	// result.setStatus(1);
	// return result;
	// }
	//
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject saveByUsername(@RequestBody ManagementRecord t) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			System.out.println(t);
			int count = service.count(t);
			if (count == 0) {
				t.setStatus(0);
				t.setOperationTime(new Date(new java.util.Date().getTime()));
				t.setOrdernumber(SerialNumber.getYSDNumber());
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
	// @RequestMapping(value = "username/{username}/cargo/{cargoinfoid}", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public APIResponseBaseObject saveByUsernameAndCargo(@PathVariable("username")
	// String username, @PathVariable("cargoinfoid") Integer cargoinfoid) {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// try {
	// List<UserFavorite> byUsernameAndCargo =
	// service.getByUsernameAndCargo(username, cargoinfoid);
	// if (byUsernameAndCargo == null || byUsernameAndCargo.size() ==0) {
	// UserFavorite t = new UserFavorite();
	// t.setUsername(username);
	// t.setCargoinfoid(cargoinfoid);
	// service.save(t);
	// result.setInfo("OK");
	// result.setStatus(1);
	// } else {
	// result.setInfo("duplicate");
	// result.setStatus(2);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.setInfo(e.getMessage());
	// result.setStatus(0);
	// }
	// return result;
	// }
	// @RequestMapping(value = "id/{id}", method = RequestMethod.DELETE)
	// @ResponseBody
	// public APIResponseBaseObject deleteById(@PathVariable("id") Integer id) {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// try {
	// UserFavorite t = new UserFavorite();
	// t.setId(id);
	// service.delete(t);
	// result.setInfo("OK");
	// result.setStatus(1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.setInfo(e.getMessage());
	// result.setStatus(0);
	// }
	// return result;
	// }

	@RequestMapping(value = "{txid}/confirm", method = RequestMethod.PUT)
	@ResponseBody
	public APIResponseBaseObject confirm(@PathVariable("txid") Integer txid) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord managementRecord = service.get(txid);
		if (managementRecord != null) {
			//service.updateStatus(txid, 1);
			Integer cargoId = managementRecord.getCargoId();
			Integer truckId = managementRecord.getTruckId();
			String approver1 = null;
			String approver2 = null;
			{
				Goods goods = cargoService.get(cargoId);
				if(goods !=null) {
					User user = userService.getByUsername(goods.getUsername());
					if(user!=null) {
						approver1 = user.getRealname();
					}
				}
				
				Truck truck = truckService.get(truckId);
				if(truck !=null) {
					User user = userService.getByUsername(truck.getUsername());
					if(user!=null) {
						approver2 = user.getRealname();
					}
				}
			}
			{
				ManagementRecord t = new ManagementRecord();
				t.setId(txid);
				t.setStatus(1);
				t.setApprover1(approver1);
				t.setDatetime1(new Date(new java.util.Date().getTime()));
				t.setApprover2(approver2);
				t.setDatetime2(new Date(new java.util.Date().getTime()));
				service.updateStatusWithApprovers(t);
			}
			{
				ManagementRecord t = new ManagementRecord();
				t.setCargoId(cargoId);
				t.setTruckId(truckId);
				List<ManagementRecord> list = service.listByCargoIdOrTruckId(t);
				if (list != null) {
					for (ManagementRecord managementRecord2 : list) {
						Integer id = managementRecord2.getId();
						if (id.equals(txid)) {
							continue;
						} else {
							service.updateStatus(id, -1);
						}
					}
				}
			}

			Goods goods = new Goods();
			goods.setId(cargoId);
			goods.setStatus(11);
			cargoService.updateStatus(goods);
			Truck truck = new Truck();
			truck.setId(truckId);
			truck.setStatus(11);
			truckService.updateStatus(truck);
			result.setInfo("OK");
			result.setStatus(1);
		} else {
			result.setInfo("该记录不存在");
			result.setStatus(-1);
		}

		return result;
	}

	@RequestMapping(value = "{txid}/cancel", method = RequestMethod.PUT)
	@ResponseBody
	public APIResponseBaseObject cancel(@PathVariable("txid") Integer txid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		service.updateStatus(txid, -1);

		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}

	// @RequestMapping(value = "username/{username}/vehicle/{vehicleinfoid}", method
	// = RequestMethod.DELETE)
	// @ResponseBody
	// public APIResponseBaseObject
	// deleteByUsernameAndVehicle(@PathVariable("username") String username,
	// @PathVariable("vehicleinfoid") Integer vehicleinfoid) {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// try {
	// service.deleteByUsernameAndVehicle(username, vehicleinfoid);
	// result.setInfo("OK");
	// result.setStatus(1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.setInfo(e.getMessage());
	// result.setStatus(0);
	// }
	// return result;
	// }
	// @RequestMapping(value = "username/{username}/cargo/{cargoinfoid}", method =
	// RequestMethod.DELETE)
	// @ResponseBody
	// public APIResponseBaseObject
	// deleteByUsernameAndCargo(@PathVariable("username") String username,
	// @PathVariable("cargoinfoid") Integer cargoinfoid) {
	// APIResponseBaseObject result = new APIResponseBaseObject();
	// try {
	// service.deleteByUsernameAndCargo(username, cargoinfoid);
	// result.setInfo("OK");
	// result.setStatus(1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.setInfo(e.getMessage());
	// result.setStatus(0);
	// }
	// return result;
	// }
}
