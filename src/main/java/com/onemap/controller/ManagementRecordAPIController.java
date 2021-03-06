package com.onemap.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.onemap.api.OneMapClient;
import com.onemap.api.OneMapResponse;
import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Clause;
import com.onemap.domain.CropType;
import com.onemap.domain.Goods;
import com.onemap.domain.Landblock;
import com.onemap.domain.ManagementRecord;
import com.onemap.domain.NGTableObject;
import com.onemap.domain.Site;
import com.onemap.service.BaseService;
import com.onemap.service.GoodsService;
import com.onemap.service.LandblockService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.SiteService;
import com.onemap.utl.common.FileUtil;
import com.onemap.utl.common.OperationUtil;
import com.onemap.utl.common.QueryPeriodUtil;
import com.onemap.utl.common.ShortUrlUtil;
import com.onemap.utl.pages.PaginatedArrayList;

@Controller
@RequestMapping("/api/managementrecord")
public class ManagementRecordAPIController extends
		BaseController<ManagementRecord, Integer> {
	@Autowired
	private ManagementRecordService service;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private SiteService siteService;

	BaseService<ManagementRecord, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<ManagementRecord> ngtable(ManagementRecord t, HttpServletRequest request)
			throws Exception {
		List<ManagementRecord> tList = null;
		Integer count = new Integer(10);
		count = Integer.parseInt(request.getParameter("count"));
		Integer page = new Integer(1);
		page = Integer.parseInt(request.getParameter("page"));
		t.setPageSize(count);
		t.setStartPos(count * (page - 1) + 1);
		
		Enumeration parameterNames = request.getParameterNames();
		List<Clause> whereClause = new ArrayList<Clause>();
		while (parameterNames.hasMoreElements()) {
			Object _paraName = parameterNames.nextElement();
			if (_paraName != null) {
				String paraName = (String) _paraName;
				if (paraName.startsWith("sorting[") && paraName.endsWith("]")) {
					String orderBy = paraName.substring("sorting[".length(),
							paraName.length() - 1);
					t.setOrderBy(orderBy);
					String paraValue = request.getParameter(paraName);
					t.setOrder(paraValue == null ? "asc" : paraValue);
				}
				if (paraName.startsWith("filter[") && paraName.endsWith("]")) {
					String filter = paraName.substring("filter[".length(),
							paraName.length() - 1);
					if ("cooperativeId".equals(filter)) {
						String filterValue = request.getParameter(paraName);
						if (filterValue == null || filterValue.equals("")) {
							// ingore this
						} else {
							Clause clause = new Clause();
							clause.setColumn(filter);
							clause.setOperator(" = ");
							clause.setValue(filterValue);
							whereClause.add(clause);
						}
					} else {
						String filterValue = request.getParameter(paraName);
						// System.out.println("==="+filterValue);
						filterValue = (filterValue == null) ? "" : new String(
								filterValue.getBytes("iso-8859-1"), "UTF-8");
						// System.out.println("==="+filterValue);
						Clause clause = new Clause();
						clause.setColumn(filter);
						clause.setOperator("like");
						clause.setValue("%" + filterValue + "%");
						whereClause.add(clause);
					}
				}
			}
		}

		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}
		// System.out.println("t:" + t);
		tList = getBaseService().listByLimit(t, request);
		// System.out.println("tList:" + tList);
		for (ManagementRecord item : tList) {
			String item1 = item.getItem1();
			double price1 = item.getPrice1();
			String item2 = item.getItem2();
			double price2 = item.getPrice2();
			String item3 = item.getItem3();
			double price3 = item.getPrice3();
			String item4 = item.getItem4();
			double price4 = item.getPrice4();
			String item5 = item.getItem5();
			double price5 = item.getPrice5();
			double totalPrice = 0;
			StringBuffer sb = new StringBuffer();
			if(price1 > 0) {
				sb.append(item1).append(":").append(price1).append("\n");
				totalPrice +=price1;
			}
			if(price2 > 0 ) {
				sb.append(item2).append(":").append(price2).append("\n");
				totalPrice +=price2;
			}
			if(price3 > 0 ) {
				sb.append(item3).append(":").append(price3).append("\n");
				totalPrice +=price3;
			}
			if(price4 > 0 ) {
				sb.append(item4).append(":").append(price4).append("\n");
				totalPrice +=price4;
			}
			if(price5 > 0 ) {
				sb.append(item5).append(":").append(price5).append("\n");
				totalPrice +=price5;
			}
			item.setItem1(sb.toString());
			double price = 0;
			Goods goods = goodsService.get(item.getCargoId());
			if(goods!=null) {
				price = goods.getPrice();
			}
			item.setPrice1(price);
			if(Math.abs(totalPrice-price) < 0.1) {
				//ok
				item.setItem2("金额匹配");
				item.setPrice2(0);
			} else {
				item.setItem2("金额不匹配");
				item.setPrice2(-1);
			}
			int status = item.getStatus(); 
			if(status == 1) {
				item.setItem3("已确认交易");
			} else if(status == -1) {
				item.setItem3("已拒绝交易");
			} else {
				item.setItem3("进行中");
			}
		}
		int totalCount = getBaseService().count(t);
		NGTableObject<ManagementRecord> result = new NGTableObject<>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}
	@RequestMapping(value = "ngtable2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<ManagementRecord> ngtable2(ManagementRecord t, HttpServletRequest request)
			throws Exception {
		List<ManagementRecord> tList = null;
		Integer count = new Integer(10);
		count = Integer.parseInt(request.getParameter("count"));
		Integer page = new Integer(1);
		page = Integer.parseInt(request.getParameter("page"));
		t.setPageSize(count);
		t.setStartPos(count * (page - 1) + 1);
		
		Enumeration parameterNames = request.getParameterNames();
		List<Clause> whereClause = new ArrayList<Clause>();
		while (parameterNames.hasMoreElements()) {
			Object _paraName = parameterNames.nextElement();
			if (_paraName != null) {
				String paraName = (String) _paraName;
				if (paraName.startsWith("sorting[") && paraName.endsWith("]")) {
					String orderBy = paraName.substring("sorting[".length(),
							paraName.length() - 1);
					t.setOrderBy(orderBy);
					String paraValue = request.getParameter(paraName);
					t.setOrder(paraValue == null ? "asc" : paraValue);
				}
				if (paraName.startsWith("filter[") && paraName.endsWith("]")) {
					String filter = paraName.substring("filter[".length(),
							paraName.length() - 1);
					if ("cooperativeId".equals(filter)) {
						String filterValue = request.getParameter(paraName);
						if (filterValue == null || filterValue.equals("")) {
							// ingore this
						} else {
							Clause clause = new Clause();
							clause.setColumn(filter);
							clause.setOperator(" = ");
							clause.setValue(filterValue);
							whereClause.add(clause);
						}
					} else {
						String filterValue = request.getParameter(paraName);
						// System.out.println("==="+filterValue);
						filterValue = (filterValue == null) ? "" : new String(
								filterValue.getBytes("iso-8859-1"), "UTF-8");
						// System.out.println("==="+filterValue);
						Clause clause = new Clause();
						clause.setColumn(filter);
						clause.setOperator("like");
						clause.setValue("%" + filterValue + "%");
						whereClause.add(clause);
					}
				}
			}
		}

		Clause c1 = new Clause();
		c1.setColumn("status");
		c1.setOperator("=");
		c1.setValue(1);
		whereClause.add(c1);
		
		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}
		// System.out.println("t:" + t);
		tList = getBaseService().listByLimit(t, request);
		// System.out.println("tList:" + tList);
		for (ManagementRecord item : tList) {
			String item1 = item.getItem1();
			double price1 = item.getPrice1();
			String item2 = item.getItem2();
			double price2 = item.getPrice2();
			String item3 = item.getItem3();
			double price3 = item.getPrice3();
			String item4 = item.getItem4();
			double price4 = item.getPrice4();
			String item5 = item.getItem5();
			double price5 = item.getPrice5();
			double totalPrice = 0;
			StringBuffer sb = new StringBuffer();
			if(price1 > 0) {
				sb.append(item1).append(":").append(price1).append("\n");
				totalPrice +=price1;
			}
			if(price2 > 0 ) {
				sb.append(item2).append(":").append(price2).append("\n");
				totalPrice +=price2;
			}
			if(price3 > 0 ) {
				sb.append(item3).append(":").append(price3).append("\n");
				totalPrice +=price3;
			}
			if(price4 > 0 ) {
				sb.append(item4).append(":").append(price4).append("\n");
				totalPrice +=price4;
			}
			if(price5 > 0 ) {
				sb.append(item5).append(":").append(price5).append("\n");
				totalPrice +=price5;
			}
			item.setItem1(sb.toString());
			double price = 0;
			Goods goods = goodsService.get(item.getCargoId());
			if(goods!=null) {
				price = goods.getPrice();
			}
			item.setPrice1(price);
			if(Math.abs(totalPrice-price) < 0.1) {
				//ok
				item.setItem2("金额匹配");
				item.setPrice2(0);
			} else {
				item.setItem2("金额不匹配");
				item.setPrice2(-1);
			}
			int status = item.getStatus(); 
			if(status == 1) {
				item.setItem3("已确认交易");
			} else if(status == -1) {
				item.setItem3("已拒绝交易");
			} else {
				item.setItem3("进行中");
			}
		}
		int totalCount = getBaseService().count(t);
		NGTableObject<ManagementRecord> result = new NGTableObject<>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}
	

//	@RequestMapping(value = "edit", method = RequestMethod.GET)
//	public ModelAndView edit(ManagementRecord t) throws Exception {
//
//		ModelAndView modelAndView = new ModelAndView();
//		if (t.getId() == null) {
//			return modelAndView;
//		}
//		ManagementRecord tInDb = getBaseService().get(t.getId());
//
//		modelAndView.addObject("currentObj", tInDb);
//		addDataForEditView(modelAndView);
//
//		return modelAndView;
//	}

	@RequestMapping("editlist")
	public ModelAndView editlist(HttpServletRequest request, ManagementRecord t)
			throws Exception {
		List<ManagementRecord> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	@RequestMapping("alleditlist")
	public ModelAndView alleditlist(HttpServletRequest request, ManagementRecord t)
			throws Exception {
		List<ManagementRecord> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	@RequestMapping(value = "chart", method = RequestMethod.GET)
	public ModelAndView showTrail(ManagementRecord t) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		ManagementRecord tInDb = getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		// if (tInDb != null) {
		// modelAndView.addObject("id", tInDb.get);
		// modelAndView.addObject("x", site.getGpsx());
		// modelAndView.addObject("y", site.getGpsy());
		// }
		return modelAndView;
	}

	@RequestMapping("process")
	public ModelAndView process(HttpServletRequest request, ManagementRecord t)
			throws Exception {
		List<ManagementRecord> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("investsearchcharts")
	public ModelAndView investsearchcharts(HttpServletRequest request,
			ManagementRecord t) throws Exception {
		List<ManagementRecord> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

//	@RequestMapping(value = "block/{blockId}/crop/{cropTypeId}/charts/csv", method = RequestMethod.GET, produces = "text/javascript")
//	@ResponseBody
//	public JSONObject getData(HttpServletRequest request, ManagementRecord t,
//			@PathVariable("blockId") Integer blockId,
//			@PathVariable("cropTypeId") Integer cropTypeId) throws Exception {
//		// System.out.println(blockId + "," + cropTypeId);
//		JSONObject sb = new JSONObject();
//		Landblock landblock = landblockService.get(blockId);
//		Integer cooperativeId = landblock.getCooperativeId();
//		sb.put("cooperativeId", cooperativeId);
//		sb.put("cooperativeName", landblock.getCooperative() == null ? ""
//				: landblock.getCooperative().getCooperativeName());
//		sb.put("blockNumber", landblock.getBlockNumber());
//
//		t = new ManagementRecord();
//		t.setBlockId(blockId);
//		t.setCropTypeId(cropTypeId);
//		String[] query = QueryPeriodUtil.getPeriod(request);
//		t.setQueryBeginTime(query[0]);
//		t.setQueryEndTime(query[1]);
//		List<ManagementRecord> tList = getBaseService().list(t);
//
//		JSONArray data = new JSONArray();
//		sb.put("data", data);
//		JSONArray time = new JSONArray();
//		sb.put("time", time);
//		JSONArray id = new JSONArray();
//		sb.put("id", id);
//		JSONArray operationType = new JSONArray();
//		sb.put("operationType", operationType);
//		JSONArray operator = new JSONArray();
//		sb.put("operator", operator);
//		JSONArray type = new JSONArray();
//		sb.put("type", type);
//		JSONArray amount = new JSONArray();
//		sb.put("amount", amount);
//		JSONArray company = new JSONArray();
//		sb.put("company", company);
//		JSONArray diseased = new JSONArray();
//		sb.put("diseased", diseased);
//		JSONArray naturalDisaster = new JSONArray();
//		sb.put("naturalDisaster", naturalDisaster);
//
//		Date beginDate = null;
//		Date endDate = null;
//		if (tList != null) {
//			int index = 0;
//			for (Iterator<ManagementRecord> iterator = tList.iterator(); iterator
//					.hasNext();) {
//				ManagementRecord managementRecord = (ManagementRecord) iterator
//						.next();
//				String _operationType = managementRecord.getOperationType();
//				data.add(OperationUtil.getOperationValue(_operationType));
//
//				Date operationTime = managementRecord.getOperationTime();
//				if (index == 0) {
//					beginDate = operationTime;
//					endDate = operationTime;
//					index++;
//				}
//				// if(!iterator.hasNext()){
//				// endDate = operationTime;
//				// }
//				if (beginDate != null && beginDate.after(operationTime)) {
//					beginDate = operationTime;
//				}
//				if (endDate != null && endDate.before(operationTime)) {
//					endDate = operationTime;
//				}
//
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				String formattedTime = sdf.format(operationTime);
//				time.add(formattedTime);
//
//				id.add(managementRecord.getId());
//				operationType.add(managementRecord.getOperationType());
//				operator.add(managementRecord.getOperator());
//				String[] operationValues = OperationUtil
//						.getOperationValues(managementRecord);
//				// System.out.println(operationValues[0] + ","
//				// + operationValues[1] + "," + operationValues[2]);
//				type.add(operationValues[0]);
//				amount.add(operationValues[1]);
//				company.add(operationValues[2]);
//			}
//		}
//
//		if (beginDate != null && endDate != null) {
//			long diff = endDate.getTime() - beginDate.getTime();
//			// long days = diff / (1000 * 60 * 60 * 24);
//			long newtime = beginDate.getTime() - diff / 4;
//			sb.put("newtime", newtime);
//		}
//
//		// sb.append(formattedTime);
//		// "4.3, 5.1, 4.3, 5.2, 7.4, 4.7, 3.5,8.2, 9, 10, 14,13";
//		return sb;
//	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/crops", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getCropsData(ManagementRecord t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println(blockNumber + "," + cropType);
		List<ManagementRecord> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		StringBuffer time = new StringBuffer("");

		if (tList != null) {
			data.append("data:[");
			time.append("time:[");
			for (Iterator<ManagementRecord> iterator = tList.iterator(); iterator
					.hasNext();) {
				ManagementRecord managementRecord = (ManagementRecord) iterator
						.next();
				String operationType = managementRecord.getOperationType();
				data.append(OperationUtil.getOperationValue(operationType));

				Date operationTime = managementRecord.getOperationTime();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String formattedTime = sdf.format(operationTime);
				time.append("'").append(formattedTime).append("'");

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

	@RequestMapping(value = "updateReturnOrder", method = RequestMethod.POST)
	public String updateReturnOrder(ManagementRecord t) throws Exception {

		if (t.getId() == null) {
			//this.getBaseService().save(t);
		} else {
			service.updateReturnOrder(t);
		}
		return "redirect:./editlist";
	}

	@RequestMapping("cropsearch")
	public ModelAndView addOrUpdate2(ManagementRecord t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("investsearch")
	public ModelAndView addOrUpdate3(ManagementRecord t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}

		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public APIResponseBaseObject getJsonById(@PathVariable("id") Integer id)
			throws Exception {
		APIResponseBaseObject result = new APIResponseBaseObject();
		ManagementRecord managementRecord = service.get(id);
		if (managementRecord != null) {
			Goods goods = goodsService.get(managementRecord.getCargoId());
			managementRecord.setCargoObj(goods);
			
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

	
}
