package com.onemap.domain;

public class Area extends AbstractStorehouse {

	/**
	 *货区
	 */
	private static final long serialVersionUID = -617423878422426056L;
	private Integer regionId;
	private Region region;
	private Integer coreThreshold;
	private Integer relatedThreshold;
	private Integer commonThreshold;
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Integer getCoreThreshold() {
		return coreThreshold;
	}
	public void setCoreThreshold(Integer coreThreshold) {
		this.coreThreshold = coreThreshold;
	}
	public Integer getRelatedThreshold() {
		return relatedThreshold;
	}
	public void setRelatedThreshold(Integer relatedThreshold) {
		this.relatedThreshold = relatedThreshold;
	}
	public Integer getCommonThreshold() {
		return commonThreshold;
	}
	public void setCommonThreshold(Integer commonThreshold) {
		this.commonThreshold = commonThreshold;
	}
	
	
}
