/**
 * 
 */
package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.IdentityAuthDao;
import com.onemap.domain.IdentityAuth;
import com.onemap.service.IdentityAuthService;

/**
 * @author junpingwang
 * 
 */
@Service
public class IdentityAuthServiceImpl extends BaseServiceImpl<IdentityAuth, Integer> implements IdentityAuthService  {
	@Autowired
	private IdentityAuthDao dao;

	@Override
	public List<IdentityAuth> getByUsername(String username)
			throws Exception {
		return dao.getByUsername(username);
	}

	@Override
	public BaseDao<IdentityAuth, Integer> getDao() {
		return dao;
	}

	@Override
	public List<IdentityAuth> getByUsernameAndType(String username, String type) throws Exception {
		return dao.getByUsernameAndType(username, type);
	}


}
