package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.User;

public interface UserDao extends BaseDao<User, Integer>{

	List<User> getByUsername(@Param("username") String username) throws Exception;
	List<User> getByOpenid(@Param("openid") String openid) throws Exception;

	void updateApproveRole(User t);
	void updatePassword(User t);
	void updateInternal(User t);
	void updateWX(User t);
}
