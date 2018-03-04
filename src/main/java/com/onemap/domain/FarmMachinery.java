package com.onemap.domain;

import java.util.Date;

public class FarmMachinery extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345955900177596917L;
	private Integer id;
	private String machineryNumber;
	private String machineryType;
	private String status;
	private String ownerName, ownerTelephone;
	private Integer cooperativeId;
	private Date operationTime;
	private Site cooperative;

	private String owneridnumber;
	private String owneraddress;
	private String gpsdevice;
	private String gpsinstaller;
	private String gpstype;
	private Date gpsinstalldate;
	private String licensenumber;
	private String xinghao;
	private Double gefu;
	private String vendor;
	private String workfor;
	private Double machinerysize;
	private Double weight;
	private Double volume;
	private String brand;
	private String automation;
	private String powertype;
	private Date purchasetime;
	private Date producetime;
	private String driverlicense;
	private String enginenumber;
	private String bodynumber;
	private String machinerypower;
	private String province;
	private String city1;
	private String zipcode;

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

	public String getMachineryType() {
		return machineryType;
	}

	public void setMachineryType(String machineryType) {
		this.machineryType = machineryType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTelephone() {
		return ownerTelephone;
	}

	public void setOwnerTelephone(String ownerTelephone) {
		this.ownerTelephone = ownerTelephone;
	}

	public Integer getCooperativeId() {
		return cooperativeId;
	}

	public void setCooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public Site getCooperative() {
		return cooperative;
	}

	public void setCooperative(Site cooperative) {
		this.cooperative = cooperative;
	}

	public String getOwneridnumber() {
		return owneridnumber;
	}

	public void setOwneridnumber(String owneridnumber) {
		this.owneridnumber = owneridnumber;
	}

	public String getOwneraddress() {
		return owneraddress;
	}

	public void setOwneraddress(String owneraddress) {
		this.owneraddress = owneraddress;
	}

	public String getGpsdevice() {
		return gpsdevice;
	}

	public void setGpsdevice(String gpsdevice) {
		this.gpsdevice = gpsdevice;
	}

	public String getGpsinstaller() {
		return gpsinstaller;
	}

	public void setGpsinstaller(String gpsinstaller) {
		this.gpsinstaller = gpsinstaller;
	}

	public String getGpstype() {
		return gpstype;
	}

	public void setGpstype(String gpstype) {
		this.gpstype = gpstype;
	}

	public Date getGpsinstalldate() {
		return gpsinstalldate;
	}

	public void setGpsinstalldate(Date gpsinstalldate) {
		this.gpsinstalldate = gpsinstalldate;
	}

	public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	public String getXinghao() {
		return xinghao;
	}

	public void setXinghao(String xinghao) {
		this.xinghao = xinghao;
	}

	public Double getGefu() {
		return gefu;
	}

	public void setGefu(Double gefu) {
		this.gefu = gefu;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getWorkfor() {
		return workfor;
	}

	public void setWorkfor(String workfor) {
		this.workfor = workfor;
	}

	public Double getMachinerysize() {
		return machinerysize;
	}

	public void setMachinerysize(Double machinerysize) {
		this.machinerysize = machinerysize;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAutomation() {
		return automation;
	}

	public void setAutomation(String automation) {
		this.automation = automation;
	}

	public String getPowertype() {
		return powertype;
	}

	public void setPowertype(String powertype) {
		this.powertype = powertype;
	}

	public Date getPurchasetime() {
		return purchasetime;
	}

	public void setPurchasetime(Date purchasetime) {
		this.purchasetime = purchasetime;
	}

	public Date getProducetime() {
		return producetime;
	}

	public void setProducetime(Date producetime) {
		this.producetime = producetime;
	}

	public String getDriverlicense() {
		return driverlicense;
	}

	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}

	public String getEnginenumber() {
		return enginenumber;
	}

	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}

	public String getBodynumber() {
		return bodynumber;
	}

	public void setBodynumber(String bodynumber) {
		this.bodynumber = bodynumber;
	}

	public String getMachinerypower() {
		return machinerypower;
	}

	public void setMachinerypower(String machinerypower) {
		this.machinerypower = machinerypower;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
