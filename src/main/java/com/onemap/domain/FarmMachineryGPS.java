package com.onemap.domain;

import java.util.Date;

public class FarmMachineryGPS extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345955900177596917L;
	private Integer id;
	private String machineryNumber;
	private String gpsdevice;
	private String enginenumber;
	private String driverlicense,licensenumber;
	private Integer cooperativeId;
	private Double positionX;
	private Double positionY;
	private Date operationTime;
//	private Site cooperative;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMachineryNumber() {
		return machineryNumber;
	}
	public void setMachineryNumber(String machineryNumber) {
		this.machineryNumber = machineryNumber;
	}
	public String getGpsdevice() {
		return gpsdevice;
	}
	public void setGpsdevice(String gpsdevice) {
		this.gpsdevice = gpsdevice;
	}
	public String getEnginenumber() {
		return enginenumber;
	}
	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}
	public String getDriverlicense() {
		return driverlicense;
	}
	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}
	public String getLicensenumber() {
		return licensenumber;
	}
	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}
	public Integer getCooperativeId() {
		return cooperativeId;
	}
	public void setCooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}
	public Double getPositionX() {
		return positionX;
	}
	public void setPositionX(Double positionX) {
		this.positionX = positionX;
	}
	public Double getPositionY() {
		return positionY;
	}
	public void setPositionY(Double positionY) {
		this.positionY = positionY;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
//	public Site getCooperative() {
//		return cooperative;
//	}
//	public void setCooperative(Site cooperative) {
//		this.cooperative = cooperative;
//	}

		

}
