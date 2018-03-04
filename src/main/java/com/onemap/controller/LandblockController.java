package com.onemap.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

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

import com.onemap.domain.BlockXY;
import com.onemap.domain.CropLifecycle;
import com.onemap.domain.CropType;
import com.onemap.domain.Landblock;
import com.onemap.domain.ManagementRecord;
import com.onemap.domain.Site;
import com.onemap.domain.YieldPrediction;
import com.onemap.service.BaseService;
import com.onemap.service.BlockXYService;
import com.onemap.service.CropLifecycleService;
import com.onemap.service.CropTypeService;
import com.onemap.service.LandblockService;
import com.onemap.service.ManagementRecordService;
import com.onemap.service.SiteService;
import com.onemap.service.YieldPredictionService;
import com.onemap.utl.common.QueryPeriodUtil;
import com.onemap.utl.pages.PaginatedArrayList;

@Controller
@RequestMapping("/landblock")
public class LandblockController extends BaseController<Landblock, Integer> {
	@Autowired
	private LandblockService landblockService;
	@Autowired
	private BlockXYService blockXYservice;
	@Autowired
	private SiteService siteService;
	@Autowired
	private CropTypeService cropTypeService;
	@Autowired
	private CropLifecycleService cropLifecycleService;
	@Autowired
	private YieldPredictionService yieldPredictionService;
	@Autowired
	private ManagementRecordService mrService;

	@Override
	BaseService<Landblock, Integer> getBaseService() {
		return this.landblockService;
	}

	@RequestMapping(value = "json/block/{id}", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public Object getJson(@PathVariable("id") Integer blockId) throws Exception {
		Landblock tInDb = null;
		tInDb = this.getBaseService().get(blockId);
		return tInDb;
	}

	@RequestMapping(value = "json/blocks/site/{id}", method = RequestMethod.POST, produces = "text/javascript")
	@ResponseBody
	public Object getLocksJson(@PathVariable("id") Integer cooperativeId)
			throws Exception {
		Landblock t = new Landblock();
		t.setCooperativeId(cooperativeId);
		List<Landblock> tList = this.landblockService.list(t);
		return tList;
	}

	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			Object obj = modelAndView.getModel().get("cropTypeId");
			Integer cropTypeId = obj == null ? 0 : Integer.parseInt(obj
					.toString());
			System.out.println("cropTypeId:" + cropTypeId);
			CropType cropType = this.cropTypeService.get(cropTypeId);
			modelAndView.addObject("cropType", cropType);
		}
	}

	@RequestMapping("editli")
	public ModelAndView editlist(BlockXY t) throws Exception {
		List<BlockXY> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = this.blockXYservice.listByLimit(t);
		int totalCount = this.blockXYservice.count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping(value = "block/{id}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsDataByBlock(HttpServletRequest request,
			@PathVariable("id") Integer blockId) throws Exception {
		System.out.println("search by " + blockId + ",");
		Landblock landblock = getBaseService().get(blockId);

		BlockXY t = new BlockXY();
		t.setBlockId(blockId);
		List<BlockXY> tList = this.blockXYservice.list(t);
		// POLYGON((20.828125 -10.3515625, 0 0,132.1875 -13.0078125, -1.40625
		// -59.4140625, 28.828125 0.3515625))
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");

		if (tList != null) {
			data.append("data:[");
			StringBuffer item = new StringBuffer("POLYGON((");
			for (Iterator<BlockXY> iterator = tList.iterator(); iterator
					.hasNext();) {
				BlockXY xy = iterator.next();
				Double x = xy.getPositionX();
				Double y = xy.getPositionY();
				item.append(x).append(" ").append(y);
				if (iterator.hasNext()) {
					item.append(",");
				}
			}
			item.append("))");
			data.append("{item:'" + item.toString() + "',id:'" + blockId
					+ "',blockNumber:'" + landblock.getBlockNumber() + "'}");
			data.append("]");
			sb.append(data);
		}

		sb.append("}");
		return sb.toString();
	}

	@RequestMapping(value = "site/{id}/block/{blockId}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsDataBySiteBlock(HttpServletRequest request,
			@PathVariable("id") Integer siteId, @PathVariable("blockId") Integer blockId) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);
		System.out.println("search by " + siteId + ","+ blockId);
		Landblock block = new Landblock();
//		block.setCooperativeId(siteId);
		block.setId(blockId);
		List<Landblock> blockList = this.landblockService.list(block);
		// StringBuffer sb = new StringBuffer("{");
		// StringBuffer data = new StringBuffer("");
		if (block != null) {
			// data.append("data:[");
			for (Iterator<Landblock> iterator = blockList.iterator(); iterator
					.hasNext();) {
				Landblock landblock = (Landblock) iterator.next();
				JSONObject item = new JSONObject();
				BlockXY t = new BlockXY();
				t.setBlockId(landblock.getId());
				List<BlockXY> tList = this.blockXYservice.list(t);
				JSONArray polygon = new JSONArray();
				if (tList != null) {
					for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						BlockXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						JSONObject xyobj = new JSONObject();
						xyobj.put("x", x);
						xyobj.put("y", y);
						polygon.add(xyobj);
					}
				}
				item.put("polygon", polygon);
				item.put("id", landblock.getId());
				item.put("blockNumber", landblock.getBlockNumber());
				item.put("area", landblock.getArea());
				item.put("blockType", landblock.getBlockType());
				item.put("landType", landblock.getLandType());
				item.put("site", landblock.getCooperative());
				Integer cropTypeId = 0;
				String cropType = "";
//				String[] query = QueryPeriodUtil.getPeriod(request);
//
//				{
//					ManagementRecord mr = new ManagementRecord();
//					mr.setBlockId(landblock.getId());
//					mr.setQueryBeginTime(query[0]);
//					mr.setQueryEndTime(query[1]);
//					List<ManagementRecord> mrList = this.mrService.list(mr);
//					if (mrList != null && mrList.size() > 0) {
//						cropTypeId = mrList.get(0).getCropTypeId();
//						cropType = mrList.get(0).getCropType();
//					}
//					item.put("cropTypeId_mr", cropTypeId);
//					item.put("cropType_mr", cropType);
//				}
//				{
//					CropLifecycle lc = new CropLifecycle();
//					lc.setBlockId(landblock.getId());
//
//					lc.setQueryBeginTime(query[0]);
//					lc.setQueryEndTime(query[1]);
//					List<CropLifecycle> mrList = this.cropLifecycleService
//							.list(lc);
//					if (mrList != null && mrList.size() > 0) {
//						cropTypeId = mrList.get(0).getCropTypeId();
//						cropType = mrList.get(0).getCropType();
//					}
//					item.put("cropTypeId_lc", cropTypeId);
//					item.put("cropType_lc", cropType);
//				}
//				{
//					YieldPrediction t2 = new YieldPrediction();
//					t2.setBlockId(landblock.getId());
//					t2.setQueryBeginTime(query[0]);
//					t2.setQueryEndTime(query[1]);
//					List<YieldPrediction> tList2 = this.yieldPredictionService
//							.listByCropType(t2);
//					if (tList2 != null && tList2.size() > 0) {
//						YieldPrediction yp = tList2.get(0);
//						cropTypeId = yp.getCropTypeId();
//						cropType = yp.getCropType();
//					}
//
//					item.put("cropTypeId_yp", cropTypeId);
//					item.put("cropType_yp", cropType);
//				}

				array.add(item);
			}// end for loop
		}
		return obj;
	}
	
	@RequestMapping(value = "site/{id}/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsDataBySite(HttpServletRequest request,
			@PathVariable("id") Integer siteId) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);
		System.out.println("search by " + siteId + ",");
		Landblock block = new Landblock();
		block.setCooperativeId(siteId);
		List<Landblock> blockList = this.landblockService.list(block);
		// StringBuffer sb = new StringBuffer("{");
		// StringBuffer data = new StringBuffer("");
		if (block != null) {
			// data.append("data:[");
			for (Iterator<Landblock> iterator = blockList.iterator(); iterator
					.hasNext();) {
				Landblock landblock = (Landblock) iterator.next();
				JSONObject item = new JSONObject();
				BlockXY t = new BlockXY();
				t.setBlockId(landblock.getId());
				List<BlockXY> tList = this.blockXYservice.list(t);
				JSONArray polygon = new JSONArray();
				if (tList != null) {
					for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						BlockXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						JSONObject xyobj = new JSONObject();
						xyobj.put("x", x);
						xyobj.put("y", y);
						polygon.add(xyobj);
					}
				}
				item.put("polygon", polygon);
				item.put("id", landblock.getId());
				item.put("blockNumber", landblock.getBlockNumber());
				item.put("area", landblock.getArea());
				item.put("blockType", landblock.getBlockType());
				item.put("landType", landblock.getLandType());
				item.put("site", landblock.getCooperative());
				Integer cropTypeId = 0;
				String cropType = "";
				String[] query = QueryPeriodUtil.getPeriod(request);

				{
					ManagementRecord mr = new ManagementRecord();
//					mr.setBlockId(landblock.getId());
//					mr.setQueryBeginTime(query[0]);
//					mr.setQueryEndTime(query[1]);
//					List<ManagementRecord> mrList = this.mrService.list(mr);
//					if (mrList != null && mrList.size() > 0) {
//						cropTypeId = mrList.get(0).getCropTypeId();
//						cropType = mrList.get(0).getCropType();
//					}
					item.put("cropTypeId_mr", cropTypeId);
					item.put("cropType_mr", cropType);
				}
				{
					CropLifecycle lc = new CropLifecycle();
					lc.setBlockId(landblock.getId());

					lc.setQueryBeginTime(query[0]);
					lc.setQueryEndTime(query[1]);
					List<CropLifecycle> mrList = this.cropLifecycleService
							.list(lc);
					if (mrList != null && mrList.size() > 0) {
						cropTypeId = mrList.get(0).getCropTypeId();
						cropType = mrList.get(0).getCropType();
					}
					item.put("cropTypeId_lc", cropTypeId);
					item.put("cropType_lc", cropType);
				}
				{
					YieldPrediction t2 = new YieldPrediction();
					t2.setBlockId(landblock.getId());
					t2.setQueryBeginTime(query[0]);
					t2.setQueryEndTime(query[1]);
					List<YieldPrediction> tList2 = this.yieldPredictionService
							.listByCropType(t2);
					if (tList2 != null && tList2.size() > 0) {
						YieldPrediction yp = tList2.get(0);
						cropTypeId = yp.getCropTypeId();
						cropType = yp.getCropType();
					}

					item.put("cropTypeId_yp", cropTypeId);
					item.put("cropType_yp", cropType);
				}

				array.add(item);
			}// end for loop
		}
		return obj;
	}

	@RequestMapping(value = "site/{id}/trails/lifecycle", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsLifecycleDataBySite(HttpServletRequest request,
			@PathVariable("id") Integer siteId) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);
		System.out.println("search by " + siteId + ",");
		Landblock block = new Landblock();
		block.setCooperativeId(siteId);
		List<Landblock> blockList = this.landblockService.list(block);
		// StringBuffer sb = new StringBuffer("{");
		// StringBuffer data = new StringBuffer("");
		if (block != null) {
			// data.append("data:[");
			for (Iterator<Landblock> iterator = blockList.iterator(); iterator
					.hasNext();) {
				Landblock landblock = (Landblock) iterator.next();
				JSONObject item = new JSONObject();
				BlockXY t = new BlockXY();
				t.setBlockId(landblock.getId());
				List<BlockXY> tList = this.blockXYservice.list(t);
				JSONArray polygon = new JSONArray();
				if (tList != null) {
					for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						BlockXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						JSONObject xyobj = new JSONObject();
						xyobj.put("x", x);
						xyobj.put("y", y);
						polygon.add(xyobj);
					}
				}
				item.put("polygon", polygon);
				item.put("id", landblock.getId());
				item.put("blockNumber", landblock.getBlockNumber());
				item.put("area", landblock.getArea());
				item.put("blockType", landblock.getBlockType());
				item.put("landType", landblock.getLandType());
				item.put("site", landblock.getCooperative());
				CropLifecycle crop = new CropLifecycle();
				crop.setBlockId(landblock.getId());
				Integer cropTypeId = new Integer(-1);
				String cropType = "";

				CropLifecycle lc = new CropLifecycle();
				lc.setBlockId(landblock.getId());
				String[] query = QueryPeriodUtil.getPeriod(request);
				lc.setQueryBeginTime(query[0]);
				lc.setQueryEndTime(query[1]);
				List<CropLifecycle> mrList = this.cropLifecycleService.list(lc);
				if (mrList != null && mrList.size() > 0) {
					cropTypeId = mrList.get(0).getCropTypeId();
					cropType = mrList.get(0).getCropType();
				}
				item.put("cropTypeId", cropTypeId);
				item.put("cropType", cropType);
				array.add(item);
			}// end for loop
		}
		return obj;
	}

	@RequestMapping(value = "site/{id}/trails/record", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsRecordDataBySite(HttpServletRequest request,
			@PathVariable("id") Integer siteId) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);
		System.out.println("search by " + siteId + ",");
		Landblock block = new Landblock();
		block.setCooperativeId(siteId);
		List<Landblock> blockList = this.landblockService.list(block);
		// StringBuffer sb = new StringBuffer("{");
		// StringBuffer data = new StringBuffer("");
		if (block != null) {
			// data.append("data:[");
			for (Iterator<Landblock> iterator = blockList.iterator(); iterator
					.hasNext();) {
				Landblock landblock = (Landblock) iterator.next();
				JSONObject item = new JSONObject();
				BlockXY t = new BlockXY();
				t.setBlockId(landblock.getId());
				List<BlockXY> tList = this.blockXYservice.list(t);
				JSONArray polygon = new JSONArray();
				if (tList != null) {
					for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						BlockXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						JSONObject xyobj = new JSONObject();
						xyobj.put("x", x);
						xyobj.put("y", y);
						polygon.add(xyobj);
					}
				}
				item.put("polygon", polygon);
				item.put("id", landblock.getId());
				item.put("blockNumber", landblock.getBlockNumber());
				item.put("area", landblock.getArea());
				item.put("blockType", landblock.getBlockType());
				item.put("landType", landblock.getLandType());
				item.put("site", landblock.getCooperative());
				// CropLifecycle crop = new CropLifecycle();
				// crop.setBlockId(landblock.getId());
				Integer cropTypeId = 0;
				String cropType = "";

				ManagementRecord mr = new ManagementRecord();
//				mr.setBlockId(landblock.getId());
//				String[] query = QueryPeriodUtil.getPeriod(request);
//				mr.setQueryBeginTime(query[0]);
//				mr.setQueryEndTime(query[1]);
//				List<ManagementRecord> mrList = this.mrService.list(mr);
//				if (mrList != null && mrList.size() > 0) {
//					cropTypeId = mrList.get(0).getCropTypeId();
//					cropType = mrList.get(0).getCropType();
//				}
				// YieldPrediction t2 = new YieldPrediction();
				// t2.setQueryBeginTime(query[0]);
				// t2.setQueryEndTime(query[1]);
				// List<YieldPrediction> tList2 =
				// this.yieldPredictionService.listByCropType(t2);
				// if(tList2 != null && tList2.size() > 0){
				// YieldPrediction yp = tList2.get(0);
				// cropTypeId = yp.getCropTypeId();
				// cropType = yp.getCropType();
				// }

				// crop = this.cropLifecycleService.getLatest(crop);

				// if (crop != null) {
				// cropTypeId = crop.getCropTypeId();
				// cropType = crop.getCropType();
				// }
				item.put("cropTypeId", cropTypeId);
				item.put("cropType", cropType);
				array.add(item);
			}// end for loop
		}
		return obj;
	}

	@RequestMapping(value = "site/{id}/trails/yield", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public JSONObject getTrailsYieldDataBySite(HttpServletRequest request,
			@PathVariable("id") Integer siteId) throws Exception {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("data", array);

		Landblock block = new Landblock();
		block.setCooperativeId(siteId);
		List<Landblock> blockList = this.landblockService.list(block);
		// StringBuffer sb = new StringBuffer("{");
		// StringBuffer data = new StringBuffer("");
		if (block != null) {
			// data.append("data:[");
			for (Iterator<Landblock> iterator = blockList.iterator(); iterator
					.hasNext();) {
				Landblock landblock = (Landblock) iterator.next();
				JSONObject item = new JSONObject();
				BlockXY t = new BlockXY();
				t.setBlockId(landblock.getId());
				List<BlockXY> tList = this.blockXYservice.list(t);
				JSONArray polygon = new JSONArray();
				if (tList != null) {
					for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
							.hasNext();) {
						BlockXY xy = iterator2.next();
						Double x = xy.getPositionX();
						Double y = xy.getPositionY();
						JSONObject xyobj = new JSONObject();
						xyobj.put("x", x);
						xyobj.put("y", y);
						polygon.add(xyobj);
					}
				}
				item.put("polygon", polygon);
				item.put("id", landblock.getId());
				item.put("blockNumber", landblock.getBlockNumber());
				item.put("area", landblock.getArea());
				item.put("blockType", landblock.getBlockType());
				item.put("landType", landblock.getLandType());
				item.put("site", landblock.getCooperative());
				// CropLifecycle crop = new CropLifecycle();
				// crop.setBlockId(landblock.getId());
				Integer cropTypeId = 0;
				String cropType = "";
				String[] query = QueryPeriodUtil.getPeriod(request);

				YieldPrediction t2 = new YieldPrediction();
				t2.setBlockId(landblock.getId());
				t2.setQueryBeginTime(query[0]);
				t2.setQueryEndTime(query[1]);
				List<YieldPrediction> tList2 = this.yieldPredictionService
						.listByCropType(t2);
				if (tList2 != null && tList2.size() > 0) {
					YieldPrediction yp = tList2.get(0);
					cropTypeId = yp.getCropTypeId();
					cropType = yp.getCropType();
				}

				item.put("cropTypeId", cropTypeId);
				item.put("cropType", cropType);
				array.add(item);
			}// end for loop
		}
		return obj;
	}

	@RequestMapping(value = "/trails", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String getTrailsDataByAll() throws Exception {
		StringBuffer sb = new StringBuffer("{");
		StringBuffer data = new StringBuffer("");
		data.append("data:[");
		Site _t = new Site();
		List<String> gpsinfoList = new ArrayList<String>();

		List<Site> sites = this.siteService.list(_t);
		if (sites != null) {
			List<String> dataTempList = new ArrayList<String>();
			for (Iterator<Site> siteIterator = sites.iterator(); siteIterator
					.hasNext();) {
				Site site = (Site) siteIterator.next();
				gpsinfoList.add(site.getGpsx() + "," + site.getGpsy());
				Landblock block = new Landblock();
				block.setCooperativeId(site.getId());
				List<Landblock> blockList = this.landblockService.list(block);

				if (blockList != null && blockList.size() > 0) {
					StringBuffer itemTemp = new StringBuffer();
					for (Iterator<Landblock> iterator = blockList.iterator(); iterator
							.hasNext();) {
						Landblock landblock = (Landblock) iterator.next();
						BlockXY t = new BlockXY();
						t.setBlockId(landblock.getId());
						List<BlockXY> tList = this.blockXYservice.list(t);
						StringBuffer item = new StringBuffer("POLYGON((");
						if (tList != null) {
							for (Iterator<BlockXY> iterator2 = tList.iterator(); iterator2
									.hasNext();) {
								BlockXY xy = iterator2.next();
								Double x = xy.getPositionX();
								Double y = xy.getPositionY();
								item.append(x).append(" ").append(y);
								if (iterator2.hasNext()) {
									item.append(",");
								}
							}
						}
						item.append("))");

						CropLifecycle crop = new CropLifecycle();
						crop.setBlockId(landblock.getId());
						crop = this.cropLifecycleService.getLatest(crop);
						Integer cropTypeId = null;
						String cropType = null;
						if (crop != null) {
							cropTypeId = crop.getCropTypeId();
							cropType = crop.getCropType();
						}

						itemTemp.append("{item:'"
								+ item.toString()
								+ "',id:'"
								+ landblock.getId()
								+ "',blockNumber:'"
								+ landblock.getBlockNumber()
								+ "',site:'"
								+ site.getCooperativeNumber()
								+ "',landType:'"
								+ landblock.getLandType().getLandType()
								+ "',cropTypeId:'"
								+ (landblock.getCropTypeId() == null ? cropTypeId
										: landblock.getCropTypeId())
								+ "',cropType:'"
								+ (landblock.getCropType() == null ? cropType
										: landblock.getCropType().getCropType())
								+ "',blockType:'"
								+ landblock.getBlockType().getBlockType()
								+ "'}");
						if (iterator.hasNext()) {
							itemTemp.append(",");
						}
					}
					dataTempList.add(itemTemp.toString());
				}// end for IF

			}// end for root loop

			for (Iterator<String> iterator = dataTempList.iterator(); iterator
					.hasNext();) {
				String string = (String) iterator.next();
				data.append(string);
				// System.out.println(string);
				if (iterator.hasNext()) {
					data.append(",");
				}
			}

			data.append("]");
			sb.append(data);
		}
		sb.append(",gpsinfo:[");
		StringBuffer sbGpsInfo = new StringBuffer();
		for (Iterator<String> iterator = gpsinfoList.iterator(); iterator
				.hasNext();) {
			String _gpsinfo = iterator.next();
			if (_gpsinfo != null) {
				StringTokenizer st = new StringTokenizer(_gpsinfo, ",");
				sbGpsInfo.append("{x:'").append(st.nextElement())
						.append("',y:'").append(st.nextElement()).append("'}");
				if (iterator.hasNext()) {
					sbGpsInfo.append(",");
				}
			}
		}
		sb.append(sbGpsInfo);
		sb.append("]");
		sb.append("}");
		System.out.println(sb.toString());
		return sb.toString();
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public Landblock getCertJson(@PathVariable("id") Integer id)
			throws Exception {
		Landblock t = new Landblock();
		t.setId(id);
		PaginatedArrayList<Landblock> tInDbList = this.getBaseService()
				.listByLimit(t);
		if (tInDbList != null && tInDbList.size() > 0) {
			return tInDbList.get(0);
		}
		return null;
	}
}
