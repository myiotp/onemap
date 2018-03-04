package com.onemap.utl.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CalculateDisUtil {
	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 通过经纬度获取距离(单位：100公里取整)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static int getDistance(double lat1, double lng1, double lat2, double lng2) {
		boolean enabled = false;
		if (enabled) {
			double distanceTencent = getDistanceTencent(lat1, lng1, lat2, lng2);
			BigDecimal setScale = new BigDecimal(distanceTencent / 1000).setScale(0, BigDecimal.ROUND_HALF_UP);

			return setScale.intValue();
		} else {
			double radLat1 = rad(lat1);
			double radLat2 = rad(lat2);
			double a = radLat1 - radLat2;
			double b = rad(lng1) - rad(lng2);
			double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
					+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
			s = s * EARTH_RADIUS;
			s = Math.round(s * 10000d) / 10000d;

			BigDecimal setScale = new BigDecimal(s * 1.2).setScale(0, BigDecimal.ROUND_HALF_UP);

			return setScale.intValue();
		}
	}

	/**
	 * 通过经纬度获取距离(单位：公里)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	private static double getDistance2(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		return s;
	}

	public static double getDistanceTencent(double lat1, double lng1, double lat2, double lng2) {
		String key = "ZGFBZ-G276X-IOD4L-77PUX-A6LZ2-LYFMY";
		// Map<String, String> params = new HashMap<>();

		String from = lat1 + "," + lng1;
		String to = lat2 + "," + lng2;
		String url = 
				"http://apis.map.qq.com/ws/distance/v1/?mode=driving&key=" + key + "&from=" + from + "&to=" + to;
		System.out.println(url);
		String response = HttpClientUtil.doGet(url);
		System.out.println(response);
		String oriLng = null;
		String oriLat = null;
		JSONObject jsonObjectOri = com.alibaba.fastjson.JSONObject.parseObject(response);
		if (jsonObjectOri != null) {
			JSONObject jsonObject = jsonObjectOri.getJSONObject("result");

			if (jsonObject != null) {
				JSONArray jsonArray = jsonObject.getJSONArray("elements");
				if (jsonArray != null && jsonArray.size() > 0) {
					JSONObject jsonObject2 = jsonArray.getJSONObject(0);
					return jsonObject2.getDouble("distance");
				}
			}
		}
		return 0;
	}

	public static double[] getLngLat(String location) {
		 double[] lngLatTencent = getLngLatTencent(location);
		 if(lngLatTencent[0] == 0 && lngLatTencent[1] == 0) {
			 return getLngLatBaidu(location);
		 }
		 return lngLatTencent;
	}

	public static double[] getLngLatTencent(String location) {
		String key = "ZGFBZ-G276X-IOD4L-77PUX-A6LZ2-LYFMY";
		// Map<String, String> params = new HashMap<>();
		String url = "http://apis.map.qq.com/ws/geocoder/v1/?key=" + key + "&address=" + location;
		System.out.println(url);

		String response = HttpClientUtil
				.doGet(url);
		return getLngLatImpl(response);
	}

	public static double[] getLngLatBaidu(String location) {
		String key = "6dtvdbX5acH7wNZU4yPXGYL0";
		// Map<String, String> params = new HashMap<>();

		String response = HttpClientUtil
				.doGet("http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + key + "&address=" + location);
		return getLngLatImpl(response);
	}

	private static double[] getLngLatImpl(String response) {

		System.out.println(response);
		String oriLng = null;
		String oriLat = null;
		JSONObject jsonObjectOri = com.alibaba.fastjson.JSONObject.parseObject(response);
		if (jsonObjectOri != null) {
			JSONObject jsonObject = jsonObjectOri.getJSONObject("result");

			if (jsonObject != null) {
				JSONObject jsonObject2 = jsonObject.getJSONObject("location");
				if (jsonObject2 != null) {
					oriLng = jsonObject2.getString("lng");
					oriLat = jsonObject2.getString("lat");
				}
			}
		}
		System.out.println(oriLat + "|" + oriLng);
		if (oriLat != null && oriLng != null) {
			return new double[] { Double.parseDouble(oriLng), Double.parseDouble(oriLat) };
		} else {
			return new double[] { 0, 0 };
		}

	}

	// origin:出发地地名，如:余杭区
	// destination:目的地地名，如:黄浦区
	// 返回两地的行车距离，如:234.56公里
	private static String distance(String origin, String destination, String key) {
		Map<String, String> params = new HashMap<>();

		String originDouble = HttpClientUtil
				.doGet("http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + key + "&address=" + origin);
		String desDouble = HttpClientUtil
				.doGet("http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + key + "&address=" + destination);

		JSONObject jsonObjectOri = com.alibaba.fastjson.JSONObject.parseObject(originDouble);
		JSONObject jsonObjectDes = com.alibaba.fastjson.JSONObject.parseObject(desDouble);
		String oriLng = jsonObjectOri.getJSONObject("result").getJSONObject("location").getString("lng");
		String oriLat = jsonObjectOri.getJSONObject("result").getJSONObject("location").getString("lat");

		System.out.println(oriLat + "|" + oriLng);
		String desLng = jsonObjectDes.getJSONObject("result").getJSONObject("location").getString("lng");
		String desLat = jsonObjectDes.getJSONObject("result").getJSONObject("location").getString("lat");
		System.out.println(desLat + "|" + desLng);
		params.put("output", "json");
		params.put("ak", key);
		params.put("origins", oriLat + "," + oriLng + "|" + oriLat + "," + oriLng);
		params.put("destinations", desLat + "," + desLng + "|" + desLat + "," + desLng);

		String result = HttpClientUtil.doGet("http://api.map.baidu.com/routematrix/v2/driving", params);
		JSONArray jsonArray = com.alibaba.fastjson.JSONObject.parseObject(result).getJSONArray("result");
		System.out.println(result);
		String distanceString = jsonArray.getJSONObject(0).getJSONObject("distance").getString("text");
		return distanceString;
	}

	public static String getCityCode(String codestring) {
		if (codestring != null) {
			String[] split = codestring.split(",");
			int length = split.length;
			while (length > 0) {
				String trim = split[length - 1].trim();
				if (trim.length() > 0) {
					return trim;
				}
				length--;
			}
		}
		return "";
	}

	public static void main(String[] args) {
		// String key = "6dtvdbX5acH7wNZU4yPXGYL0";
		// String distance = distance("天津市", "丹东市", key);
		 int distance = getDistance(39.92998577808024, 116.39564503787867,
		 36.71611487305138, 119.14263382297053);
		 System.out.println(distance);
//		String location = "北京市通州区新华大街8号";
//		getLngLatTencent(location);
//		getLngLatBaidu(location);
		// String fromid = "210000,,";
		// String toid = "210000,210600,210606";
		// String cityCode = getCityCode(fromid);
		// System.out.println(cityCode);
		// System.out.println(getCityCode(toid));
	}
}
