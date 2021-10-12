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

    
    /** 
     * @param node
     */
    public void setLeft(MorseNode node){
        this.left = node;
    }

    
    /** 
     * @param node
     */
    public void setRight(MorseNode node){
        this.right = node;
    }

    
    /** 
     * @return MorseNode
     */
    public MorseNode getLeft(){
        return this.left;
    }

    
    /** 
     * @return MorseNode
     */
    public MorseNode getRight(){
        return this.right;
    }

    
    /** 
     * @param letter
     */
    public void setLetter(String letter){
        this.morseCode.setLetter(letter);
    }

    
    /** 
     * @param code
     */
    public void setCode(String code){
        this.morseCode.setCode(code);
    }

    
    /** 
     * @param code
     * @param letter
     */
    public void setMorseCode(String code, String letter){
        this.morseCode = new Code(code, letter);
    }

    
    /** 
     * @param code
     */
    public void setMorseCode(Code code){
        this.morseCode = code;
    }

    
    /** 
     * @return Code
     */
    public Code getMorseCode(){
        return this.morseCode;
    }

    
    /** 
     * @return String
     */
    public String getLetter(){
        return this.morseCode.getLetter();
    }

    
    /** 
     * @return String
     */
    public String getCode(){
        return this.morseCode.getCode();
    }

    
    /** 
     * @return String
     */
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
