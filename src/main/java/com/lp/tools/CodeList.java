package com.lp.tools;

import com.lp.exceptions.MorseBusinessException;

public class CodeList {
    private Code code;
    private CodeList next;
    
    public CodeList(Code c, CodeList cl){
        this.code = c;
        this.next = cl;
    }

    public Code getCode() {
        return code;
    }

    public CodeList getNext() {
        return next;
    }

    public void addCode(Code code){
        if(this.getNext() == null){
            this.setNext(new CodeList(code, null));
        } else {
            this.getNext().addCode(code);
        }
    }

    public void addCode(String c, String l){
        if(this.getNext() == null){
            this.setNext(new CodeList(new Code(l, c),null));
        } else {
            this.getNext().addCode(c,l);
        }
    }

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

    private boolean isEmpty(CodeList l){
        return l == null;
    }

    public void setNext(CodeList cl){
        this.next = cl;
    }

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

    @Override
    public String toString(){
        return "| "
                +this.code.getLetter()
                +" | "
                +this.code.getCode()
                +"  \n" 
                + (isEmpty(this.getNext()) ? "" : this.getNext());
    }
}

