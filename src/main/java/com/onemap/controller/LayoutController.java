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
import com.onemap.domain.Goods;
import com.onemap.domain.LandType;
import com.onemap.domain.Landblock;
import com.onemap.domain.NGTableObject;
import com.onemap.service.BaseService;
import com.onemap.service.GoodsService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.ProvinceService;
import com.onemap.service.UserFavoriteService;
import com.onemap.utl.common.QueryPeriodUtil;

@Controller
@RequestMapping("/layout")
public class LayoutController extends BaseController<Goods, Integer> {
	@Autowired
	private GoodsService service;
	@Autowired
	private UserFavoriteService userfavoriteService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private IdentityAuthService authservice;
	
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
	public String reject(Goods t, @RequestParam("id2") Integer myid)
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
	public ModelAndView list(Goods t) throws Exception {
		ModelAndView modelAndView = new ModelAndView("layout/view");
		List<Goods> tList = null;
		if(t == null) {
			t = new Goods();
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
	BaseService<Goods, Integer> getBaseService() {
		return this.service;
	}
	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<Goods> ngtable(Goods t, HttpServletRequest request)
			throws Exception {
		List<Goods> tList = null;
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
		NGTableObject<Goods> result = new NGTableObject<>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}

}
