package com.onemap.service;

import java.util.List;

import com.onemap.domain.IdentityAuth;

public interface IdentityAuthService extends BaseService<IdentityAuth, Integer> {
	List<IdentityAuth> getByUsername(String username) throws Exception;

	List<IdentityAuth> getByUsernameAndType(String username, String type) throws Exception;
}
