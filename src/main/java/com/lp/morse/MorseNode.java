package com.lp.morse;

import com.lp.tools.Code;

public class MorseNode {
    
    private Code morseCode;
    private MorseNode left;
    private MorseNode right;

    public MorseNode(){
        this.left = null;
        this.right = null;
        this.morseCode = new Code("", "");
    }

    public void setLeft(MorseNode node){
        this.left = node;
    }

    public void setRight(MorseNode node){
        this.right = node;
    }

    public MorseNode getLeft(){
        return this.left;
    }

    public MorseNode getRight(){
        return this.right;
    }

    public void setLetter(String letter){
        this.morseCode.setLetter(letter);
    }

    public void setCode(String code){
        this.morseCode.setCode(code);
    }

    public void setMorseCode(String code, String letter){
        this.morseCode = new Code(code, letter);
    }

    public void setMorseCode(Code code){
        this.morseCode = code;
    }

    public Code getMorseCode(){
        return this.morseCode;
    }

    public String getLetter(){
        return this.morseCode.getLetter();
    }

    public String getCode(){
        return this.morseCode.getCode();
    }

    @Override
    public String toString(){
        return this.getMorseCode().toString();
    }

    public void displayTree(){
        System.out.println("display : "+this.morseCode);
        if(this.left != null){
            this.left.displayTree();
        }
        if(this.right != null){
            this.right.displayTree();
        }
    }
}
