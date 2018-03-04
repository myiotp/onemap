package com.onemap.service;

import java.util.List;

import com.onemap.domain.YieldPrediction;

public interface YieldPredictionService extends BaseService<YieldPrediction, Integer> {

	public List<YieldPrediction> listByCropType(YieldPrediction t) throws Exception;

}
