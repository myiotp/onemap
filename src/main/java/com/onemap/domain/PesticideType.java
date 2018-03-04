/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class PesticideType  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String pesticideType;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPesticideType() {
		return pesticideType;
	}
	public void setPesticideType(String pesticideType) {
		this.pesticideType = pesticideType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
