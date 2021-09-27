package com.lp.morse;


import com.lp.exceptions.MorseBusinessException;

public class TranslateToText {
    public static String toText(MorseNode tree,String morse) throws MorseBusinessException{
        String text ="";

        String[] words = morse.split(" ");

        for(String word : words){
            if(word.charAt(0) == '\n'){
                text += "\n";
                word = word.substring(1,word.length()-1);
            }
            if(word.equals("/")){
                text += " ";
            } else {
                text += toChar(tree, word, 0);
            }
        }
        return text.trim();
    }

    private static String toChar(MorseNode tree, String word, int index) throws MorseBusinessException{
        if(index == word.length()){
            return tree.getLetter();
        } else if(word.charAt(index) == '.'){
            try {
                return toChar(tree.getLeft(), word, index+1);
            } catch (Exception e) {
                throw new MorseBusinessException("\n"+e + "error : " +" left :"+tree+" , word :"+word+" , index : "+index);
            }
        } else if(word.charAt(index) == '-'){
            return toChar(tree.getRight(), word, index+1);
        } else {
            throw new MorseBusinessException("Character " + word.charAt(index) + " does not exist in the morse code...");
        }
    }
}
