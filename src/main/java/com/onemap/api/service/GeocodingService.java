package com.onemap.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

import com.onemap.Configuration;

public class GeocodingService {
	private static GeocodingService instance;

	private GeocodingService() {
	}

	public static GeocodingService getInstance() {
		if (instance == null) {
			instance = new GeocodingService();
		}
		return instance;
	}

	public JSONObject getAddress(double x, double y)
			throws ClientProtocolException, IOException, JSONException {
		JSONObject obj = new JSONObject();
		String mapUrl = Configuration.getInstance().getMapUrl();
		// 创建HttpClient实例
		HttpClient httpclient = new DefaultHttpClient();
//		httpclient.getParams().setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET,"UTF-8");
		HttpGet httpgets = new HttpGet(mapUrl + "location=" + y + "," + x);
		HttpResponse response = httpclient.execute(httpgets);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String str = EntityUtils.toString(entity);
//			System.out.println(entityString);
//			String str = new String(entityString.getBytes("GBK"),"UTF-8");
//			System.out.println(str);
////			InputStream instreams = entity.getContent();
////			String str = convertStreamToString(instreams);
//			str = new String(str.getBytes("GBK"),"UTF-8");
			// Do not need the rest
			httpgets.abort();
			System.out.println(str);
//			System.out.println(new String(str.getBytes("GBK"),"UTF-8"));
			JSONObject res = new JSONObject(str);
			JSONObject result = res.getJSONObject("result");
			if (result != null) {
				String formatted_address = result
						.getString("formatted_address");
				obj.put("formatted_address", formatted_address);
				JSONObject addressComponent = result
						.getJSONObject("addressComponent");
				if (addressComponent != null) {
					String city = addressComponent.getString("city");
					obj.put("city", city);
					String province = addressComponent.getString("province");
					obj.put("province", province);
					String district = addressComponent.getString("district");
					obj.put("district", district);
				}
			}
		}
		return obj;
	}

	public JSONObject getMatchedObject(double x, double y, String province,
			String city, String district) {
		try {
			JSONObject obj = GeocodingService.getInstance().getAddress(x, y);
			boolean b1 = (province == null || province.length() == 0) ? true
					: province.equals(obj.getString("province"));
			boolean b2 = (city == null || city.length() == 0) ? true : city
					.equals(obj.getString("city"));
			boolean b3 = (district == null || district.length() == 0) ? true
					: district.equals(obj.getString("district"));
			if (!(b1 && b2 && b3)) {
				return obj;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
