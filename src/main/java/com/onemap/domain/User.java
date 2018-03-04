/**
 * 
 */
package com.onemap.domain;

import lombok.Data;

/**
 * @author junpingwang
 *
 */
@Data
public class User  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String username,wx_avatarUrl,wx_nickName,openid,wx_gender,wx_city,wx_province,wx_country,wx_unionid;
	private String password;
	private String email;
	private String realname,company,mobilephone,idcard,province,city,address,emergency,emergencyphone;
	private int usertype = 1;
	private boolean b1,b2,b3;
	private int approverole;
	private String actiontype;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmergency() {
		return emergency;
	}
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}
	public String getEmergencyphone() {
		return emergencyphone;
	}
	public void setEmergencyphone(String emergencyphone) {
		this.emergencyphone = emergencyphone;
	}
	
}
