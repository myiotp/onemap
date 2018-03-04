package com.onemap.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.api.service.GeocodingService;
import com.onemap.domain.FarmMachinery;
import com.onemap.domain.FarmMachineryGPS;
import com.onemap.domain.Landblock;
import com.onemap.domain.MachineryXY;
import com.onemap.service.BaseService;
import com.onemap.service.FarmMachineryGPSService;
import com.onemap.service.FarmMachineryService;
import com.onemap.service.MachineryXYService;
import com.onemap.utl.common.QueryPeriodUtil;

@Controller
@RequestMapping("/farmmachinery")
public class FarmMachineryController extends
		BaseController<FarmMachinery, Integer> {
	@Autowired
	private FarmMachineryService service;
	@Autowired
	private FarmMachineryGPSService gpsService;
	@Autowired
	private MachineryXYService machineryXYservice;

	@Override
	BaseService<FarmMachinery, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
		
	}
	
//	@RequestMapping(value = "edit",  method = RequestMethod.GET)
//	public ModelAndView edit(FarmMachinery t) throws Exception {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		if (t.getId() == null) {
//			return modelAndView;
//		}
//		FarmMachinery tInDb = getBaseService().get(t.getId());
//
//		modelAndView.addObject("currentObj", tInDb);
//		addDataForEditView(modelAndView);
//
//		return modelAndView;
//	}
	
	@RequestMapping("chartsearchcharts")
	public ModelAndView charts(FarmMachinery t) throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("cropsearchcharts")
	public ModelAndView cropcharts(FarmMachinery t) throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("investsearchcharts")
	public ModelAndView investsearchcharts(FarmMachinery t) throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/csv", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getData(FarmMachinery t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<FarmMachinery> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");
		
		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<FarmMachinery> iterator = tList.iterator(); iterator
					.hasNext();) {
				FarmMachinery farmMachinery = (FarmMachinery) iterator
						.next();
//				String operationType = farmMachinery.getOperationType();
//				data.append(OperationUtil.getOperationValue(operationType));				
//				
//				Date operationTime = FarmMachinery.getOperationTime();
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				String formattedTime = sdf.format(operationTime);
//				time.append("'").append(formattedTime).append("'");
				
				if(iterator.hasNext()){
					data.append(",");
					time.append(",");
				}
			}
			data.append("]");
			time.append("]");
			sb.append(data).append(",").append(time);
		}
		
		sb.append("}");
//		sb.append(formattedTime);
		// "4.3, 5.1, 4.3, 5.2, 7.4, 4.7, 3.5,8.2, 9, 10, 14,13";
		return sb.toString();
	}
	
	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/crops", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getCropsData(FarmMachinery t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<FarmMachinery> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");
		
		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<FarmMachinery> iterator = tList.iterator(); iterator
					.hasNext();) {
				FarmMachinery FarmMachinery = (FarmMachinery) iterator
						.next();
//				String operationType = FarmMachinery.getOperationType();
//				data.append(OperationUtil.getOperationValue(operationType));				
//				
//				Date operationTime = FarmMachinery.getOperationTime();
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				String formattedTime = sdf.format(operationTime);
//				time.append("'").append(formattedTime).append("'");
				
				if(iterator.hasNext()){
					data.append(",");
					time.append(",");
				}
			}
			data.append("]");
			time.append("]");
			sb.append(data).append(",").append(time);
		}
		
		sb.append("}");
//		sb.append(formattedTime);
		// "4.3, 5.1, 4.3, 5.2, 7.4, 4.7, 3.5,8.2, 9, 10, 14,13";
		return sb.toString();
	}
	
	@RequestMapping("chartsearch")
	public ModelAndView chartsearch(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping("chartsearc")
	public ModelAndView chartsearc(HttpServletRequest request, FarmMachinery t)
			throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if (t != null) {
			String[] query = QueryPeriodUtil.getPeriod(request);
			t.setQueryBeginTime(query[0]);
			t.setQueryEndTime(query[1]);			
		}
		
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("njyilan")
	public ModelAndView njyilan(HttpServletRequest request, FarmMachinery t)
			throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if (t != null) {
			String[] query = QueryPeriodUtil.getPeriod(request);
			t.setQueryBeginTime(query[0]);
			t.setQueryEndTime(query[1]);			
		}
		
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("track")
	public ModelAndView track(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	@RequestMapping("trac")
	public ModelAndView trac(HttpServletRequest request, FarmMachinery t)
			throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if (t != null) {
			String[] query = QueryPeriodUtil.getPeriod(request);
			t.setQueryBeginTime(query[0]);
			t.setQueryEndTime(query[1]);			
		}
		System.out.println(t);
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("barrier")
	public ModelAndView barrier(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	@RequestMapping("barrie")
	public ModelAndView barrie(HttpServletRequest request, FarmMachinery t)
			throws Exception {
		List<FarmMachinery> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if (t != null) {
			String[] query = QueryPeriodUtil.getPeriod(request);
			t.setQueryBeginTime(query[0]);
			t.setQueryEndTime(query[1]);			
		}
		System.out.println(t);
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	
	@RequestMapping("cropsearch")
	public ModelAndView addOrUpdate2(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping("investsearch")
	public ModelAndView addOrUpdate3(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping(value = "machineryOperation/{id}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsDataByBlock(@PathVariable("id") Integer machineryOperationId)
			throws Exception {
		System.out.println("search by " + machineryOperationId + ",");
		FarmMachinery farmMachinery = getBaseService().get(machineryOperationId);
		
		MachineryXY t = new MachineryXY();
		t.setMachineryOperationId(machineryOperationId);
		List<MachineryXY> tList = this.machineryXYservice.list(t);
		// POLYGON((20.828125 -10.3515625, 0 0,132.1875 -13.0078125, -1.40625
		// -59.4140625, 28.828125 0.3515625))
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		
		if (tList != null) {
			data.append("data:[");
			StringBuffer item = new StringBuffer("LINESTRING(");
			for (Iterator<MachineryXY> iterator = tList.iterator(); iterator
					.hasNext();) {
				MachineryXY xy = iterator.next();
				Double x = xy.getPositionX();
				Double y = xy.getPositionY();
				item.append(x).append(" ").append(y);
				if (iterator.hasNext()) {
					item.append(",");
				}
			}
			item.append(")");
			data.append("{item:'"+item.toString()+"',id:'"+machineryOperationId+"',machineryNumber:'"+farmMachinery.getMachineryNumber()+"'}");
			data.append("]");
			sb.append(data);
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	@RequestMapping(value = "cooperative/{cooperativeId}/operation/{operationTime}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsByCooperativeIdAndOperationTime(@PathVariable("cooperativeId") Integer cooperativeId,
			@PathVariable("operationTime") Long operationTime)
			throws Exception {
		System.out.println("search by " + cooperativeId + "," + operationTime);
		FarmMachinery searchFarmMachinery = new FarmMachinery();
		searchFarmMachinery.setCooperativeId(cooperativeId);
		Date _operationTime = new Date(operationTime);
		searchFarmMachinery.setOperationTime(_operationTime);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		data.append("data:[");
		List<FarmMachinery> list = getBaseService().list(searchFarmMachinery);
		if(list != null){
			for (Iterator<FarmMachinery> iterator = list.iterator(); iterator.hasNext();) {
				FarmMachinery farmMachinery = (FarmMachinery) iterator.next();

				MachineryXY t = new MachineryXY();
				t.setMachineryOperationId(farmMachinery.getId());
				List<MachineryXY> tList = this.machineryXYservice.list(t);				
				
				if (tList != null) {
					
					StringBuffer item = new StringBuffer("LINESTRING(");
					for (Iterator<MachineryXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						MachineryXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						item.append(x).append(" ").append(y);
						if (iterator2.hasNext()) {
							item.append(",");
						}
					}
					item.append(")");
					data.append("{item:'"+item.toString()+"',id:'"+farmMachinery.getId()
							+"',machineryNumber:'"+farmMachinery.getMachineryNumber()
							+"',machineryType:'"+farmMachinery.getMachineryType()
							+"',cooperative:'"+farmMachinery.getCooperative().getCooperativeNumber()
							+"',status:'"+farmMachinery.getStatus()
							+"',username:'"+farmMachinery.getOwnerName()
							+"',tel:'"+farmMachinery.getOwnerTelephone()+"'}");
					if(iterator.hasNext()){
						data.append(",");
					}
				}
			}//end for parent loop
			data.append("]");
			sb.append(data);
		}
		sb.append("}");
		return sb.toString();
	}
	
	
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ModelAndView show(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() != null) {
			FarmMachinery tInDb = this.getBaseService().get(t.getId());
			this.service.fillDetail(tInDb);
			modelAndView.addObject("currentObj", tInDb);
			return modelAndView;
		}
		if (t.getLicensenumber() != null) {
			String filterValue = new String(
					t.getLicensenumber().getBytes("iso-8859-1"), "UTF-8");
			t.setLicensenumber(filterValue);
			List<FarmMachinery> tInDbList = this.getBaseService()
					.listByLimit(t);
			if (tInDbList != null && tInDbList.size() > 0) {
				FarmMachinery tInDb = tInDbList.get(0);
				this.service.fillDetail(tInDb);
				modelAndView.addObject("currentObj",tInDb);
				return modelAndView;
			}
		}

		return modelAndView;
	}
	
	
	@RequestMapping(value = "showMachineinfo", method = RequestMethod.GET)
	public ModelAndView showMachineinfo(FarmMachinery t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() != null) {
			FarmMachinery tInDb = this.getBaseService().get(t.getId());
			this.service.fillDetail(tInDb);
			modelAndView.addObject("currentObj", tInDb);
			return modelAndView;
		}
		if (t.getLicensenumber() != null) {
			String filterValue = new String(
					t.getLicensenumber().getBytes("iso-8859-1"), "UTF-8");
			t.setLicensenumber(filterValue);
			List<FarmMachinery> tInDbList = this.getBaseService()
					.listByLimit(t);
			if (tInDbList != null && tInDbList.size() > 0) {
				FarmMachinery tInDb = tInDbList.get(0);
				this.service.fillDetail(tInDb);
				modelAndView.addObject("currentObj",tInDb);
				return modelAndView;
			}
		}

		return modelAndView;
	}
	
	@RequestMapping("chartsear")
	public ModelAndView nongjifenbu(Landblock b) {
		ModelAndView modelAndView = new ModelAndView();		
		return modelAndView;
	}
	@RequestMapping("tra")
	public ModelAndView tra(Landblock b) {
		ModelAndView modelAndView = new ModelAndView();		
		return modelAndView;
	}	
	
	@RequestMapping(value = "check", method = RequestMethod.POST, produces = "text/javascript")
	@ResponseBody
	public JSONObject check(
			@RequestParam("licensenumber") String licensenumber,
			@RequestParam("city1") String city,
			@RequestParam("province") String province,
			@RequestParam("district") String district) throws Exception {
		System.out.println(licensenumber);
		System.out.println(province + ", city:" + city+", district:" + district);
		JSONObject response = new JSONObject();
		JSONArray array = new JSONArray();
		FarmMachineryGPS t = new FarmMachineryGPS();
		t.setLicensenumber(licensenumber);
		List<FarmMachineryGPS> trails = gpsService.list(t);
		if(trails!=null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (FarmMachineryGPS farmMachineryGPS : trails) {
				double x = farmMachineryGPS.getPositionX();
				double y = farmMachineryGPS.getPositionY();
				JSONObject obj = GeocodingService.getInstance().getMatchedObject(x, y, province, city, district);
				if(obj != null){
					obj.put("operationTime", df.format(farmMachineryGPS.getOperationTime()));
					array.add(obj);
				}
			}
		}
		response.put("result", array);
		return response;
	}
}
