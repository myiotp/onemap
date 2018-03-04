/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class UploadImage  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932601508441706526L;
	private Integer id;
	private String username;
	private String image;
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
