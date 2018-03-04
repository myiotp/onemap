package com.onemap.domain;

import java.io.Serializable;
import java.util.List;

public class NGTableObject<T extends BaseModel> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5598708145813391354L;

	private Integer total;
	private List<T> result;
		
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> results) {
		this.result = results;
	}	
}
