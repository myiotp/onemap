/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class MachineryXY  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private Double positionX;
	private Double positionY;
	private Integer machineryOperationId;
	private Integer positionSequence;
	
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
	public Integer getPositionSequence() {
		return positionSequence;
	}
	public void setPositionSequence(Integer positionSequence) {
		this.positionSequence = positionSequence;
	}
	public Integer getMachineryOperationId() {
		return machineryOperationId;
	}
	public void setMachineryOperationId(Integer machineryOperationId) {
		this.machineryOperationId = machineryOperationId;
	}
	
	
}
