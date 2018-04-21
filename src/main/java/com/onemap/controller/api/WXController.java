package com.onemap.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.User;
import com.onemap.service.UserService;
import com.onemap.utl.common.AesCbcUtil;
import com.onemap.utl.common.HttpRequest;

/**
 * Handles requests for wexin
 */
@Controller
@RequestMapping("/api/wx")
public class WXController {

	private static final Logger logger = LoggerFactory.getLogger(WXController.class);
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "username/{openid}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public APIResponseBaseObject getUsernameByOpenid(@PathVariable("openid") String openid) {
		APIResponseBaseObject responseObject = new APIResponseBaseObject();
		User user = null;
		try {
			user = this.service.getByOpenid(openid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user != null) {
			responseObject.setData(user.getUsername());
			responseObject.setStatus(1);
			responseObject.setInfo("OK");
		} else {
			responseObject.setStatus(0);
			responseObject.setInfo("username is null");
		}
		return responseObject;
	}
	@RequestMapping(value = "decodeUser/{code}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public APIResponseBaseObject decodeUser(@RequestParam("encryptedData") String encryptedData,
			@RequestParam("iv") String iv, @PathVariable("code") String code) {
		APIResponseBaseObject responseObject = new APIResponseBaseObject();
		logger.info("encryptedData:" + encryptedData + ",iv:" + iv + ",code:" + code);
		if (code == null || code.length() == 0) {
			responseObject.setStatus(0);
			responseObject.setInfo("code 不能为空");
			return responseObject;
		}
		// 小程序唯一标识 (在微信小程序管理后台获取)
		String wxspAppid = System.getenv("wxspAppid");
		// 小程序的 app secret (在微信小程序管理后台获取)
		String wxspSecret = System.getenv("wxspSecret");
		// 授权（必填）
		String grant_type = "authorization_code";
		//////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
		// 请求参数
		String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
				+ grant_type;
		// 发送请求
		String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
		// 解析相应内容（转换成json对象）
		JSONObject json = JSONObject.parseObject(sr);
		// 获取会话密钥（session_key）
		String session_key = json.get("session_key").toString();
		// 用户的唯一标识（openid）
		String openid = (String) json.get("openid");

		//////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
		try {
			Map<String, Object> userInfo = new HashMap<>();
			User user = this.service.getByOpenid(openid);
			if(user != null) {
				userInfo.put("username", user.getUsername());
			}
			String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
			if (null != result && result.length() > 0) {
				responseObject.setStatus(1);
				responseObject.setInfo("解密成功");

				JSONObject userInfoJSON = JSONObject.parseObject(result);
				
				userInfo.put("openId", userInfoJSON.get("openId"));
				userInfo.put("nickName", userInfoJSON.get("nickName"));
				userInfo.put("gender", userInfoJSON.get("gender"));
				userInfo.put("city", userInfoJSON.get("city"));
				userInfo.put("province", userInfoJSON.get("province"));
				userInfo.put("country", userInfoJSON.get("country"));
				userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
				userInfo.put("unionId", userInfoJSON.get("unionId"));
				
				if(user == null) {
					user = new User();
					user.setApproverole(-1);
				}
				user.setWx_avatarUrl(polish(userInfo.get("avatarUrl")));
				user.setWx_city(polish(userInfo.get("city")));
				user.setWx_country(polish(userInfo.get("country")));
				user.setWx_gender(polish(userInfo.get("gender")));
				user.setWx_nickName(polish(userInfo.get("nickName")));
				user.setWx_province(polish(userInfo.get("province")));
				user.setWx_unionid(polish(userInfo.get("unionId")));
				user.setOpenid(polish(userInfo.get("openId")));
				if(user != null && user.getId() != null) {
					this.service.updateWX(user);
				} else {
					this.service.save(user);
				}
				
				responseObject.setData(userInfo);
				return responseObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseObject.setStatus(0);
		responseObject.setInfo("解密失败");
		return responseObject;
	}
	
	private static String polish(Object obj) {
		if(obj == null)
			return null;
		return obj.toString();
	}

}