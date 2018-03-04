package com.onemap.service;

import java.util.List;

import com.onemap.domain.UploadImage;

public interface UploadImageService extends BaseService<UploadImage, Integer> {
	List<UploadImage> getByUsername(String username) throws Exception;

	List<UploadImage> getByUsernameAndType(String username, String type) throws Exception;
}
