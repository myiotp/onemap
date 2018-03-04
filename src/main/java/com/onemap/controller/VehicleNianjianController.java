package com.onemap.controller;

import java.sql.Date;
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

import com.onemap.Configuration;
import com.onemap.domain.Clause;
import com.onemap.domain.NGTableObject;
import com.onemap.domain.PurchaseSeedType;
import com.onemap.domain.SeedType;
import com.onemap.domain.User;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.PurchaseSeedTypeService;
import com.onemap.service.SeedTypeService;
import com.onemap.service.UserService;
import com.onemap.service.UserVehicleService;

@Controller
@RequestMapping("/vehiclenianjian")
public class VehicleNianjianController extends BaseController<UserVehicle, Integer> {
	@Autowired
	private UserVehicleService service;
	@Autowired
	private UserService userService;
	

	@Override
	BaseService<UserVehicle, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public UserVehicle getJSONObject(@PathVariable("id") Integer id) throws Exception {
		UserVehicle tInDb = this.getBaseService().get(id);
		// if (tInDb != null) {
		// SeedType type = typeService.get(tInDb.getSeedTypeId());
		// tInDb.setSeedType(type.getSeedType());
		// }
		if (tInDb != null) {
			String certimage = tInDb.getCertimage();
			tInDb.setCertimage(Configuration.getImage_url() + "/vehicle/" + certimage);
		}
		return tInDb;
	}

	@RequestMapping("approve")
	public String approve(UserVehicle t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			UserVehicle userVehicle = service.get(t.getId());
			userVehicle.setComment(t.getComment());
			userVehicle.setAuthresult(1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}

	@RequestMapping("reject")
	public String reject(UserVehicle t, @RequestParam("id2") Integer myid, @RequestParam("comment2") String comment)
			throws Exception {
		if (myid == null) {
			//
		} else {
			UserVehicle userVehicle = service.get(myid);
			userVehicle.setComment(comment);
			userVehicle.setAuthresult(-1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}
	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<UserVehicle> ngtable(UserVehicle t, HttpServletRequest request)
			throws Exception {
		List<UserVehicle> tList = null;
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

		t.setOrder("asc");
		t.setOrderBy("checkdeadline");
	
		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}
		// System.out.println("t:" + t);
		tList = getBaseService().listByLimit(t, request);
		for (UserVehicle v : tList) {
			Date checkdeadline = v.getCheckdeadline();
			int day = (int) ((checkdeadline.getTime() - System.currentTimeMillis()) /(1000*60*60*24));
			v.setAuthresult(day);
			
			String username = v.getUsername();
			User user = userService.getByUsername(username);
			if(user !=null) {
				v.setCreatorUserName(user.getRealname());
				v.setComment(user.getMobilephone());
			}
		}
		// System.out.println("tList:" + tList);
		int totalCount = getBaseService().count(t);
		NGTableObject<UserVehicle> result = new NGTableObject<>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}
}
