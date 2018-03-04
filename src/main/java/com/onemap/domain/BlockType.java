/**
 * 
 */
package com.onemap.domain;

/**
 * @author junpingwang
 *
 */
public class BlockType  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private String blockType;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlockType() {
		return blockType;
	}
	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
