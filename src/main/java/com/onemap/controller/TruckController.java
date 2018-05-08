package com.onemap.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Clause;
import com.onemap.domain.Goods;
import com.onemap.domain.IdentityAuth;
import com.onemap.domain.ManagementRecord;
import com.onemap.domain.Province;
import com.onemap.domain.Truck;
import com.onemap.domain.User;
import com.onemap.domain.UserFavorite;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.ProvinceService;
import com.onemap.service.TruckService;
import com.onemap.service.UserFavoriteService;
import com.onemap.service.UserService;
import com.onemap.service.UserVehicleService;
import com.onemap.utl.common.CalculateDisUtil;

@Controller
@RequestMapping("/api/truck")
public class TruckController extends BaseController<Truck, Integer> {
	@Autowired
	private TruckService truckService;
	@Autowired
	private UserVehicleService userVehicleService;
	@Autowired
	private UserFavoriteService userfavoriteService;
	@Autowired
	private ManagementRecordService mgtrecordService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private IdentityAuthService authservice;
	@Autowired
	private UserService userService;
	@Override
	BaseService<Truck, Integer> getBaseService() {
		return this.truckService;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {

	}

	private String getPolishedFromAddress(Truck goods) {
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
		return sb.toString();
	}
	private String getPolishedToAddress(Truck goods) {
		String s1 = goods.getToProvinceName();
		String s2 = goods.getToCityName();
		String s3 = goods.getToAreaName();
		String s4 = goods.getToAddress();
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
		return sb.toString();
	}
	private boolean populateCalculation(Truck goods) throws Exception {
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
		
		String s11 = goods.getToProvinceName();
		String s21 = goods.getToCityName();
		String s31 = goods.getToAreaName();
		String toname = goods.getToname();
		if (toname != null) {
			String[] split = toname.split(",");
			int length = split.length;
			if(StringUtils.isEmpty(s11) && length > 0) {
				goods.setToProvinceName(split[0]);
			}
			if(StringUtils.isEmpty(s21) && length > 1) {
				goods.setToCityName(split[1]);
			}
			if(StringUtils.isEmpty(s31) && length > 2) {
				goods.setToAreaName(split[2]);
			}
		}
		double tolng = goods.getTolng();
		double tolat = goods.getTolat();
		if(tolng <= 0 || tolat <=0) {
			String toAddress = getPolishedToAddress(goods);
			double[] lngLat = CalculateDisUtil.getLngLat(toAddress);
			goods.setTolng(lngLat[0]);
			goods.setTolat(lngLat[1]);
			needupdate = true;
		}
		
		int mileage = goods.getMileage();
		if(mileage <=0) {
			String fromid = goods.getFromid();
			String toid = goods.getToid();
			String cityCode = CalculateDisUtil.getCityCode(fromid);
			String cityCode2 = CalculateDisUtil.getCityCode(toid);
			double lng1 = 0.0;
			double lng2 = 0.0;
			double lat1 = 0.0;
			double lat2 = 0.0;
			Province p = new Province();
			p.setProvince_id(cityCode);
			List<Province> list = provinceService.list(p);
			if(!list.isEmpty()) {
				lng1 = list.get(0).getLng();
				lat1 = list.get(0).getLat();
			}
			p.setProvince_id(cityCode2);
			list = provinceService.list(p);
			if(!list.isEmpty()) {
				lng2 = list.get(0).getLng();
				lat2 = list.get(0).getLat();
			}
			
			int distance = CalculateDisUtil.getDistance(lat1, lng1, lat2, lng2);
			goods.setMileage(distance);
			needupdate = true;
		}
		
		return needupdate;
	}
	
	@RequestMapping("")
	@ResponseBody
	public APIResponseBaseObject listJson(Truck t, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		if(t.getStatus() == -1) {
			//nothing to do
		} else {
			List<Clause> whereClause = new ArrayList<>();
			if(t.getStatus() == 0) {
				//status: 0 or 1
				Clause clause = new Clause();
				clause.setColumn("status");
				clause.setOperator(">=");
				clause.setValue(0);
				whereClause.add(clause);
				
				Clause clause2 = new Clause();
				clause2.setColumn("status");
				clause2.setOperator("<=");
				clause2.setValue(1);
				whereClause.add(clause2);
				
			} else {
				//status: 11(对方确认交易) or 99(作废)
				Clause clause = new Clause();
				clause.setColumn("status");
				clause.setOperator("=");
				clause.setValue(t.getStatus());
				whereClause.add(clause);
			}
			
			t.setWhereClause(whereClause);
		}
		List<Truck> tList = getBaseService().listByLimit(t);
//		System.out.println(tList);
		for (Truck goods : tList) {
			boolean needupdate = populateCalculation(goods);
			if(needupdate) {
				getBaseService().update(goods);
			}
		}
		if(!StringUtils.isEmpty(username)) {
			for (Truck truck : tList) {
				List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndVehicle(username, truck.getId());
				truck.setFavoited(byUsernameAndCargo!=null&&byUsernameAndCargo.size()>0);
			}
		}
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("/q")
	@ResponseBody
	public APIResponseBaseObject query(@RequestParam(value="username",required=false) String username,
			@RequestParam(value="shipTimestamp",required=false) String shipTimestamp,
			@RequestParam(value="fromid",required=false) String fromid,
			@RequestParam(value="toid",required=false) String toid,
			@RequestParam(value="carType",required=false) String carType,
			@RequestParam(value="carLength",required=false) String carLength,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="pageSize",required=false) Integer pageSize) throws Exception {
		System.out.println("page:" + page + ",pageSize:" + pageSize);
		Truck t = new Truck();
		t.setPage(page);
		t.setPageSize(pageSize);
		try {
			java.sql.Date date = java.sql.Date.valueOf(shipTimestamp);
			t.setShipTimestamp(date);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(!StringUtils.isEmpty(fromid) &&  !fromid.trim().equals(",,")) {
			t.setFromid(polish(fromid.trim()));
		}
		if(!StringUtils.isEmpty(toid) && !toid.trim().equals(",,")) {
			t.setToid(polish(toid.trim()));
		}
		if(!StringUtils.isEmpty(carType) && !carType.contains("请选择车辆类型")) {
			t.setCarType(carType.trim());
		}
//		try {
//			double _carLength = Double.parseDouble(carLength);
//			t.setCarLength(_carLength);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		t.setStatus(-1);
		if(!StringUtils.isEmpty(status)) {
			try {
				int _status = Integer.parseInt(status);
				if(_status >= 0 ) {
					t.setStatus(_status);
				} else {
					t.setStatus(-1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Truck> tList = this.truckService.query(t);
		System.out.println(tList);
		if(!StringUtils.isEmpty(username)) {
			for (Truck goods : tList) {
				List<UserFavorite> byUsernameAndVehicle = userfavoriteService.getByUsernameAndVehicle(username, goods.getId());
				goods.setFavoited(byUsernameAndVehicle!=null&&byUsernameAndVehicle.size()>0);
			}
		}
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
//	public static void main(String[] args) {
//		polish("11,1222,");
//	}
	private static String polish(String trim) {
//		if(trim == null) return null;
//		String substring = trim.substring(trim.length()-1, trim.length());
		
		return trim;
	}
	
	@RequestMapping("username/{username}")
	@ResponseBody
	public APIResponseBaseObject listJsonByUsername(@PathVariable("username") String username, Truck t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Truck> tList = truckService.getByUsername(username, t);
		System.out.println(tList);
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("username/{username}/latest")
	@ResponseBody
	public APIResponseBaseObject listLatestJsonByUsername(@PathVariable("username") String username, Truck t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		t.setPage(1);
		t.setPageSize(1);
		List<Truck> tList = truckService.getByUsername(username, t);
		System.out.println(tList);
		if(tList!=null && tList.size()>0) {
			result.setData(tList.get(0));
		} else {
			User user = userService.getByUsername(username);
			if(user != null) {
				Truck item = new Truck();
				item.setOwner(user.getRealname());
				item.setOwnerCellphone(user.getMobilephone());
				item.setOwnercompany(user.getCompany());
				item.setOperator(user.getRealname());
				item.setEmergencyContact(user.getEmergency());
				item.setEmergencyCellphone(user.getEmergencyphone());
				result.setData(item);
			}
		}
		
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("username/{username}/status/confirmed")
	@ResponseBody
	public APIResponseBaseObject listConfirmedJsonByUsername(@PathVariable("username") String username,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="pageSize",required=false) Integer pageSize,
			Truck t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		System.out.println("page:" + page + ",pageSize:" + pageSize);
		t.setPage(page);
		t.setPageSize(pageSize);
		List<Truck> tList = truckService.getByUsernameAndStatus(username, 11, t);
		if(tList != null) {
			for (Truck truck : tList) {
				ManagementRecord tx = new ManagementRecord();
				tx.setTruckId(truck.getId());
				tx.setStatus(1);
				List<ManagementRecord> list = this.mgtrecordService.list(tx);
				if(list != null && list.size() > 0) {
					Integer txId = list.get(0).getId();
					truck.setTxId(txId);
				}
			}
		}
		
		System.out.println(tList);
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("username/{username}/status/todo")
	@ResponseBody
	public APIResponseBaseObject listTodoJsonByUsername(@PathVariable("username") String username,Truck t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Truck> tList = truckService.getByUsernameAndNonStatus(username, 11, t);
		System.out.println(tList);
		if(tList != null) {
			for (Truck truck : tList) {
				ManagementRecord record = new ManagementRecord();
				record.setTruckId(truck.getId());
				record.setVar1(username);
				record.setType(2);
				int count = mgtrecordService.count(record);
				truck.setTxId(count);
			}
		}
		
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getById(@PathVariable("id") Integer id, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Truck truck = getBaseService().get(id);
		List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndVehicle(username, id);
		truck.setFavoited(byUsernameAndCargo!=null && byUsernameAndCargo.size()>0);
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setTruckId(id);
		List<ManagementRecord> records = mgtrecordService.list(t);
		truck.setCanCreate(records != null && records.size() > 0);
		
		boolean confirmed = mgtrecordService.isConfirmedByUsernameAndVehicle(username, id);
		boolean canceled = mgtrecordService.isCanceledByUsernameAndVehicle(username, id);
		if(confirmed || canceled ) {
			if(confirmed) {
				truck.setCanDelete(false);
				truck.setCanUpdate(false);
			} else {
				truck.setCanDelete(true);
				truck.setCanUpdate(false);
			} 
		} else {
			truck.setCanDelete(true);
			truck.setCanUpdate(true);
		}
		System.out.println(truck);
		result.setData(truck);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value = "{id}/txId/{txId}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getTruckByIdAndTxId(@PathVariable("id") Integer id, @PathVariable("txId") Integer txId, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Truck truck = getBaseService().get(id);
		List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndVehicle(username, id);
		truck.setFavoited(byUsernameAndCargo!=null && byUsernameAndCargo.size()>0);
		ManagementRecord record = mgtrecordService.get(txId);
		if(record!=null) {
			truck.setTxId(record.getStatus());
		} else {
			truck.setTxId(0);
		}
		System.out.println(truck);
		result.setData(truck);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody Truck t) throws Exception {
		System.out.println(t);
		boolean b1 = false;
		String username = t.getUsername();
		List<IdentityAuth> auth100 = authservice.getByUsernameAndType(username, "100");
		if(auth100.size() > 0) {
			b1 = (auth100.get(0).getAuthresult() == 1);
		}
		if(b1 == false) {
			APIResponseBaseObject result = new APIResponseBaseObject();
			result.setInfo("您还没有完成实名认证，不能发布信息！");
			result.setStatus(-1);
			return result;
		}
		boolean b2 = false;
		List<IdentityAuth> auth200 = authservice.getByUsernameAndType(username, "200");
		if(auth200.size() > 0) {
			b2 = (auth200.get(0).getAuthresult() == 1);
		}
		if(b2 == false) {
			APIResponseBaseObject result = new APIResponseBaseObject();
			result.setInfo("您还没有完成驾驶证认证，不能发布信息！");
			result.setStatus(-1);
			return result;
		}
		
		String fromname = t.getFromname();
		if(fromname != null) {
			String[] split = fromname.split(",");
			if(split.length > 0) {
				t.setFromProvinceName(split[0]);
			}
			if(split.length > 1) {
				t.setFromCityName(split[1]);
			}
			if(split.length > 2) {
				t.setFromAreaName(split[2]);
			}
		}
		String toname = t.getToname();
		if(toname != null) {
			String[] split = toname.split(",");
			if(split.length > 0) {
				t.setToProvinceName(split[0]);
			}
			if(split.length > 1) {
				t.setToCityName(split[1]);
			}
			if(split.length > 2) {
				t.setToAreaName(split[2]);
			}
		}
		if(StringUtils.isEmpty(t.getLicenseplate())) {
			APIResponseBaseObject result = new APIResponseBaseObject();

			result.setInfo("Failed");
			result.setStatus(0);
			return result;
		}
		
		List<UserVehicle> vehicles = userVehicleService.getByUsernameAndLicense(t.getUsername(), t.getLicenseplate());
		if(vehicles != null && vehicles.size() > 0) {
			UserVehicle userVehicle = vehicles.get(0);
			t.setCarLength(userVehicle.getCargolength());
			t.setCarType(userVehicle.getVehicletype());
			t.setTruckBarnd(userVehicle.getVehiclebrand());
			t.setTruckName(userVehicle.getLicenseplate());
			t.setTruckWeight(userVehicle.getVehicleweight());
			t.setRegisterAreaName(userVehicle.getRegistrationaddress());
			t.setVehicledimension(userVehicle.getVehicledimension());
//			t.setRegisterCityName(registerCityName);
//			t.setRegisterProvinceName(registerProvinceName);
		} else {
			APIResponseBaseObject result = new APIResponseBaseObject();

			result.setInfo("Failed");
			result.setStatus(0);
			return result;
		}
		
		populateCalculation(t);
		
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
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }
}
