package com.onemap.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.Clause;
import com.onemap.domain.NGTableObject;
import com.onemap.domain.Truck;
import com.onemap.service.BaseService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.ProvinceService;
import com.onemap.service.TruckService;
import com.onemap.service.UserFavoriteService;

@Controller
@RequestMapping("/layout2")
public class LayoutController2 extends BaseController<Truck, Integer> {
	@Autowired
	private TruckService service;
	@Autowired
	private UserFavoriteService userfavoriteService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private IdentityAuthService authservice;
	
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

	@RequestMapping(value = "{id}/status/{status}", method = RequestMethod.PUT)
	@ResponseBody
	public APIResponseBaseObject updateStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Truck goods = new Truck();
		goods.setId(id);
		goods.setStatus(status);
		service.updateStatus(goods);
		result.setData(goods);
		result.setInfo("OK");
		result.setStatus(1);
		return result;
	}
	@RequestMapping("approve")
	public String approve(Truck t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			t.setStatus(1);
			service.updateStatus(t);
		}
		return "redirect:./editlist";
	}

	@RequestMapping("reject")
	public String reject(Truck t, @RequestParam("id2") Integer myid)
			throws Exception {
		if (myid == null) {
			//
		} else {
			t.setId(myid);
			t.setStatus(-1);
			service.updateStatus(t);
		}
		return "redirect:./editlist";
	}
	@RequestMapping("view")
	public ModelAndView list(Truck t) throws Exception {
		ModelAndView modelAndView = new ModelAndView("layout2/view");
		List<Truck> tList = null;
		if(t == null) {
			t = new Truck();
		}
		t.setPageSize(50);
		t.setPage(1);
		System.out.println(t);
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}
	@Override
	BaseService<Truck, Integer> getBaseService() {
		return this.service;
	}
	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<Truck> ngtable(Truck t, HttpServletRequest request)
			throws Exception {
		List<Truck> tList = null;
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
						 System.out.println("==="+filterValue);
//						filterValue = (filterValue == null) ? "" : new String(
//								filterValue.getBytes("iso-8859-1"), "UTF-8");
//						 System.out.println("==="+filterValue);
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
		int totalCount = getBaseService().count(t);
		NGTableObject<Truck> result = new NGTableObject<>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}

}
