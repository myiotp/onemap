/**
 * 
 */
package com.onemap.domain;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 *
 */
@Data
public class Province2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 391989992251749892L;
	private String province_id;
	private String province_name;
	private String province_simple;
	private Map<String, Province2City> cityList;

}
