package com.onemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.ManagementRecord;
import com.onemap.domain.SeedType;
import com.onemap.service.BaseService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.SeedTypeService;

@Controller
@RequestMapping("/seedtype")
public class SeedTypeController extends BaseController<SeedType, Integer> {
	@Autowired
	private SeedTypeService service;
	@Autowired
	private ManagementRecordService recordService;

	@Override
	BaseService<SeedType, Integer> getBaseService() {
		return this.service;
	}
	public void addDataForEditView(ModelAndView modelAndView) {
	}

	@RequestMapping("views")
	public ModelAndView list(@RequestParam("blockId") Integer blockId,
			@RequestParam("cropTypeId") Integer cropTypeId) throws Exception {
		Map<Integer, SeedType> map = new HashMap<Integer, SeedType>();
//		List<SeedType> tList = new ArrayList<SeedType>();
		
//		ManagementRecord record = new ManagementRecord();
//		record.setBlockId(blockId);
//		record.setCropTypeId(cropTypeId);
//		List<ManagementRecord> records = recordService.list(record);
//		if(records != null){
//			for (Iterator<ManagementRecord> iterator = records.iterator(); iterator.hasNext();) {
//				ManagementRecord managementRecord = (ManagementRecord) iterator
//						.next();
//				//@ TODO
////				Integer seedTypeId = managementRecord.getSeedTypeId();
////				if(seedTypeId != null){					
////					SeedType seed = getBaseService().get(seedTypeId);
////					if(seed != null && !map.containsKey(seedTypeId)){
////						map.put(seedTypeId, seed);
////					}
////				}
//			}
//		}
						
		ModelAndView modelAndView = new ModelAndView("seedtype/views");
		modelAndView.addObject("resultList", map.values());
		
		return modelAndView;
	}
}
