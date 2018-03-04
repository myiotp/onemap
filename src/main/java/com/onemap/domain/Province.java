/**
 * 
 */
package com.onemap.domain;

import lombok.Data;

/**
 *
 */
@Data
public class Province  extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7932208518441206526L;
	private Integer seqno;
	private String province_id;
	private String province_name;
	private String province_simple;
	private String parent;
	private double lng, lat;
	public Integer getSeqno() {
		return seqno;
	}
	@Override
	public Object getId() {
		return province_id;
	}
	
	
}
