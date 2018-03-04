package com.onemap.api.signature;

/**
 * 无效签名的异常 Thrown when a signature is invalid.
 * 
 * @author wang
 */
public class InvalidSignatureException extends Exception {

    private static final long serialVersionUID = 2072127674737732954L;

    public InvalidSignatureException(String msg) {
        super(msg);
    }

}
