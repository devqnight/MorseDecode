package com.lp.tools;

public class Tools {
    private static Tools instance;

    public static Tools getTools(){
        if(instance == null){
            instance = new Tools();
        }
        return instance;
    }

    public String translateWhich(String input){
        if(!isText(input)){
            return "String";
        } else {
            return "Morse";
        }
    }

    private boolean isText(String input){
        return !(input.contains(".") || input.contains("-")) && input.contains("a") || input.contains("A")
             ||input.contains("b") || input.contains("B")
             ||input.contains("c") || input.contains("C")
             ||input.contains("d") || input.contains("D")
             ||input.contains("e") || input.contains("E")
             ||input.contains("f") || input.contains("F")
             ||input.contains("g") || input.contains("G")
             ||input.contains("h") || input.contains("H")
             ||input.contains("i") || input.contains("I")
             ||input.contains("j") || input.contains("J")
             ||input.contains("k") || input.contains("K")
             ||input.contains("l") || input.contains("L")
             ||input.contains("m") || input.contains("M")
             ||input.contains("n") || input.contains("N")
             ||input.contains("o") || input.contains("O")
             ||input.contains("p") || input.contains("P")
             ||input.contains("q") || input.contains("Q")
             ||input.contains("r") || input.contains("R")
             ||input.contains("s") || input.contains("S")
             ||input.contains("t") || input.contains("T")
             ||input.contains("u") || input.contains("U")
             ||input.contains("v") || input.contains("V")
             ||input.contains("w") || input.contains("W")
             ||input.contains("x") || input.contains("X")
             ||input.contains("y") || input.contains("Y")
             ||input.contains("z") || input.contains("Z");
    }

    public boolean isMorse(String input){
        return (input.contains(".") || input.contains("-")) && (!isText(input));
    }
}
