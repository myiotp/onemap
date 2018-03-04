/**
 * @(#)UnauthorizedException.java, Jan 21, 2013. 
 * 
 * Copyright 2012 , Inc. All rights reserved.
 */
package com.onemap.api;

/**
 * 客户端授权失败时抛出的异常
 * 
 * @author 
 * 
 */
public class AuthorizationException extends OneMapClientException {

	public AuthorizationException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778378769543685112L;

}
