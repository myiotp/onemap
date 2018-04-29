/**
 * 
 */
package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.UserDao;
import com.onemap.domain.User;
import com.onemap.service.UserService;

/**
 * @author junpingwang
 * 
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService  {
	@Autowired
	private UserDao userDao;

	@Override
	public User getByUsername(String username)
			throws Exception {
		 List<User> list = userDao.getByUsername(username);
		 if(list == null || list.size() == 0) {
			 return null;
		 }
		 return list.get(0);
	}

	@Override
	public BaseDao<User, Integer> getDao() {
		return userDao;
	}

	@Override
	public void updateApproveRole(User t) {
		userDao.updateApproveRole(t);
		
	}

	@Override
	public void updatePassword(User t) {
		userDao.updatePassword(t);		
	}

	@Override
	public User getByOpenid(String openid) throws Exception {
		 List<User> list = userDao.getByOpenid(openid);
		 if(list == null || list.size() == 0) {
			 return null;
		 }
		 return list.get(0);
	}

	@Override
	public void updateWX(User t) {
		userDao.updateWX(t);
	}

	@Override
	public void updateInternal(User t) {
		userDao.updateInternal(t);
		
	}


}
