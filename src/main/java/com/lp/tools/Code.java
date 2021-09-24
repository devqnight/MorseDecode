package com.lp.tools;

public class Code {
    private String code;
    private String letter;

    public Code(String c, String l){
        this.letter = c;
        this.code = l;
    }

    public String getCode() {
        return code;
    }

    public String getLetter() {
        return letter;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString(){
        return this.getCode() + " => " + this.getLetter();
    }

}
