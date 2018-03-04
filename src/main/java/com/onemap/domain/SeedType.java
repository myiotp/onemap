/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class SeedType  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String seedType;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSeedType() {
		return seedType;
	}
	public void setSeedType(String seedType) {
		this.seedType = seedType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
