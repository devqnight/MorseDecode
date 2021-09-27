package com.lp.tools;

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

    public String isIn(String searched){
        if(searched.equals(this.code.getLetter())){
            return this.code.getCode();
        } else {
            if(isEmpty(this.next)){
                return ""; //possibly throw an exception here
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

