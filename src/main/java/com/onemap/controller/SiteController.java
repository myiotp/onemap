package com.onemap.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.FarmMachinery;
import com.onemap.domain.Landblock;
import com.onemap.domain.Site;
import com.onemap.domain.SiteCert;
import com.onemap.service.BaseService;
import com.onemap.service.FarmMachineryService;
import com.onemap.service.LandblockService;
import com.onemap.service.SiteCertService;
import com.onemap.service.SiteService;

@Controller
@RequestMapping("/site")
public class SiteController extends BaseController<Site, Integer> {
	@Autowired
	private SiteService siteService;

	@Autowired
	private LandblockService landblockService;
	@Autowired
	private SiteCertService siteCertService;
	@Autowired
	private FarmMachineryService machineryService;

	@Override
	BaseService<Site, Integer> getBaseService() {
		return this.siteService;
	}

	@Override
    public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping(value = "editli")
	public ModelAndView editlist(Landblock t,
			@RequestParam("id") Integer cooperativeId) throws Exception {
		List<Landblock> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("cooperativeId:" + cooperativeId);
		t = new Landblock();
		t.setCooperativeId(cooperativeId);
		tList = this.landblockService.listByLimit(t);
		int totalCount = this.landblockService.count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping(value = "/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsDataByAll() throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);
		Site _t = new Site();
		List<Site> sites = this.siteService.list(_t);
		if (sites != null) {
			for (Iterator<Site> siteIterator = sites.iterator(); siteIterator
					.hasNext();) {
				Site site = siteIterator.next();
				JSONObject item = new JSONObject();
				item.put("id", site.getId());
				item.put("cooperativeNumber",
						site.getCooperativeNumber());
				item.put("cooperativeName",
						site.getCooperativeName());
				item.put("owner", site.getOwner());
				item.put("telephone", site.getTelephone());
				item.put("x", site.getGpsx());
				item.put("y", site.getGpsy());

				array.add(item);
			}// end for root loop

		}


		return obj;
	}
	
	@RequestMapping(value = "show",  method = RequestMethod.GET)
    public ModelAndView show(Site t) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        if (t.getId() == null) {
            return modelAndView;
        }
        Site tInDb = this.getBaseService().get(t.getId());
        modelAndView.addObject("currentObj", tInDb);
        
		SiteCert sc = new SiteCert();
		sc.setCooperativeId(t.getId());
		List<SiteCert> tList = this.siteCertService.listByLimit(sc);
//		int totalCount = this.siteCertService().count(t);
//		modelAndView.addObject("totalCount", totalCount);
//		modelAndView.addObject("totalPage",
//				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);

		FarmMachinery fm = new FarmMachinery();
		fm.setCooperativeId(t.getId());
		List<FarmMachinery> tList2 = this.machineryService.listByLimit(fm);
		modelAndView.addObject("resultList2", tList2);
		
		
        addDataForEditView(modelAndView);

        return modelAndView;
    }
}
