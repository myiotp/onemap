/**
 * @(#)ClientException.java, 2012-12-11. 
 * 
 * Copyright 2012 , Inc. All rights reserved.
 */
package com.onemap.api;

/**
 * @author 
 * 
 */
public abstract class OneMapClientException extends OneMapException {

    private static final long serialVersionUID = 4388460521854290492L;

    
    public OneMapClientException(String msg){
        super(null, null, msg);
    }

}
