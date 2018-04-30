package com.onemap.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.onemap.domain.Goods;
import com.onemap.domain.IdentityAuth;
import com.onemap.domain.ManagementRecord;
import com.onemap.domain.Province;
import com.onemap.domain.Truck;
import com.onemap.domain.User;
import com.onemap.domain.UserFavorite;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.GoodsService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.ProvinceService;
import com.onemap.service.UserFavoriteService;
import com.onemap.service.UserService;
import com.onemap.utl.common.CalculateDisUtil;

@Controller
@RequestMapping("/api/cargoes")
public class GoodsController extends BaseController<Goods, Integer> {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private GoodsService service;
	@Autowired
	private UserFavoriteService userfavoriteService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private IdentityAuthService authservice;
	@Autowired
	private ManagementRecordService mgtrecordService;
	@Autowired
	private UserService userService;
	@Override
	BaseService<Goods, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	private String getPolishedFromAddress(Goods goods) {
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
	private String getPolishedToAddress(Goods goods) {
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
	
	@RequestMapping("")
	@ResponseBody
	public APIResponseBaseObject listJson(Goods t, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		t.setUsername(null);
		List<Goods> tList = getBaseService().listByLimit(t);
//		System.out.println(tList);
		for (Goods goods : tList) {
			boolean needupdate = populateCalculation(goods);
			if(needupdate) {
				getBaseService().update(goods);
			}
		}
		if(!StringUtils.isEmpty(username)) {
			for (Goods goods : tList) {
				List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndCargo(username, goods.getId());
				goods.setFavoited(byUsernameAndCargo!=null&&byUsernameAndCargo.size()>0);
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
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="pageSize",required=false) Integer pageSize) throws Exception {
		System.out.println("page:" + page + ",pageSize:" + pageSize);
		Goods t = new Goods();
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
		try {
			double _carLength = Double.parseDouble(carLength);
			t.setCarLength(_carLength);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Goods> tList = this.service.query(t);
		System.out.println(tList);
		if(!StringUtils.isEmpty(username)) {
			for (Goods goods : tList) {
				List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndCargo(username, goods.getId());
				goods.setFavoited(byUsernameAndCargo!=null&&byUsernameAndCargo.size()>0);
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
	public APIResponseBaseObject listJsonByUsername(@PathVariable("username") String username, Goods t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		List<Goods> tList = service.getByUsername(username, t);
		System.out.println(tList);
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("username/{username}/latest")
	@ResponseBody
	public APIResponseBaseObject listLatestJsonByUsername(@PathVariable("username") String username, Goods t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		t.setPage(1);
		t.setPageSize(1);
		List<Goods> tList = service.getByUsername(username, t);
		System.out.println(tList);
		if(tList!=null && tList.size()>0) {
			result.setData(tList.get(0));
		} else {
			User user = userService.getByUsername(username);
			if(user != null) {
				Goods item = new Goods();
				item.setCargoOwner(user.getRealname());
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
			@RequestParam(value="pageSize",required=false) Integer pageSize, Goods t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		System.out.println("page:" + page + ",pageSize:" + pageSize);
		t.setPage(page);
		t.setPageSize(pageSize);
		List<Goods> tList = service.getByUsernameAndStatus(username, 11, t);
		if(tList != null) {
			for (Goods item : tList) {
				ManagementRecord tx = new ManagementRecord();
				tx.setCargoId(item.getId());
				tx.setStatus(1);
				List<ManagementRecord> list = this.mgtrecordService.list(tx);
				if(list != null && list.size() > 0) {
					Integer txId = list.get(0).getId();
					item.setTxId(txId);
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
	public APIResponseBaseObject listTodoJsonByUsername(@PathVariable("username") String username, @RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="pageSize",required=false) Integer pageSize, Goods t) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		System.out.println("page:" + page + ",pageSize:" + pageSize);
		t.setPage(page);
		t.setPageSize(pageSize);
		List<Goods> tList = service.getByUsernameAndNonStatus(username, 11, t);
		System.out.println(tList);
		if(tList != null) {
			for (Goods item : tList) {
				ManagementRecord record = new ManagementRecord();
				record.setCargoId(item.getId());
				record.setVar1(username);
				record.setType(1);
				int count = mgtrecordService.count(record);
				item.setTxId(count);
			}
		}
		result.setData(tList);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value = "{id}/txId/{txId}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getCargoByIdAndTxId(@PathVariable("id") Integer id, @PathVariable("txId") Integer txId, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Goods goods = service.get(id);
		List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndCargo(username, id);
		goods.setFavoited(byUsernameAndCargo!=null && byUsernameAndCargo.size()>0);
		ManagementRecord record = mgtrecordService.get(txId);
		if(record!=null) {
			goods.setTxId(record.getStatus());
		} else {
			goods.setTxId(0);
		}
		result.setData(goods);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getById(@PathVariable("id") Integer id, @RequestParam(value="username",required=false) String username) throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Goods goods = getBaseService().get(id);
		System.out.println(goods);
		List<UserFavorite> byUsernameAndCargo = userfavoriteService.getByUsernameAndCargo(username, id);
		goods.setFavoited(byUsernameAndCargo!=null && byUsernameAndCargo.size()>0);
		ManagementRecord t = new ManagementRecord();
		t.setOperator(username);
		t.setCargoId(id);
		List<ManagementRecord> records = mgtrecordService.list(t);
		goods.setCanCreate(records != null && records.size() > 0);
		
		boolean confirmed = mgtrecordService.isConfirmedByUsernameAndCargo(username, id);
		boolean canceled = mgtrecordService.isCanceledByUsernameAndCargo(username, id);
		if(confirmed || canceled ) {
			if(confirmed) {
				goods.setCanDelete(false);
				goods.setCanUpdate(false);
			} else {
				goods.setCanDelete(true);
				goods.setCanUpdate(false);
			} 
		} else {
			goods.setCanDelete(true);
			goods.setCanUpdate(true);
		}
		
		result.setData(goods);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	
	@RequestMapping(value = "{id}/status/{status}", method = RequestMethod.PUT)
	@ResponseBody
	public APIResponseBaseObject updateStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Goods goods = new Goods();
		goods.setId(id);
		goods.setStatus(status);
		service.updateStatus(goods);
		result.setData(goods);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("approve")
	public String approve(Goods t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			t.setStatus(1);
			service.updateStatus(t);
		}
		return "redirect:./editlist";
	}

	@RequestMapping("reject")
	public String reject(Goods t, @RequestParam("id2") Integer myid, @RequestParam("comment2") String comment)
			throws Exception {
		if (myid == null) {
			//
		} else {
			t.setStatus(-1);
			service.updateStatus(t);
		}
		return "redirect:./editlist";
	}
	private boolean populateCalculation(Goods goods) throws Exception {
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
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody Goods t) throws Exception {
		System.out.println(t);
		boolean b1 = false;
//		boolean b2 = false;
		String username = t.getUsername();
		List<IdentityAuth> auth100 = authservice.getByUsernameAndType(username, "100");
		if(auth100.size() > 0) {
			b1 = (auth100.get(0).getAuthresult() == 1);
		}
//		List<IdentityAuth> auth200 = authservice.getByUsernameAndType(username, "200");
//		if(auth200.size() > 0) {
//			b2 = (auth200.get(0).getAuthresult() == 1);
//		}
		if(b1 == false) {
			APIResponseBaseObject result = new APIResponseBaseObject();

			result.setInfo("您还没有完成实名认证，不能发布信息！");
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
