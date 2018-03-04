package com.onemap.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class APIResponseBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1758198331543317824L;
	private String info;
	private int status;
	private Object data;
	private int size;
	
	public int getSize() {
		if(data == null || !(data instanceof List)) {
			return 0;
		}
		@SuppressWarnings("rawtypes")
		List list = (List)data;
		size = list.size();
		return size;
	}
}
