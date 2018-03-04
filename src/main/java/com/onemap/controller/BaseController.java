package com.onemap.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.BaseModel;
import com.onemap.domain.Clause;
import com.onemap.domain.NGTableObject;
import com.onemap.service.BaseService;
import com.onemap.utl.common.QueryPeriodUtil;

public abstract class BaseController<T extends BaseModel, ID extends Serializable> {

	abstract BaseService<T, ID> getBaseService();

	@RequestMapping("tongji")
	@ResponseBody
	public Object getJson4Tongji(T t) throws Exception {
		List<T> tList = null;
		tList = this.getBaseService().tongji(t);
		return tList;
	}

	@RequestMapping("getJson")
	@ResponseBody
	public Object getJson(T t) throws Exception {
		T tInDb = null;
		tInDb = this.getBaseService().get((ID) (t.getId()));
		return tInDb;
	}

	@RequestMapping("listJson")
	@ResponseBody
	public Object listJson(T t) throws Exception {
		List<T> tList = null;
		tList = getBaseService().list(t);

		return tList;
	}

	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<T> ngtable(T t, HttpServletRequest request)
			throws Exception {
		List<T> tList = null;
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
		int totalCount = getBaseService().count(t);
		NGTableObject<T> result = new NGTableObject<T>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}

	@RequestMapping("list")
	public ModelAndView list(T t) throws Exception {
		List<T> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().listByLimit(t);
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("editlist")
	public ModelAndView editlist(HttpServletRequest request, T t)
			throws Exception {
		List<T> tList = null;
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

	@RequestMapping("save")
	public String save(T t) throws Exception {
		if (t.getId() == null) {
			this.getBaseService().save(t);
		} else {
			this.getBaseService().update(t);
		}
		return "redirect:./editlist";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView addOrUpdate(T t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		// System.out.println(t);
		// System.out.println(modelAndView);
		if (t.getId() == null) {
			return modelAndView;
		}
		T tInDb = this.getBaseService().get((ID) (t.getId()));

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public ModelAndView show(T t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		T tInDb = this.getBaseService().get((ID) (t.getId()));

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}

	public abstract void addDataForEditView(ModelAndView modelAndView)
			throws Exception;

	@RequestMapping("delete")
	public String delete(T t) throws Exception {
		this.getBaseService().delete(t);

		return "redirect:./editlist";
	}
}
