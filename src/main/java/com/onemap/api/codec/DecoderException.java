package com.onemap.api.codec;

/**
 * Thrown when a Decoder has encountered a failure condition during a decode. 
 */
public class DecoderException extends Exception {

	private static final long serialVersionUID = -8524444543640582985L;

	/**
     * Creates a DecoderException
     * 
     * @param pMessage A message with meaning to a human
     */
    public DecoderException(String pMessage) {
        super(pMessage);
    }

}  

