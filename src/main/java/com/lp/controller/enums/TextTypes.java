package com.lp.controller.enums;

public enum TextTypes {
    ERROR_T ("red"),
    TEXT_T ("black"),
    SUCCESS_T ("green")
    ;
    private final String textTypeColor;
    
    private TextTypes(String color){
        this.textTypeColor = color;
    }

    public String getColor(){
        return this.textTypeColor;
    }
}
