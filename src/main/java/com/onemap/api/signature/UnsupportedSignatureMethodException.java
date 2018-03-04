package com.onemap.api.signature;


/**
 * 不支持的签名方法的异常
 * 
 * @author wang
 */
public class UnsupportedSignatureMethodException extends Exception {

    private static final long serialVersionUID = 1108134192433376266L;

    public UnsupportedSignatureMethodException(String msg) {
        super(msg);
    }
}
