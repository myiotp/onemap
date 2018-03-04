package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.UploadImage;

public interface UploadImageDao extends BaseDao<UploadImage, Integer>{

	List<UploadImage> getByUsername(@Param("username") String username) throws Exception;
	List<UploadImage> getByUsernameAndType(@Param("username") String username, @Param("type") String type) throws Exception;
}
