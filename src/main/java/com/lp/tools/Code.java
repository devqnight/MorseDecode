package com.lp.tools;

public class Code {
    private String code;
    private String letter;

    public Code(String l, String c){
        this.letter = l;
        this.code = c;
    }

    
    /** 
     * @return String
     */
    public String getCode() {
        return code;
    }

    
    /** 
     * @return String
     */
    public String getLetter() {
        return letter;
    }

    
    /** 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    
    /** 
     * @param letter
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return this.getLetter() + " => " + this.getCode();
    }

}
