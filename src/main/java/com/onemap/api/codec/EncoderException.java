package com.onemap.api.codec;

/**
 * Thrown when there is a failure condition during the encoding process.  This
 * exception is thrown when an Encoder encounters a encoding specific exception
 * such as invalid data, inability to calculate a checksum, characters outside of the 
 * expected range.
 */
public class EncoderException extends Exception {

	private static final long serialVersionUID = 639276068312354225L;

	/**
     * Creates a new instance of this exception with an useful message.
     * 
     * @param pMessage a useful message relating to the encoder specific error.
     */
    public EncoderException(String pMessage) {
        super(pMessage);
    }
}  

