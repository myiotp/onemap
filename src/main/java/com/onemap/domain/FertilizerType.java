/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class FertilizerType  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String fertilizerType;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFertilizerType() {
		return fertilizerType;
	}
	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
