package com.lp.morse;

import com.lp.tools.CodeList;

public class TranslateToMorse {

    public static String toMorse(CodeList codes, String original){
        String morse = "";
        for(int i=0;i<original.length();i++) {
            if(original.charAt(i) == ' ' && i != 0){
                morse += "/ ";
            } else if (original.charAt(i) == '\n') {
                morse += "\n";
            } else {
                String res;
                if( (res=codes.isIn(String.valueOf(original.charAt(i)).toUpperCase())) != ""){
                    morse += res + " ";
                } else {
                    morse += "? ";
                }
            }
        }
        morse= morse.trim();
        return morse;
    }
}
