/**
 * 
 */
package com.onemap.domain;

import java.util.Date;

/**
 * @author junpingwang
 *
 */
public class YieldPrediction  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String blockNumber;
	private String cropType;
	private Integer blockId;
	private Integer cropTypeId;
	private Date predictTime;
	private Double predictYield;
	private Double actualYield;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public Date getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(Date predictTime) {
		this.predictTime = predictTime;
	}
	public Double getPredictYield() {
		return predictYield;
	}
	public void setPredictYield(Double predictYield) {
		this.predictYield = predictYield;
	}
	public Double getActualYield() {
		return actualYield;
	}
	public void setActualYield(Double actualYield) {
		this.actualYield = actualYield;
	}
	public Integer getBlockId() {
		return blockId;
	}
	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Integer getCropTypeId() {
		return cropTypeId;
	}
	public void setCropTypeId(Integer cropTypeId) {
		this.cropTypeId = cropTypeId;
	}
	
	
}
