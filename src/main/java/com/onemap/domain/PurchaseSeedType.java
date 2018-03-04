/**
 * 
 */
package com.onemap.domain;

import java.util.Date;

/**
 * @author junpingwang
 *
 */
public class PurchaseSeedType  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508241206526L;
	private Integer id;
	private Integer seedTypeId, cooperativeId;
	private Date purchaseTime;
	private Double purchaseAmount;
	private String seedType, name,serialNumber,level,producer,producerTel,seller,sellerTel,brief,purchaser;
	private Site cooperative;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeedTypeId() {
		return seedTypeId;
	}
	public void setSeedTypeId(Integer seedTypeId) {
		this.seedTypeId = seedTypeId;
	}
	public Integer getCooperativeId() {
		return cooperativeId;
	}
	public void setCooperativeId(Integer cooperativeId) {
		this.cooperativeId = cooperativeId;
	}
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public Double getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getProducerTel() {
		return producerTel;
	}
	public void setProducerTel(String producerTel) {
		this.producerTel = producerTel;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getSellerTel() {
		return sellerTel;
	}
	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public String getSeedType() {
		return seedType;
	}
	public void setSeedType(String seedType) {
		this.seedType = seedType;
	}
	public Site getCooperative() {
		return cooperative;
	}
	public void setCooperative(Site cooperative) {
		this.cooperative = cooperative;
	}
	
	
}
