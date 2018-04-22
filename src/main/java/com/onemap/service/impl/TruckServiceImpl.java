package com.onemap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemap.dao.BaseDao;
import com.onemap.dao.TruckDao;
import com.onemap.domain.Clause;
import com.onemap.domain.Goods;
import com.onemap.domain.Truck;
import com.onemap.service.TruckService;
import com.onemap.utl.pages.PaginatedArrayList;
import com.onemap.utl.pages.PaginatedList;

@Service
public class TruckServiceImpl extends BaseServiceImpl<Truck, Integer> implements TruckService {

	@Autowired
	private TruckDao truckDao;

	@Override
	public BaseDao<Truck, Integer> getDao() {
		return truckDao;
	}

	@Override
	protected void fillDetail(Truck truck) throws Exception {
	}

	@Override
	public List<Truck> getByUsername(String username,Truck t) throws Exception {
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
	}

	@Override
	public PaginatedList<Truck> query(Truck t) throws Exception {
		if (t == null) {
			return new PaginatedArrayList<>(0, 0, 0);
		}

		// 查询数据库中记录的总数
		int total = this.getDao().count(t);

		// 构造带有分页信息的List
		PaginatedArrayList<Truck> resultList = new PaginatedArrayList<>(total, t.getPage(), t.getPageSize());
		t.setStartPos(resultList.getStartPos());

		List<Truck> queryResultList = truckDao.query(t);
		resultList.addAll(queryResultList);

		return resultList;
	}

	@Override
	public int countByUsername(String username) throws Exception {
		return truckDao.countByUsername(username);
	}

	@Override
	public void updateStatus(Truck t) {
		truckDao.updateStatus(t);
		
	}

	@Override
	public List<Truck> getByUsernameAndStatus(String username, int status,Truck t) throws Exception {
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
	}

	@Override
	public List<Truck> getByUsernameAndNonStatus(String username, int status,Truck t) throws Exception {
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
	}
}
