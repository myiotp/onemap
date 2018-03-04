package com.onemap.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class Clause implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488035729598435642L;

	private String column;
	private String operator;
	private Object value;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
