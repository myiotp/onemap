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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.Configuration;
import com.onemap.domain.User;
import com.onemap.domain.UserVehicle;
import com.onemap.service.BaseService;
import com.onemap.service.UserService;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, Integer> {
	@Autowired
	private UserService service;

	@Override
	BaseService<User, Integer> getBaseService() {
		return service;
	}

	@RequestMapping("id/{id}")
	@ResponseBody
	public User getJSONObject(@PathVariable("id") Integer id) throws Exception {
		User tInDb = this.getBaseService().get(id);
		// if (tInDb != null) {
		// SeedType type = typeService.get(tInDb.getSeedTypeId());
		// tInDb.setSeedType(type.getSeedType());
		// }
		
		return tInDb;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView addOrUpdate(User t) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		
		// System.out.println(modelAndView);
		if (t.getId() == null) {
			return modelAndView;
		}
		String actiontype = t.getActiontype();
		System.out.println("actiontype:" + actiontype);
		User tInDb = this.getBaseService().get(t.getId());
		if(tInDb != null) {
			tInDb.setActiontype(actiontype);
		}
		modelAndView.addObject("currentObj", tInDb);
		addDataForEditView(modelAndView);

		return modelAndView;
	}
	
	@RequestMapping(value = "username/{username}", method = RequestMethod.GET, produces = "text/javascript")
	@ResponseBody
	public String checkIfExists(@PathVariable("username") String username)
			throws Exception {
		User user = service.getByUsername(username);
		if (user == null)
			return "0";
		else
			return "1";
	}

	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request, User user) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String result = "login";
		if (!currentUser.isAuthenticated()) {
			result = login(currentUser, user.getUsername(), user.getPassword());
		} else {// 重复登录
			User currentuser = (User) currentUser.getPrincipal();
			if (!currentuser.getUsername().equalsIgnoreCase(user.getUsername())) {// 如果登录名不同
				currentUser.logout();
				result = login(currentUser, user.getUsername(),
						user.getPassword());
			} else {
				result = "success";
			}
		}
		ModelAndView modelAndView = null;
		if (result.equals("success")) {
			request.getSession().setAttribute("userid", user.getUsername());
			User user2 = service.getByUsername(user.getUsername());
			//System.out.println("login:" + user2);
			if (user2 != null) {
				request.getSession().setAttribute("approverole", user2.getApproverole());
			}
			if ("admin".equals(user.getUsername())) {
				request.getSession().setAttribute("admin", true);
			} else {
				request.getSession().setAttribute("admin", false);
			}

			modelAndView = new ModelAndView("success");
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("result", result);
		}
		return modelAndView;

	}

	@RequestMapping("regCheck")
	public ModelAndView regCheck(User user) {
		String originalPassword = user.getPassword();
		try {
			User existingUser = service.getByUsername(user.getUsername());
			if (existingUser != null) {
				ModelAndView modelAndView = new ModelAndView("reg");
				modelAndView.addObject("result", "existingUser");
				return modelAndView;
			} else {
				Md5Hash hash = new Md5Hash(originalPassword);
				user.setPassword(hash.toHex());
				// System.out.println(user);
				service.save(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Subject currentUser = SecurityUtils.getSubject();
		String result = "login";
		if (!currentUser.isAuthenticated()) {
			result = login(currentUser, user.getUsername(), originalPassword);
		} else {// 重复登录
			User currentuser = (User) currentUser.getPrincipal();
			if (!currentuser.getUsername().equalsIgnoreCase(user.getUsername())) {// 如果登录名不同
				currentUser.logout();
				result = login(currentUser, user.getUsername(),
						originalPassword);
			} else {
				result = "success";
			}
		}
		ModelAndView modelAndView = null;
		if (result.equals("success")) {
			modelAndView = new ModelAndView("success");
		} else {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("result", result);
		}
		return modelAndView;

	}

	@RequestMapping("login")
	public ModelAndView login(User user) {

		return new ModelAndView("login");

	}

	@RequestMapping("period")
	public ModelAndView period(User user) {
		return new ModelAndView("period");
	}

	@RequestMapping("switchPeriod")
	public String switchPeriod(HttpServletRequest request, User user,
			@RequestParam int year, @RequestParam int period) {
		// System.out.println(year +":" + period);
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("period", period);
		return "redirect:../welcomePage/view";
	}

	@RequestMapping("logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			currentUser.logout();
		}
		// return new ModelAndView("login");
		return "redirect:../welcomePage/view";
	}

	private String login(Subject currentUser, String username, String password) {
		String result = "login";
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		token.setRememberMe(false);
		try {
			currentUser.login(token);
			result = "success";
		} catch (UnknownAccountException uae) {
			result = "failure";
		} catch (IncorrectCredentialsException ice) {
			result = "failure";
		} catch (LockedAccountException lae) {
			result = "failure";
		} catch (AuthenticationException ae) {
			result = "failure";
		}
		return result;
	}

	@RequestMapping("success")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView("success");
		return modelAndView;
	}

	@RequestMapping("reg")
	public ModelAndView reg(User user) {

		return new ModelAndView("reg");

	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@RequestMapping("editlist")
	public ModelAndView editlist(HttpServletRequest request, User t)
			throws Exception {
		List<User> tList = null;
		ModelAndView modelAndView = new ModelAndView();
		tList = getBaseService().listByLimit(t);

		if (tList != null) {
			for (Iterator<User> iterator = tList.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				user.setPassword("************");
			}
		}

		int totalCount = getBaseService().count(t);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("totalPage",
				(int) Math.ceil((double) totalCount / t.getPageSize()));
		modelAndView.addObject("resultList", tList);
		return modelAndView;
	}

	@RequestMapping("save")
	public String save(User t) throws Exception {
		String originalPassword = t.getPassword();
		Md5Hash hash = new Md5Hash(originalPassword);
		t.setPassword(hash.toHex());

		if (t.getId() == null) {
			this.getBaseService().save(t);
		} else {
			this.getBaseService().update(t);
		}
		return "redirect:./editlist";
	}
	
	@RequestMapping("updateuser")
	public String updateuser(User t) throws Exception {
		if (t.getId() == null) {
			//
		} else {
			String actiontype = t.getActiontype();
			if(actiontype == null) {
				//
			} else {
				if(actiontype.equals("100")) {
					service.updateApproveRole(t);
				} else if(actiontype.equals("200")) {
					String originalPassword = t.getPassword();
					Md5Hash hash = new Md5Hash(originalPassword);
					t.setPassword(hash.toHex());
					service.updatePassword(t);
				} else if(actiontype.equals("300")) {
					service.updateInternal(t);
				}
			}
			
		}
		return "redirect:./editlist";
	}
}
