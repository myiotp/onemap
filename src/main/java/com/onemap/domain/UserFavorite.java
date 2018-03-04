/**
 * 
 */
package com.onemap.domain;

import lombok.Data;

/**
 * @author junpingwang
 *
 */
@Data
public class UserFavorite extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608601412306526L;
	private Integer id;
	private String username;
	private int vehicleinfoid;
	private int cargoinfoid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
