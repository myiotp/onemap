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
public class Province2City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3677369366991310806L;
	private String name;
	private Map<String,String> list;
	
	
}
