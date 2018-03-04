package com.onemap.service;

import java.util.List;

import com.onemap.domain.Goods;

public interface GoodsService extends BaseService<Goods, Integer> {
	List<Goods> getByUsername(String username, Goods t) throws Exception;
	List<Goods> getByUsernameAndStatus(String username, int status, Goods t) throws Exception;
	List<Goods> getByUsernameAndNonStatus(String username, int status, Goods t) throws Exception;
	List<Goods> query(Goods t) throws Exception;
	int countByUsername(String username) throws Exception;
	int countByUsernameAndStatus(String username, int status) throws Exception;
	void updateStatus(Goods t);
}
