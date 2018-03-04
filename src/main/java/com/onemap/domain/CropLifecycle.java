/**
 * 
 */
package com.onemap.domain;

import java.util.Date;

/**
 * @author junpingwang
 * 
 */
public class CropLifecycle extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441203526L;
	private Integer id;
	private Integer blockId;
	private Integer cropTypeId;
	private Double positionX, positionY;
	private Date gatherTime;
	private String windDirection;
	private Double pressure, humidity, windSpeed, rainfall, temperature, soilTemperature, soilHumidity;
	private String  radiation,
			soilN, soilP, soilK, leafArea, leafGreen;

	private String picture, video;
	private String blockNumber;
	private String cropType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getGatherTime() {
		return gatherTime;
	}

	public void setGatherTime(Date gatherTime) {
		this.gatherTime = gatherTime;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getRainfall() {
		return rainfall;
	}

	public void setRainfall(Double rainfall) {
		this.rainfall = rainfall;
	}

	public String getRadiation() {
		return radiation;
	}

	public void setRadiation(String radiation) {
		this.radiation = radiation;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getSoilN() {
		return soilN;
	}

	public void setSoilN(String soilN) {
		this.soilN = soilN;
	}

	public String getSoilP() {
		return soilP;
	}

	public void setSoilP(String soilP) {
		this.soilP = soilP;
	}

	public String getSoilK() {
		return soilK;
	}

	public void setSoilK(String soilK) {
		this.soilK = soilK;
	}

	public Double getSoilTemperature() {
		return soilTemperature;
	}

	public void setSoilTemperature(Double soilTemperature) {
		this.soilTemperature = soilTemperature;
	}

	public Double getSoilHumidity() {
		return soilHumidity;
	}

	public void setSoilHumidity(Double soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	public String getLeafArea() {
		return leafArea;
	}

	public void setLeafArea(String leafArea) {
		this.leafArea = leafArea;
	}

	public String getLeafGreen() {
		return leafGreen;
	}

	public void setLeafGreen(String leafGreen) {
		this.leafGreen = leafGreen;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
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

}
