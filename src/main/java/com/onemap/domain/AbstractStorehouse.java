package com.onemap.domain;

public class AbstractStorehouse extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3139565389646694336L;
	private Integer id;
	private String name;
	private Integer coreCapacity;
	private Integer relatedCapacity;
	private Integer commonCapacity;
	private Integer coreCurrentAmount;
	private Integer relatedCurrentAmount;
	private Integer commonCurrentAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCoreCapacity() {
		return coreCapacity;
	}

	public void setCoreCapacity(Integer coreCapacity) {
		this.coreCapacity = coreCapacity;
	}

	public Integer getRelatedCapacity() {
		return relatedCapacity;
	}

	public void setRelatedCapacity(Integer relatedCapacity) {
		this.relatedCapacity = relatedCapacity;
	}

	public Integer getCommonCapacity() {
		return commonCapacity;
	}

	public void setCommonCapacity(Integer commonCapacity) {
		this.commonCapacity = commonCapacity;
	}

	public Integer getCoreCurrentAmount() {
		return coreCurrentAmount;
	}

	public void setCoreCurrentAmount(Integer coreCurrentAmount) {
		this.coreCurrentAmount = coreCurrentAmount;
	}

	public Integer getRelatedCurrentAmount() {
		return relatedCurrentAmount;
	}

	public void setRelatedCurrentAmount(Integer relatedCurrentAmount) {
		this.relatedCurrentAmount = relatedCurrentAmount;
	}

	public Integer getCommonCurrentAmount() {
		return commonCurrentAmount;
	}

	public void setCommonCurrentAmount(Integer commonCurrentAmount) {
		this.commonCurrentAmount = commonCurrentAmount;
	}

}
