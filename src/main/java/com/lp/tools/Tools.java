package com.lp.tools;

import java.io.IOException;

public class Tools {
    private static Tools instance;

    public static Tools getTools(){
        if(instance == null){
            instance = new Tools();
        }
        return instance;
    }

    public String translateWhich(String input) throws IOException{
        if(!isText(input)){
            return "String"; // translate a string
        } else {
            return "Morse"; // translate a morse code
        }
    }

    private boolean isText(String input) throws IOException{
        return Reader.getCodes().hasLetter(input);
    }

    public boolean isMorse(String input){
        return input.matches("[-\\.]+");
    }
}
