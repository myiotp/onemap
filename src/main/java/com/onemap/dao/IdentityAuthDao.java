package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.IdentityAuth;

public interface IdentityAuthDao extends BaseDao<IdentityAuth, Integer>{

	List<IdentityAuth> getByUsername(@Param("username") String username) throws Exception;
	List<IdentityAuth> getByUsernameAndType(@Param("username") String username, @Param("type") String type) throws Exception;
}
