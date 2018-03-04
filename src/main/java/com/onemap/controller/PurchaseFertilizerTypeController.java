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

import com.onemap.Configuration;
import com.onemap.domain.Clause;
import com.onemap.domain.FertilizerType;
import com.onemap.domain.IdentityAuth;
import com.onemap.domain.NGTableObject;
import com.onemap.domain.PurchaseFertilizerType;
import com.onemap.domain.UploadImage;
import com.onemap.domain.User;
import com.onemap.service.BaseService;
import com.onemap.service.FertilizerTypeService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.PurchaseFertilizerTypeService;
import com.onemap.service.UploadImageService;
import com.onemap.service.UserService;
import com.onemap.utl.common.QueryPeriodUtil;

@Controller
@RequestMapping("/purchasefertilizertype")
public class PurchaseFertilizerTypeController extends BaseController<IdentityAuth, Integer> {
	@Autowired
	private IdentityAuthService service;
	@Autowired
	private UploadImageService imageService;
	@Autowired
	private UserService userService;

	@Override
	BaseService<IdentityAuth, Integer> getBaseService() {
		return this.service;
	}

	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "ngtable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NGTableObject<IdentityAuth> ngtable(IdentityAuth t, HttpServletRequest request) throws Exception {
		List<IdentityAuth> tList = null;
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
					String orderBy = paraName.substring("sorting[".length(), paraName.length() - 1);
					t.setOrderBy(orderBy);
					String paraValue = request.getParameter(paraName);
					t.setOrder(paraValue == null ? "asc" : paraValue);
				}
				if (paraName.startsWith("filter[") && paraName.endsWith("]")) {
					String filter = paraName.substring("filter[".length(), paraName.length() - 1);
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
						filterValue = (filterValue == null) ? ""
								: new String(filterValue.getBytes("iso-8859-1"), "UTF-8");
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

		Clause clause = new Clause();
		clause.setColumn("type");
		clause.setOperator("=");
		clause.setValue("200");
		whereClause.add(clause);

		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}
		// System.out.println("t:" + t);
		tList = getBaseService().listByLimit(t, request);
		for (IdentityAuth item : tList) {
			List<UploadImage> images = imageService.getByUsername(item.getUsername());
			if (images != null) {
				for (UploadImage uploadImage : images) {
					String type = uploadImage.getType();
					if (type == null) {
						continue;
					} else if (type.equals("101")) {
						item.setImage101(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("102")) {
						item.setImage102(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("103")) {
						item.setImage103(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("104")) {
						item.setImage104(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("201")) {
						item.setImage201(Configuration.getImage_url() + "/" + uploadImage.getImage());
					}
				}

			}
		}

		// System.out.println("tList:" + tList);
		int totalCount = getBaseService().count(t);
		NGTableObject<IdentityAuth> result = new NGTableObject<IdentityAuth>();
		result.setTotal(totalCount);
		result.setResult(tList);

		return result;
	}

	@RequestMapping("list")
	public ModelAndView list(IdentityAuth t) throws Exception {
		List<IdentityAuth> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		Clause clause = new Clause();
		clause.setColumn("type");
		clause.setOperator("=");
		clause.setValue("200");
		List<Clause> whereClause = new ArrayList<Clause>();
		whereClause.add(clause);
		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}

		tList = getBaseService().listByLimit(t);
		for (IdentityAuth item : tList) {
			List<UploadImage> images = imageService.getByUsername(item.getUsername());
			if (images != null) {
				for (UploadImage uploadImage : images) {
					String type = uploadImage.getType();
					if (type == null) {
						continue;
					} else if (type.equals("101")) {
						item.setImage101(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("102")) {
						item.setImage102(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("103")) {
						item.setImage103(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("104")) {
						item.setImage104(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("201")) {
						item.setImage201(Configuration.getImage_url() + "/" + uploadImage.getImage());
					}
				}

			}
		}
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage", (int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("editlist")
	public ModelAndView editlist(HttpServletRequest request, IdentityAuth t) throws Exception {
		List<IdentityAuth> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		if (t != null) {
			String[] query = QueryPeriodUtil.getPeriod(request);
			t.setQueryBeginTime(query[0]);
			t.setQueryEndTime(query[1]);
		}
		Clause clause = new Clause();
		clause.setColumn("type");
		clause.setOperator("=");
		clause.setValue("200");
		List<Clause> whereClause = new ArrayList<Clause>();
		whereClause.add(clause);
		if (whereClause.size() > 0) {
			t.setWhereClause(whereClause);
		}

		tList = getBaseService().listByLimit(t);
		for (IdentityAuth item : tList) {
			List<UploadImage> images = imageService.getByUsername(item.getUsername());
			if (images != null) {
				for (UploadImage uploadImage : images) {
					String type = uploadImage.getType();
					if (type == null) {
						continue;
					} else if (type.equals("101")) {
						item.setImage101(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("102")) {
						item.setImage102(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("103")) {
						item.setImage103(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("104")) {
						item.setImage104(Configuration.getImage_url() + "/" + uploadImage.getImage());
					} else if (type.equals("201")) {
						item.setImage201(Configuration.getImage_url() + "/" + uploadImage.getImage());
					}
				}

			}
		}
		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage", (int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public IdentityAuth getJSONObject(@PathVariable("id") Integer id) throws Exception {
		IdentityAuth tInDb = service.get(id);

		return tInDb;
	}
	@RequestMapping(value = "views", method = RequestMethod.GET)
	public ModelAndView show(User t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getUsername() == null) {
			return modelAndView;
		}
		User tInDb = userService.getByUsername(t.getUsername());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	@RequestMapping("approve")
	public String approve(IdentityAuth t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			IdentityAuth userVehicle = service.get(t.getId());
			userVehicle.setComment(t.getComment());
			userVehicle.setAuthresult(1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}
	@RequestMapping("reject")
	public String reject(IdentityAuth t, @RequestParam("id2") Integer myid, @RequestParam("comment2") String comment) throws Exception {
		if (myid == null) {
			//
		} else {
			IdentityAuth userVehicle = service.get(myid);
			userVehicle.setComment(comment);
			userVehicle.setAuthresult(-1);
			this.getBaseService().update(userVehicle);
		}
		return "redirect:./editlist";
	}
}
