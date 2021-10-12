package com.lp.exceptions;

/**
 * DAO Exception for Morse. 
 * Error while using the DAO.
 */
public class MorseDaoException extends MorseException{
    public MorseDaoException(String msg){
        super(msg);
    }
}
