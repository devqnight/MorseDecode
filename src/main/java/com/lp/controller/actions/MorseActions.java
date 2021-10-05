package com.lp.controller.actions;

import java.io.IOException;

import com.lp.exceptions.MorseBusinessException;
import com.lp.morse.Translator;
import com.lp.tools.Tools;

public class MorseActions {

    public static String translate(String textString) throws MorseBusinessException, IOException{
        String result ="";
        switch(Tools.getTools().translateWhich(textString)){
            case "String":
                result = Translator.getTranslator().toText(textString);
                break;
            case "Morse":
                result = Translator.getTranslator().toMorse(textString);
                break;
            } 
                
        return result;
    }
    
}
