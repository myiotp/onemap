package com.onemap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.ManagementRecord;
import com.onemap.domain.YieldPrediction;
import com.onemap.service.BaseService;
import com.onemap.service.YieldPredictionService;
import com.onemap.utl.common.QueryPeriodUtil;

@Controller
@RequestMapping("/yieldactual")
public class YieldActualController extends
		BaseController<YieldPrediction, Integer> {
	@Autowired
	private YieldPredictionService service;

	@Override
	BaseService<YieldPrediction, Integer> getBaseService() {
		return this.service;
	}

//	@RequestMapping("save")
//	public String save(YieldPrediction t) throws Exception {
//		if(t != null){
//			if(t.getActualYield() !=null && t.getPredictYield() == null){
//				t.setPredictYield(t.getActualYield());
//				t.setActualYield(new Double(1));
//			}
//		}
//		if (t.getId() == null) {
//			this.getBaseService().save(t);
//		} else {
//			this.getBaseService().update(t);
//		}
//		return "redirect:./editlist";
//	}
	
	public void addDataForEditView(ModelAndView modelAndView) {
	}
	
	@RequestMapping(value = "edit",  method = RequestMethod.GET)
	public ModelAndView edit(YieldPrediction t) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		YieldPrediction tInDb = getBaseService().get(t.getId());

		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping("charts")
	public ModelAndView charts(HttpServletRequest request, YieldPrediction t) throws Exception {
		List<YieldPrediction> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);
		tList = getBaseService().list(t);

		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping(value = "block/{blockNumber}/crop/{cropType}/charts/csv", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getData(HttpServletRequest request, YieldPrediction t,
			@PathVariable("blockNumber") String blockNumber,
			@PathVariable("cropType") String cropType) throws Exception {
		System.out.println("search by " + blockNumber + "," + cropType);
		t.setBlockNumber(blockNumber);
		t.setCropType(cropType);
		String[] query = QueryPeriodUtil.getPeriod(request);
		t.setQueryBeginTime(query[0]);
		t.setQueryEndTime(query[1]);
		List<YieldPrediction> tList = getBaseService().list(t);
		StringBuffer sb = new StringBuffer("预测时间,预测产量,实际产量\n");
		if (tList != null) {
			for (Iterator<YieldPrediction> iterator = tList.iterator(); iterator
					.hasNext();) {
				YieldPrediction yieldPrediction = (YieldPrediction) iterator
						.next();
				Date predictTime = yieldPrediction.getPredictTime();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String formattedPredictTime = sdf.format(predictTime);
				Double predictYield = yieldPrediction.getPredictYield();
				Double actualYield = yieldPrediction.getActualYield();
				sb.append(formattedPredictTime).append(",")
						.append(predictYield).append(",").append(actualYield)
						.append("\n");
			}
		}
		// String csv =
		// "预测时间,预测产量,实际产量\n3/9/13,5691,4346\n3/10/13,5403,4112\n3/11/13,15574,11356\n3/12/13,16211,11876\n3/13/13,16427,11966\n3/14/13,16486,12086\n3/15/13,14737,10916\n3/16/13,5838,4507\n3/17/13,5542,4202\n3/18/13,15560,11523\n3/19/13,18940,14431\n3/20/13,16970,12599\n3/21/13,17580,13094\n3/22/13,17511,13234\n3/23/13,6601,5213\n3/24/13,6158,4806\n3/25/13,17353,12639\n3/26/13,17660,12768\n3/27/13,16921,12389\n3/28/13,15964,11686\n3/29/13,12028,8891\n3/30/13,5835,4513\n3/31/13,4799,3661\n4/1/13,13037,9503\n4/2/13,16976,12666\n4/3/13,17100,12635\n4/4/13,15701,11394\n4/5/13,14378,10530\n4/6/13,5889,4521\n4/7/13,6779,5109\n4/8/13,16068,11599\n";
		return sb.toString();
	}
	
	@RequestMapping("chartsearch")
	public ModelAndView addOrUpdate(YieldPrediction t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		if (t.getId() == null) {
			return modelAndView;
		}
		
		addDataForEditView(modelAndView);

		return modelAndView;
	}
}
