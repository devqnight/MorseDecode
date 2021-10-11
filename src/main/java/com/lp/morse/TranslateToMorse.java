package com.lp.morse;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import com.lp.exceptions.MorseBusinessException;
import com.lp.tools.CodeList;

public class TranslateToMorse {

    // Translates a String text to Morse Code
    
    public static String toMorse(CodeList codes, String original) throws MorseBusinessException{
        original = Normalizer.normalize(original, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        original = original.replaceAll("[-\\.]+", "");
        String morse = "";
        for(int i=0;i<original.length();i++) {
            if(original.charAt(i) == ' '){
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
