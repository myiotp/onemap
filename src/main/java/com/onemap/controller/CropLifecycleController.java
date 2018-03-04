package com.onemap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.CropLifecycle;
import com.onemap.domain.CropType;
import com.onemap.domain.Landblock;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.CropLifecycleService;
import com.onemap.service.LandblockService;
import com.onemap.service.SiteService;
import com.onemap.utl.common.OperationUtil;
import com.onemap.utl.common.QueryPeriodUtil;
import com.onemap.utl.pages.PaginatedArrayList;

@Controller
@RequestMapping("/croplifecycle")
public class CropLifecycleController extends
		BaseController<CropLifecycle, Integer> {
	@Autowired
	private CropLifecycleService service;
	@Autowired
	private SiteService siteService;
	@Autowired
	private LandblockService landblockService;

	@Override
	BaseService<CropLifecycle, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(CropLifecycle t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		CropLifecycle tInDb = getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("charts")
	public ModelAndView charts(HttpServletRequest request, CropLifecycle t)
			throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);

		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("overview")
	public ModelAndView overview(HttpServletRequest request,
			CropLifecycle t) throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);

		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("chart")
	public ModelAndView chart(HttpServletRequest request, CropLifecycle t)
			throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);

		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	
	@RequestMapping("growStatus")
	public ModelAndView growStatus(HttpServletRequest request, CropLifecycle t)
			throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);
		
		tList = getBaseService().list(t);
		
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("chartsearc")
	public ModelAndView chartssearc(HttpServletRequest request, Site site)
			throws Exception {
		String cooperativeId = request.getParameter("cooperativeId");
		if (cooperativeId != null) {
			site = siteService.get(Integer.parseInt(cooperativeId));
		}
		ModelAndView modelAndView = new ModelAndView("croplifecycle/chartsearc");
		if (site != null) {
			modelAndView.addObject("id", site.getId());
			modelAndView.addObject("x", site.getGpsx());
			modelAndView.addObject("y", site.getGpsy());
		}
		return modelAndView;
	}

	@RequestMapping("cropsearchcharts")
	public ModelAndView cropcharts(CropLifecycle t) throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("investsearchcharts")
	public ModelAndView investsearchcharts(CropLifecycle t) throws Exception {
		List<CropLifecycle> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropTypeId}/charts/csv", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getData(HttpServletRequest request,
			@PathVariable("blockNumber") Integer blockId,
			@PathVariable("cropTypeId") Integer cropTypeId) throws Exception {
		// System.out.println(blockId + "," + cropTypeId);
		JSONObject sb = new JSONObject();
		Landblock landblock = landblockService.get(blockId);
		Integer cooperativeId = landblock.getCooperativeId();
		sb.put("cooperativeId", cooperativeId);
		sb.put("cooperativeName", landblock.getCooperative() == null ? ""
				: landblock.getCooperative().getCooperativeName());
		sb.put("blockNumber", landblock.getBlockNumber());

		CropLifecycle t = new CropLifecycle();
		t.setBlockId(blockId);
		t.setCropTypeId(cropTypeId);

		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);

		List<CropLifecycle> tList = getBaseService().list(t);
		JSONArray data = new JSONArray();
		sb.put("data", data);
		JSONArray operationType = new JSONArray();
		sb.put("operationType", operationType);
		JSONArray sbTemp = new JSONArray();
		sb.put("sbTemp", sbTemp);
		JSONArray sbPressure = new JSONArray();
		sb.put("sbPressure", sbPressure);
		JSONArray sbRainfall = new JSONArray();
		sb.put("sbRainfall", sbRainfall);
		JSONArray sbWindSpeed = new JSONArray();
		sb.put("sbWindSpeed", sbWindSpeed);
		JSONArray sbSoilTemperature = new JSONArray();
		sb.put("sbSoilTemperature", sbSoilTemperature);
		JSONArray sbSoilHumidity = new JSONArray();
		sb.put("sbSoilHumidity", sbSoilHumidity);
		JSONArray time = new JSONArray();
		sb.put("time", time);
		JSONArray id = new JSONArray();
		sb.put("id", id);
		JSONArray type = new JSONArray();
		sb.put("type", type);
		Date beginDate = null;
		Date endDate = null;
		if (tList != null) {
			int index = 0;
			for (Iterator<CropLifecycle> iterator = tList.iterator(); iterator
					.hasNext();) {
				CropLifecycle cropLifecycle = (CropLifecycle) iterator.next();
				Double humidity = cropLifecycle.getHumidity();

				operationType.add(cropLifecycle.getVideo());
				data.add(OperationUtil.getLifecycleValue(cropLifecycle
						.getVideo()));
				id.add(cropLifecycle.getId());
				type.add(OperationUtil.getLifecycleType(cropLifecycle
						.getVideo()));

				Double temp = cropLifecycle.getTemperature();
				sbTemp.add(temp);
				Double pressure = cropLifecycle.getPressure();
				sbPressure.add(pressure);
				Double rainfall = cropLifecycle.getRainfall();
				sbRainfall.add(rainfall);

				sbWindSpeed.add(cropLifecycle.getWindSpeed());
				sbSoilTemperature.add(cropLifecycle.getSoilTemperature());
				sbSoilHumidity.add(cropLifecycle.getSoilHumidity());

				Date operationTime = cropLifecycle.getGatherTime();
				if(index == 0){
					beginDate = operationTime;
					endDate = operationTime;
					index++;
				}
//				if(!iterator.hasNext()){
//					endDate = operationTime;
//				}
				if(beginDate!= null && beginDate.after(operationTime)){
					beginDate = operationTime;
				}
				if(endDate!= null && endDate.before(operationTime)){
					endDate = operationTime;
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String formattedTime = sdf.format(operationTime);
				time.add(formattedTime);
			}

		}
		if(beginDate != null && endDate != null){
			long diff = endDate.getTime() - beginDate.getTime();
//		    long days = diff / (1000 * 60 * 60 * 24);
		    long newtime = beginDate.getTime() - diff/4;	
		    sb.put("newtime", newtime);
		}
		return sb;
	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/crops", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getCropsData(CropLifecycle t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<CropLifecycle> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");

		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<CropLifecycle> iterator = tList.iterator(); iterator
					.hasNext();) {
				CropLifecycle CropLifecycle = (CropLifecycle) iterator.next();
				// String operationType = CropLifecycle.getOperationType();
				// data.append(OperationUtil.getOperationValue(operationType));
				//
				// Date operationTime = CropLifecycle.getOperationTime();
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

	@RequestMapping(value = "block/{id}/crop/{cropTypeId}/env", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getEnvDataByBlock(@PathVariable("id") Integer blockId,
			@PathVariable("cropTypeId") Integer cropTypeId) throws Exception {
		System.out.println(blockId + "," + cropTypeId);
		CropLifecycle t = new CropLifecycle();
		t.setBlockId(blockId);
		if (cropTypeId != null && cropTypeId.intValue() > 0) {
			t.setCropTypeId(cropTypeId);
		}
		CropLifecycle obj = this.service.getLatest(t);
		StringBuffer sb = new StringBuffer("{");
		if (obj != null) {
			sb.append("cropTypeId:'").append(obj.getCropTypeId()).append("',");
			sb.append("cropType:'").append(obj.getCropType()).append("',");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sb.append("gatherTime:'").append(sdf.format(obj.getGatherTime()))
					.append("',");
			sb.append("humidity:'").append(obj.getHumidity()).append("',");
			sb.append("leafArea:'").append(obj.getLeafArea()).append("',");
			sb.append("leafGreen:'").append(obj.getLeafGreen()).append("',");
			sb.append("pressure:'").append(obj.getPressure()).append("',");
			sb.append("radiation:'").append(obj.getRadiation()).append("',");
			sb.append("rainfall:'").append(obj.getRainfall()).append("',");
			sb.append("windDir:'").append(obj.getWindDirection()).append("',");
			sb.append("windSpeed:'").append(obj.getWindSpeed()).append("',");
			sb.append("soilHumidity:'").append(obj.getSoilHumidity())
					.append("',");
			sb.append("npk:'").append(obj.getSoilN()).append("/")
					.append(obj.getSoilP()).append("/").append(obj.getSoilK())
					.append("',");
			sb.append("soilTemp:'").append(obj.getSoilTemperature())
					.append("',");
			sb.append("temp:'").append(obj.getTemperature()).append("'");

		}
		sb.append("}");
		return sb.toString();
	}

	@RequestMapping("chartsearch")
	public ModelAndView addOrUpdate(CropLifecycle t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("cropsearch")
	public ModelAndView addOrUpdate2(CropLifecycle t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("investsearch")
	public ModelAndView addOrUpdate3(CropLifecycle t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping(value = "block/{blockNumber}/crops", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public Object getData(HttpServletRequest request,
			@PathVariable("blockNumber") Integer blockId) throws Exception {
		// System.out.println(blockId + "," + cropTypeId);
		CropLifecycle t = new CropLifecycle();
		t.setBlockId(blockId);

		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);

		List<CropLifecycle> tList = getBaseService().list(t);
		Map<Integer, CropType> result = new HashMap<Integer, CropType>();
		if (tList != null) {
			for (Iterator<CropLifecycle> iterator = tList.iterator(); iterator
					.hasNext();) {
				CropLifecycle cropLifecycle = (CropLifecycle) iterator.next();
				if (result.containsKey(cropLifecycle.getCropTypeId()))
					continue;
				CropType cropType = new CropType();
				cropType.setId(cropLifecycle.getCropTypeId());
				cropType.setTypename(cropLifecycle.getCropType());
				result.put(cropLifecycle.getCropTypeId(), cropType);
			}
		}

		return result.values();
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public CropLifecycle getCertJson(@PathVariable("id") Integer id)
			throws Exception {
		CropLifecycle t = new CropLifecycle();
		t.setId(id);
		PaginatedArrayList<CropLifecycle> tInDbList = this.getBaseService()
				.listByLimit(t);
		if (tInDbList != null && tInDbList.size() > 0) {
			return tInDbList.get(0);
		}
		return null;
	}
}
