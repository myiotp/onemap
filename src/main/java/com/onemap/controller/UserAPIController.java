/**
 * 
 */
package com.onemap.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.IdentityAuth;
import com.onemap.domain.Truck;
import com.onemap.domain.User;
import com.onemap.service.BaseService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.UserService;
import com.onemap.service.UserVehicleService;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/user")
public class UserAPIController extends BaseController<User, Integer> {
	@Autowired
	private UserService service;
	@Autowired
	private IdentityAuthService authservice;
	@Autowired
	private UserVehicleService uservehicleservice;
	
	@Override
	BaseService<User, Integer> getBaseService() {
		return service;
	}


	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	
	@RequestMapping(value = "openid/{openid}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByOpenid(@PathVariable("openid") String openid) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			User user = this.service.getByOpenid(openid);
			if(user == null) {
				result.setInfo("404");
				result.setStatus(-1);
				return result;
			}
//			boolean b1 = false;
//			boolean b2 = false;
//			boolean b3 = false;
//			List<IdentityAuth> auth100 = authservice.getByUsernameAndType(user.getUsername(), "100");
//			if(auth100.size() > 0) {
//				b1 = (auth100.get(0).getAuthresult() == 1);
//			}
//			List<IdentityAuth> auth200 = authservice.getByUsernameAndType(user.getUsername(), "200");
//			if(auth200.size() > 0) {
//				b2 = (auth200.get(0).getAuthresult() == 1);
//			}
//			user.setB1(b1);
//			user.setB2(b2);
//			System.out.println(user);
			result.setData(user);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "{username}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsername(@PathVariable("username") String username) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			User user = this.service.getByUsername(username);
			if(user == null) {
				result.setInfo("username not exist");
				result.setStatus(0);
				return result;
			}
			boolean b1 = false;
			boolean b2 = false;
			boolean b3 = false;
			List<IdentityAuth> auth100 = authservice.getByUsernameAndType(username, "100");
			if(auth100.size() > 0) {
				b1 = (auth100.get(0).getAuthresult() == 1);
			}
			List<IdentityAuth> auth200 = authservice.getByUsernameAndType(username, "200");
			if(auth200.size() > 0) {
				b2 = (auth200.get(0).getAuthresult() == 1);
			}
			user.setB1(b1);
			user.setB2(b2);
			System.out.println(user);
			result.setData(user);
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody User t) {
		APIResponseBaseObject result = new APIResponseBaseObject();

		String originalPassword = t.getPassword();
		Md5Hash hash = new Md5Hash(originalPassword);
		t.setPassword(hash.toHex());
		try {
			String openid = t.getOpenid();
			User user = service.getByOpenid(openid);
			
			String username = t.getUsername();
			User user2 = service.getByUsername(username);
			
			if(user == null) {
				if(user2 ==null) {
					this.getBaseService().save(t);
				} else {
					result.setInfo("username not unqiue");
					result.setStatus(10);
					return result;
				}
			} else {
				if(user2 ==null || user2.getOpenid().equals(openid)) {
					User olduser = this.getBaseService().get(user.getId());
					t.setPassword(olduser.getPassword());
					t.setEmail(olduser.getEmail());
					t.setId(user.getId());
					this.getBaseService().update(t);
					
					List<IdentityAuth> userAuths = authservice.getByUsernameAndType(username, "100");
					if(userAuths != null) {
						for (IdentityAuth identityAuth : userAuths) {
							identityAuth.setComment("提交资料后需要重新实名认证");
							identityAuth.setAuthresult(0);
							authservice.update(identityAuth);
						}
					}
					
					
				} else {
					result.setInfo("username not unqiue");
					result.setStatus(10);
					return result;
				}
				
			}
			

			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
}
