package com.lp.morse;

import com.lp.tools.Code;

/**
 * Node of Morse Tree.
 * contains a morse code, 
 * plus next node on right and next node on left
 */
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
     * sets left node
     * @param node
     */
    public void setLeft(MorseNode node){
        this.left = node;
    }

    
    /** 
     * sets right node
     * @param node
     */
    public void setRight(MorseNode node){
        this.right = node;
    }

    
    /** 
     * returns left node
     * @return MorseNode
     */
    public MorseNode getLeft(){
        return this.left;
    }

    
    /** 
     * returns right node
     * @return MorseNode
     */
    public MorseNode getRight(){
        return this.right;
    }

    
    /** 
     * sets the letter of the morse code of the node
     * @param letter
     */
    public void setLetter(String letter){
        this.morseCode.setLetter(letter);
    }

    
    /** 
     * sets the code of the morse code of the node
     * @param code
     */
    public void setCode(String code){
        this.morseCode.setCode(code);
    }

    
    /** 
     * sets the morse code of the node with the provided code and letter 
     * @param code : morse code
     * @param letter : letter for the morse code
     */
    public void setMorseCode(String code, String letter){
        this.morseCode = new Code(code, letter);
    }

    
    /** 
     * sets the morse code of the node with a code object already created
     * @param code : morse code object
     */
    public void setMorseCode(Code code){
        this.morseCode = code;
    }

    
    /** 
     * returns the morse code object of the node
     * @return Code
     */
    public Code getMorseCode(){
        return this.morseCode;
    }

    
    /** 
     * returns the letter of the morse code of the node
     * @return String
     */
    public String getLetter(){
        return this.morseCode.getLetter();
    }

    
    /** 
     * returns the code of the morse code of the node
     * @return String
     */
    public String getCode(){
        return this.morseCode.getCode();
    }

    
    /** 
     * returns the morse code of the node as a String
     * @return String
     */
    @Override
    public String toString(){
        return this.getMorseCode().toString();
    }

    /**
     * displays the whole morse tree starting from this node
     */
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
