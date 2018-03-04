package com.onemap.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.FarmMachineryGPS;
import com.onemap.domain.MachineryXY;
import com.onemap.service.BaseService;
import com.onemap.service.FarmMachineryGPSService;
import com.onemap.service.FarmMachineryService;
import com.onemap.service.MachineryXYService;

@Controller
@RequestMapping("/farmmachinerygps")
public class FarmMachineryGPSController extends
		BaseController<FarmMachineryGPS, Integer> {
	@Autowired
	private FarmMachineryGPSService service;
	@Autowired
	private FarmMachineryService fmService;
	@Autowired
	private MachineryXYService machineryXYservice;

	@Override
	BaseService<FarmMachineryGPS, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(FarmMachineryGPS t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		FarmMachineryGPS tInDb = getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("chartsearchcharts")
	public ModelAndView charts(FarmMachineryGPS t) throws Exception {
		List<FarmMachineryGPS> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("cropsearchcharts")
	public ModelAndView cropcharts(FarmMachineryGPS t) throws Exception {
		List<FarmMachineryGPS> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("investsearchcharts")
	public ModelAndView investsearchcharts(FarmMachineryGPS t) throws Exception {
		List<FarmMachineryGPS> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/csv", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getData(FarmMachineryGPS t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<FarmMachineryGPS> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");

		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<FarmMachineryGPS> iterator = tList.iterator(); iterator
					.hasNext();) {
				FarmMachineryGPS farmMachineryGPS = (FarmMachineryGPS) iterator
						.next();
				// String operationType = FarmMachineryGPS.getOperationType();
				// data.append(OperationUtil.getOperationValue(operationType));
				//
				// Date operationTime = FarmMachineryGPS.getOperationTime();
				// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				// String formattedTime = sdf.format(operationTime);
				// time.append("'").append(formattedTime).append("'");

				if (iterator.hasNext()) {
					data.append(",");
					time.append(",");
				}
			}
			data.append("]");
			time.append("]");
			sb.append(data).append(",").append(time);
		}

		sb.append("}");
		// sb.append(formattedTime);
		// "4.3, 5.1, 4.3, 5.2, 7.4, 4.7, 3.5,8.2, 9, 10, 14,13";
		return sb.toString();
	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/crops", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getCropsData(FarmMachineryGPS t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<FarmMachineryGPS> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");

		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<FarmMachineryGPS> iterator = tList.iterator(); iterator
					.hasNext();) {
				FarmMachineryGPS farmMachineryGPS = (FarmMachineryGPS) iterator
						.next();
				// String operationType = FarmMachineryGPS.getOperationType();
				// data.append(OperationUtil.getOperationValue(operationType));
				//
				// Date operationTime = FarmMachineryGPS.getOperationTime();
				// SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				// String formattedTime = sdf.format(operationTime);
				// time.append("'").append(formattedTime).append("'");

				if (iterator.hasNext()) {
					data.append(",");
					time.append(",");
				}
			}
			data.append("]");
			time.append("]");
			sb.append(data).append(",").append(time);
		}

		sb.append("}");
		// sb.append(formattedTime);
		// "4.3, 5.1, 4.3, 5.2, 7.4, 4.7, 3.5,8.2, 9, 10, 14,13";
		return sb.toString();
	}

	@RequestMapping("chartsearch")
	public ModelAndView addOrUpdate(FarmMachineryGPS t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("cropsearch")
	public ModelAndView addOrUpdate2(FarmMachineryGPS t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("investsearch")
	public ModelAndView addOrUpdate3(FarmMachineryGPS t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping(value = "machineryOperation/{id}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsDataByBlock(
			@PathVariable("id") Integer machineryOperationId) throws Exception {
		System.out.println("search by " + machineryOperationId + ",");
		FarmMachineryGPS farmMachineryGPS = getBaseService().get(
				machineryOperationId);

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
			data.append("{item:'" + item.toString() + "',id:'"
					+ machineryOperationId + "',machineryNumber:'"
					+ farmMachineryGPS.getMachineryNumber() + "'}");
			data.append("]");
			sb.append(data);
		}

		sb.append("}");
		return sb.toString();
	}

	@RequestMapping(value = "cooperative/{cooperativeId}/operation/{operationTime}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsByCooperativeIdAndOperationTime(
			@PathVariable("cooperativeId") Integer cooperativeId,
			@PathVariable("operationTime") Long operationTime) throws Exception {
		System.out.println("search by " + cooperativeId + "," + operationTime);
		FarmMachineryGPS searchFarmMachinery = new FarmMachineryGPS();
		searchFarmMachinery.setCooperativeId(cooperativeId);
		Date _operationTime = new Date(operationTime);
		searchFarmMachinery.setOperationTime(_operationTime);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		data.append("data:[");
		List<FarmMachineryGPS> list = getBaseService()
				.list(searchFarmMachinery);
		if (list != null) {
			for (Iterator<FarmMachineryGPS> iterator = list.iterator(); iterator
					.hasNext();) {
				FarmMachineryGPS farmMachinery = (FarmMachineryGPS) iterator
						.next();

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
					data.append("{item:'" + item.toString()
							+ "',id:'"
							+ farmMachinery.getId()
							+ "',machineryNumber:'"
							+ farmMachinery.getMachineryNumber()
							// +"',machineryType:'"+farmMachinery.getMachineryType()
							+ "',cooperative:'"
							/*+ farmMachinery.getCooperative()
									.getCooperativeNumber()*/
							// +"',status:'"+farmMachinery.getStatus()
							// +"',username:'"+farmMachinery.getOwnerName()
							/* +"',tel:'"+farmMachinery.getOwnerTelephone()+"'}" */+ "'}");
					if (iterator.hasNext()) {
						data.append(",");
					}
				}
			}// end for parent loop
			data.append("]");
			sb.append(data);
		}
		sb.append("}");
		return sb.toString();
	}

	@RequestMapping(value = "/fenbu", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<FarmMachineryGPS> fenbu(FarmMachineryGPS t) throws Exception {	
//		System.out.println(t);
		if (t.getLicensenumber() != null) {
			String filterValue = new String(
					t.getLicensenumber().getBytes("iso-8859-1"), "UTF-8");
			t.setLicensenumber(filterValue);
		}
		List<FarmMachineryGPS> list = this.service.listByRecent(t);
//		if (list != null) {
//			for (FarmMachineryGPS farmMachineryGPS : list) {
//				String gpsdevice = farmMachineryGPS.getGpsdevice();
//				FarmMachinery fm = fmService.getLicensenumberByGps(gpsdevice);
//				if (fm != null) {
//					farmMachineryGPS.setLicensenumber(fm.getLicensenumber());
//				}
//			}
//		}
		return list;
	}
	
	@RequestMapping(value = "licensenumber/{licensenumber}/period/{period}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrails(
			@PathVariable("licensenumber") String licensenumber,
			@PathVariable("period") String period) throws Exception {
		
		JSONObject result = new JSONObject();
		JSONArray data = new JSONArray();
		result.put("data", data);
		FarmMachineryGPS t = new FarmMachineryGPS();
		if(licensenumber != null){
			String filterValue = new String(
					licensenumber.getBytes("iso-8859-1"), "UTF-8");
			t.setLicensenumber(filterValue);
		}
//		System.out.println(t.getLicensenumber() + "," + period);
		List<FarmMachineryGPS> tList = new ArrayList<FarmMachineryGPS>();
		if(period != null){
//			System.out.println(t.getLicensenumber() + "," + period);
			if(period.equals("7")){
				tList = this.service.listBefore1W(t);
			}else if(period.equals("30")){
				tList = this.service.listBefore1M(t);
			}else if(period.equals("180")){
				tList = this.service.listBefore6M(t);				
			}
		}		
	
		if (tList != null) {
			for (Iterator<FarmMachineryGPS> iterator = tList.iterator(); iterator
					.hasNext();) {
				FarmMachineryGPS gps = iterator.next();
				JSONObject obj = new JSONObject();
				obj.put("x", gps.getPositionX());
				obj.put("y", gps.getPositionY());
				data.add(obj);
			}
		}

		return result;
	}
}
