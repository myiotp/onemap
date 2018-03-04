package com.onemap.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onemap.domain.Goods;

public interface GoodsDao extends BaseDao<Goods, Integer> {
	List<Goods> getByUsername(@Param("username") String username) throws Exception;
	List<Goods> getByUsernameAndStatus(@Param("username") String username, @Param("status") int status) throws Exception;
	List<Goods> getByUsernameAndNonStatus(@Param("username") String username, @Param("status") int status) throws Exception;
	
	List<Goods> query(Goods t);
	int countByUsername(@Param("username") String username) throws Exception;
	int countByUsernameAndStatus(@Param("username") String username, @Param("status") int status) throws Exception;
	void updateStatus(Goods t);
}
