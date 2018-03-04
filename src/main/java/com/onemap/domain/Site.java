package com.onemap.domain;

import java.util.Date;

public class Site extends AbstractStorehouse {

	/**
	 *
	 */
	private static final long serialVersionUID = -6345955900177596917L;
	private Integer id;
	private String cooperativeNumber;
	private String cooperativeName;
	private String owner;
	private String telephone;
	private String memo;
	private Double gpsx;
	private Double gpsy;
	private String character;
	private String size;
	private String address;
	private String website;
	private String legalperson;
	private String email;
	private String qq;
	private String wechat;
	private String description;
	private String qualification;
	private String collector;
	private Date collecttime;
	private String province;
	private String city1;
	private String city2;
	private String zipcode;

	public String getCooperativeNumber() {
		return cooperativeNumber;
	}
	public void setCooperativeNumber(String cooperativeNumber) {
		this.cooperativeNumber = cooperativeNumber;
	}
	public String getCooperativeName() {
		return cooperativeName;
	}
	public void setCooperativeName(String cooperativeName) {
		this.cooperativeName = cooperativeName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
    public Integer getId() {
		return id;
	}
	@Override
    public void setId(Integer id) {
		this.id = id;
	}
	public Double getGpsx() {
		return gpsx;
	}
	public void setGpsx(Double gpsx) {
		this.gpsx = gpsx;
	}
	public Double getGpsy() {
		return gpsy;
	}
	public void setGpsy(Double gpsy) {
		this.gpsy = gpsy;
	}
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getLegalperson() {
        return legalperson;
    }
    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getCollector() {
        return collector;
    }
    public void setCollector(String collector) {
        this.collector = collector;
    }
    public Date getCollecttime() {
        return collecttime;
    }
    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
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
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}



}
