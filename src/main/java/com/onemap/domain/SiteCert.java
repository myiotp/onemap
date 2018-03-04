package com.onemap.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SiteCert extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -6345955900177596217L;
	private Integer id;
	private Integer cooperativeId;
	private String certtype;
	@DateTimeFormat(pattern="yyyy年MM月dd日")  
	private Date certtime;
	private String validtime;
	private String certorganization;
	private String certnumber;
	private String certdescription;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCooperativeId() {
		return cooperativeId;
	}
	public void setCooperativeId(Integer cooperativeNumber) {
		this.cooperativeId = cooperativeNumber;
	}
	public String getCerttype() {
		return certtype;
	}
	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}
	public Date getCerttime() {
		return certtime;
	}
	public void setCerttime(Date certtime) {
		this.certtime = certtime;
	}
	public String getValidtime() {
		return validtime;
	}
	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}
	public String getCertorganization() {
		return certorganization;
	}
	public void setCertorganization(String certorganization) {
		this.certorganization = certorganization;
	}
	public String getCertnumber() {
		return certnumber;
	}
	public void setCertnumber(String certnumber) {
		this.certnumber = certnumber;
	}
	public String getCertdescription() {
		return certdescription;
	}
	public void setCertdescription(String certdescription) {
		this.certdescription = certdescription;
	}



}
