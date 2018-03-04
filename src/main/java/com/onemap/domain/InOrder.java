package com.onemap.domain;

public class InOrder extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889733233653399676L;
	private Integer id;
	private String number;
	private String date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
