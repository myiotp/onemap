package com.onemap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.GoodsDao;
import com.onemap.domain.Clause;
import com.onemap.domain.Goods;
import com.onemap.service.GoodsService;
import com.onemap.utl.pages.PaginatedArrayList;
import com.onemap.utl.pages.PaginatedList;
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods, Integer>
		implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public BaseDao<Goods, Integer> getDao() {
		return goodsDao;
	}

	@Override
	public List<Goods> getByUsername(String username, Goods t) throws Exception {
		//Goods t = new Goods();
		List<Clause> whereClause = new ArrayList<>();
		Clause clause = new Clause();
		clause.setColumn("username");
		clause.setOperator("=");
		clause.setValue(username);
		whereClause.add(clause);
		t.setWhereClause(whereClause);
		t.setOrderBy("id");
		t.setOrder("desc");
		return listByLimit(t);
		//return goodsDao.getByUsername(username);
	}
	@Override
	public PaginatedList<Goods> query(Goods t) throws Exception {
		if (t == null) {
			return new PaginatedArrayList<>(0, 0, 0);
		}

		// 查询数据库中记录的总数
		int total = this.getDao().count(t);

		// 构造带有分页信息的List
		PaginatedArrayList<Goods> resultList = new PaginatedArrayList<>(total, t.getPage(), t.getPageSize());
		t.setStartPos(resultList.getStartPos());

		List<Goods> queryResultList = goodsDao.query(t);
		resultList.addAll(queryResultList);

		return resultList;
	}


	
	@Override
	public int countByUsername(String username) throws Exception {
		return goodsDao.countByUsername(username);
	}

	@Override
	public void updateStatus(Goods t) {
		goodsDao.updateStatus(t);
	}

	@Override
	public List<Goods> getByUsernameAndStatus(String username, int status, Goods t) throws Exception {
		//Goods t = new Goods();
		List<Clause> whereClause = new ArrayList<>();
		
		{
			Clause clause = new Clause();
			clause.setColumn("username");
			clause.setOperator("=");
			clause.setValue(username);
			whereClause.add(clause);
		}
		{
			Clause clause = new Clause();
			clause.setColumn("status");
			clause.setOperator("=");
			clause.setValue(status);
			whereClause.add(clause);
		}
		t.setWhereClause(whereClause);
		t.setOrderBy("id");
		t.setOrder("desc");
		return listByLimit(t);
		//return goodsDao.getByUsernameAndStatus(username, status);
	}

	@Override
	public int countByUsernameAndStatus(String username, int status) throws Exception {
		return goodsDao.countByUsernameAndStatus(username, status);
	}

	@Override
	public List<Goods> getByUsernameAndNonStatus(String username, int status, Goods t) throws Exception {
		//Goods t = new Goods();
		List<Clause> whereClause = new ArrayList<>();
		
		{
			Clause clause = new Clause();
			clause.setColumn("username");
			clause.setOperator("=");
			clause.setValue(username);
			whereClause.add(clause);
		}
		{
			Clause clause = new Clause();
			clause.setColumn("status");
			clause.setOperator("!=");
			clause.setValue(status);
			whereClause.add(clause);
		}
		t.setWhereClause(whereClause);
		t.setOrderBy("id");
		t.setOrder("desc");
		return listByLimit(t);
		//return goodsDao.getByUsernameAndNonStatus(username, status);
	}

}
