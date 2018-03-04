package com.onemap.domain;

public class OutOrderRecommendInfoItem extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6412301129605882398L;
	private Area area;
	private Integer amount;

	public OutOrderRecommendInfoItem() {

	}

	public OutOrderRecommendInfoItem(Area area, Integer amount) {
		this.area = area;
		this.amount = amount;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
