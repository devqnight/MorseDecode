package com.lp.morse;

import com.lp.exceptions.MorseBusinessException;
import com.lp.tools.CodeList;

public class TranslateToMorse {

    public static String toMorse(CodeList codes, String original) throws MorseBusinessException{
        String morse = "";
        for(int i=0;i<original.length();i++) {
            if(original.charAt(i) == ' ' && i != 0){
                morse += "/ ";
            } else if (original.charAt(i) == '\n') {
                morse += "\n";
            } else {
                String res;
                if( (res=codes.isIn(String.valueOf(original.charAt(i)).toUpperCase())) != "(/)"){
                    morse += res + " ";
                } else {
                    throw new MorseBusinessException(String.valueOf(original.charAt(i)));
                }
            }
        }
        morse= morse.trim();
        return morse;
    }
}
