package com.lp.tools;

import java.io.IOException;

public class Tools {
    private static Tools instance;

    
    /** 
     * @return Tools
     */
    public static Tools getTools(){
        if(instance == null){
            instance = new Tools();
        }
        return instance;
    }

    
    /** 
     * Translates a string or a morse code
     * @param input String
     * @return String
     * @throws IOException
     */
    public String translateWhich(String input) throws IOException{
        if(!isText(input)){
            return "String"; // translate a string
        } else {
            return "Morse"; // translate a morse code
        }
    }

    
    /** 
     * Tests if the input is a text or not
     * @param input String
     * @return boolean
     * @throws IOException
     */
    private boolean isText(String input) throws IOException{
        return Reader.getCodes().hasLetter(input);
    }

    
    /** 
     * Tests if the input is a morse code or not
     * @param input String
     * @return boolean
     */
    public boolean isMorse(String input){
        return input.matches("[-\\.]+");
    }
}
