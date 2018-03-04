package com.onemap.domain;

public class Region extends AbstractStorehouse {

	/**
	 * 库区
	 */
	private static final long serialVersionUID = -787816868755244413L;

	private Integer floor;
	private Integer warehouseId;
	private Landblock warehouse;

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Landblock getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Landblock warehouse) {
		this.warehouse = warehouse;
	}

}
