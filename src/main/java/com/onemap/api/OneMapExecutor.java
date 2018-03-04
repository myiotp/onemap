/**
 * @(#)Executor.java, 2012-12-5. 
 * 
 * Copyright 2012 , Inc. All rights reserved.
 */
package com.onemap.api;

/**
 * @author 
 * 
 */
public interface OneMapExecutor {

    public final static String API_SERVER = "";

    public static final int DEFAULT_HTTP_PORT = 80;

    public static final String USER_AGENT = "API SDK Java v0.1";

    public OneMapResponse execute(OneMapRequest request) throws OneMapException;
}
