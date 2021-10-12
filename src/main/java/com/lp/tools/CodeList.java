package com.lp.tools;

import com.lp.exceptions.MorseBusinessException;

public class CodeList {
    private Code code;
    private CodeList next;
    
    public CodeList(Code c, CodeList cl){
        this.code = c;
        this.next = cl;
    }

    
    /** 
     * @return Code
     */
    public Code getCode() {
        return code;
    }

    
    /** 
     * @return CodeList
     */
    public CodeList getNext() {
        return next;
    }

    
    /** 
     * @param code
     */
    public void addCode(Code code){
        if(this.getNext() == null){
            this.setNext(new CodeList(code, null));
        } else {
            this.getNext().addCode(code);
        }
    }

    
    /** 
     * @param c
     * @param l
     */
    public void addCode(String c, String l){
        if(this.getNext() == null){
            this.setNext(new CodeList(new Code(l, c),null));
        } else {
            this.getNext().addCode(c,l);
        }
    }

    
    /** 
     * @param el
     * @throws MorseBusinessException
     */
    public void deleteCode(String el) throws MorseBusinessException{
        if(this.getNext() == null){
            throw new MorseBusinessException("element "+el+" does not exist in the code list...");
        }
        if(this.getNext().getCode().getCode().equals(el) || this.getNext().getCode().getLetter().equals(el)){
            this.setNext(this.getNext().getNext());
            return;
        } else {
            this.getNext().deleteCode(el);
        }
    }

    
    /** 
     * @param searched
     * @return String
     */
    public String isIn(String searched){
        if(searched.equals(this.code.getLetter())){
            return this.code.getCode();
        } else {
            if(isEmpty(this.next)){
                return "(/)"; //possibly throw an exception here
            }
            return this.next.isIn(searched);
        }
        
    }

    
    /** 
     * @param code
     * @return String
     */
    public String deCode(String code){
        if(code.equals(this.code.getCode())){
            return this.code.getLetter();
        } else {
            if(isEmpty(this.next)){
                return ""; //possibly throw an exception here
            }
            return this.next.deCode(code);
        }
    }

    
    /** 
     * @param l
     * @return boolean
     */
    private boolean isEmpty(CodeList l){
        return l == null;
    }

    
    /** 
     * @param cl
     */
    public void setNext(CodeList cl){
        this.next = cl;
    }

    
    /** 
     * @param testString
     * @return boolean
     */
    public boolean hasLetter(String testString){
        if((testString.toUpperCase()).contains(this.code.getLetter())){
            return true;
        }
        if(this.next != null){
            return this.next.hasLetter(testString);
        } else {
            return false;
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return " "
                +this.code.getLetter()
                +" => "
                +this.code.getCode()
                +"  \n" 
                + (isEmpty(this.getNext()) ? "" : this.getNext());
    }
}

