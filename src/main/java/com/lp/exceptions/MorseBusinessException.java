package com.lp.exceptions;


/**
 * Business Exception for morse. 
 * Error while translating or interface
 */
public class MorseBusinessException extends MorseException {
    public MorseBusinessException(String msg){
        super(msg);
    }
}
