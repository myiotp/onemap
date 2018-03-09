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
public class MachineryXY  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932608508441206526L;
	private Integer id;
	private Double positionX;
	private Double positionY;
	private Double speed;
	private Integer machineryOperationId;
	private Long positionSequence;
	
	public Integer getId() {
		return id;
	}
	
	
	
}
