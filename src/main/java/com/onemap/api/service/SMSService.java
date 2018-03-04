package com.onemap.api.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.wink.json4j.JSONObject;

import com.onemap.Configuration;
import com.onemap.api.AccessToken;
import com.onemap.api.AuthorizationException;
import com.onemap.api.OneMapClient;
import com.onemap.api.OneMapException;
import com.onemap.api.OneMapExecutor;
import com.onemap.api.OneMapRequest;
import com.onemap.api.OneMapRequest.Method;
import com.onemap.api.OneMapResponse;
import com.onemap.api.mapper.ObjectMapper;

public class SMSService {
	private OneMapExecutor executor;

	private AccessToken token;

	private ObjectMapper mapper;

	/**
	 * @param executor
	 * @param token
	 * @param mapper
	 */
	public SMSService(OneMapExecutor executor, AccessToken token, ObjectMapper mapper) {
		super();
		this.executor = executor;
		this.token =token;
		this.mapper = mapper;
	}

	public OneMapResponse sendMessage(String phonenumbers, String message)
			throws OneMapException {
		Map<String, String> textParams = new HashMap<String, String>();
		if (phonenumbers != null) {
			textParams.put("mobilephones", phonenumbers);
		}
		if (message != null) {
			textParams.put("msg", message);
		}
		Map<String, String> bodyParams = new HashMap<String, String>();
		Map<String, File[]> fileParams = new HashMap<String, File[]>();
		String url = Configuration.getInstance().getSmsUrl();
		OneMapRequest request = new OneMapRequest(url, Method.GET,
				textParams, bodyParams, fileParams, token);
		OneMapResponse response = executor.execute(request);
		// try {
		// //return (Album)mapper.mapCommon(response.getResponse().toString(),
		// Album.class);
		// } catch (JsonMappingException e) {
		// throw new ObjectMappingException();
		// }
		return response;
	}
	
	public static void main(String[] args) {
		OneMapClient client = new OneMapClient("api","api");		
		try {
			client.authorizeWithAccessToken("onemap");
			OneMapResponse responseObj = client.getSMSService().sendMessage("1", "2");
			System.out.println(responseObj);
			if (responseObj != null && responseObj.getResponse() != null
					&& responseObj.getResponse() instanceof JSONObject) {
				JSONObject obj = (JSONObject) responseObj.getResponse();
				if (obj.getJSONObject("Package") != null) {
					String resultCode = obj.getJSONObject("Package").getString(
							"ResultCode");
					if (resultCode != null && resultCode.equals("0000")) {
						System.out.println("成功发送!");
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
}
