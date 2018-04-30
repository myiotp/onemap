/**
 * 
 */
package com.onemap.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.IdentityAuth;
import com.onemap.domain.User;
import com.onemap.service.BaseService;
import com.onemap.service.IdentityAuthService;
import com.onemap.service.UserService;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/identityauth")
public class IdentityAuthAPIController extends BaseController<IdentityAuth, Integer> {
	@Autowired
	private IdentityAuthService service;
	@Autowired
	private UserService userservice;
	
	@Override
	BaseService<IdentityAuth, Integer> getBaseService() {
		return service;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "{username}/type/{type}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndType(@PathVariable("username") String username, @PathVariable("type") String type) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<IdentityAuth> auths = this.service.getByUsernameAndType(username, type);
			System.out.println(auths);
			if (auths.size() > 0) {
				User byUsername = userservice.getByUsername(username);
				int usertype = byUsername.getUsertype();
				IdentityAuth identityAuth = auths.get(0);
				identityAuth.setUsertype(usertype);
				result.setData(identityAuth);
			} else {
				User byUsername = userservice.getByUsername(username);
				int usertype = byUsername.getUsertype();
				IdentityAuth identityAuth = new IdentityAuth();
				identityAuth.setUsertype(usertype);
				result.setData(identityAuth);
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
	
	@RequestMapping(value = "{username}/types", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndTypes(@PathVariable("username") String username) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		Map<String, Integer> map = new HashMap<>();
		try {
			List<IdentityAuth> auths = this.service.getByUsernameAndType(username, "100");
			System.out.println(auths);
			if (auths.size() > 0) {
				User byUsername = userservice.getByUsername(username);
				int usertype = byUsername.getUsertype();
				IdentityAuth identityAuth = auths.get(0);
				identityAuth.setUsertype(usertype);
				map.put("100", identityAuth.getAuthresult());
			}
			auths = this.service.getByUsernameAndType(username, "200");
			System.out.println(auths);
			if (auths.size() > 0) {
				User byUsername = userservice.getByUsername(username);
				int usertype = byUsername.getUsertype();
				IdentityAuth identityAuth = auths.get(0);
				identityAuth.setUsertype(usertype);
				map.put("200", identityAuth.getAuthresult());
			}
			result.setData(map);
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
	public APIResponseBaseObject post(@RequestBody IdentityAuth t) {
		APIResponseBaseObject result = new APIResponseBaseObject();

		
		try {
			if (t.getId() == null) {
				List<IdentityAuth> byUsernameAndType = service.getByUsernameAndType(t.getUsername(), t.getType());
				for (IdentityAuth identityAuth : byUsernameAndType) {
					service.delete(identityAuth);
				}
				this.getBaseService().save(t);
			} else {
				this.getBaseService().update(t);
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
