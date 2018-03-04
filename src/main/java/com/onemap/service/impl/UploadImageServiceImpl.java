/**
 * 
 */
package com.onemap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.UploadImageDao;
import com.onemap.domain.UploadImage;
import com.onemap.service.UploadImageService;

/**
 * @author junpingwang
 * 
 */
@Service
public class UploadImageServiceImpl extends BaseServiceImpl<UploadImage, Integer> implements UploadImageService  {
	@Autowired
	private UploadImageDao dao;

	@Override
	public List<UploadImage> getByUsername(String username)
			throws Exception {
		return dao.getByUsername(username);
	}

	@Override
	public BaseDao<UploadImage, Integer> getDao() {
		return dao;
	}

	@Override
	public List<UploadImage> getByUsernameAndType(String username, String type) throws Exception {
		return dao.getByUsernameAndType(username, type);
	}


}
