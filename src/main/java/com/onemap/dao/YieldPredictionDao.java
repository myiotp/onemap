package com.onemap.dao;

import java.util.List;

import com.onemap.domain.YieldPrediction;

public interface YieldPredictionDao extends BaseDao<YieldPrediction, Integer> {

	public List<YieldPrediction> listByCropType(YieldPrediction t);

}
