package com.onemap.domain;

public class InOrderDetail extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7658143490908444186L;
	private Integer id;
	private Integer truckId;
	private Integer goodsId;
	private Integer amount;
	private Integer inOrderId;
	private Truck truck;
	private Goods goods;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTruckId() {
		return truckId;
	}
	public void setTruckId(Integer truckId) {
		this.truckId = truckId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getInOrderId() {
		return inOrderId;
	}
	public void setInOrderId(Integer inOrderId) {
		this.inOrderId = inOrderId;
	}
	public Truck getTruck() {
		return truck;
	}
	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	

}
