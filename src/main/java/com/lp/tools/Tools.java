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
     * @param input
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
     * @param input
     * @return boolean
     * @throws IOException
     */
    private boolean isText(String input) throws IOException{
        return Reader.getCodes().hasLetter(input);
    }

    
    /** 
     * @param input
     * @return boolean
     */
    public boolean isMorse(String input){
        return input.matches("[-\\.]+");
    }
}
