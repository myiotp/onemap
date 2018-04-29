package com.onemap.service;

import com.onemap.domain.User;

public interface UserService extends BaseService<User, Integer> {
	User getByUsername(String username)
			throws Exception;

	void updateApproveRole(User t);
	void updatePassword(User t);
	void updateInternal(User t);
	void updateWX(User t);

	User getByOpenid(String openid) throws Exception;
}
