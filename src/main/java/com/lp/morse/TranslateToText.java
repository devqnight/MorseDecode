package com.lp.morse;


import com.lp.exceptions.MorseBusinessException;

// Translates a Morse code to a String text

public class TranslateToText {
    
    /** 
     * Translates a morse text to a String text using a binary tree
     * @param tree : binary tree of the morse code
     * @param morse : morse text
     * @return String : translation
     * @throws MorseBusinessException
     */
    public static String toText(MorseNode tree,String morse) throws MorseBusinessException{
        String text ="";

        String[] words = morse.split(" ");

        for(String word : words){
            String[] res = newLineChar(word, text);
            word = res[0];
            text = res [1];
            if(word.equals("/")){
                text += " ";
            } else {
                text += toChar(tree, word, 0);
            }
        }
        return text.trim();
    }

    
    /** 
     * Adds next line characters to the final text and removes them from the word to translate.
     * Loops to get rid of \n in word and adds them to text
     * @param word : word to translate
     * @param text : final translation
     * @return String[] : { word : word to translate, text : final translation}
     */
    private static String[] newLineChar(String word, String text){
        while(word.charAt(0)=='\n'){
            text += "\n";
            word = word.substring(1, word.length());
        }
        String[] res =  {word, text};
        return res;
    }

    
    /** 
     * Recursive function :
     * returns the char corresponding to the morse code passed in word
     * @param tree : morse binary tree
     * @param word : morse code
     * @param index : start index
     * @return String : letter from the morse code
     * @throws MorseBusinessException : if the code doens't exist in the morse tree
     */
    private static String toChar(MorseNode tree, String word, int index) throws MorseBusinessException{
        if(index == word.length()){
            return tree.getLetter();
        } else if(word.charAt(index) == '.'){
            if(tree.getLeft() == null) throw new MorseBusinessException(word);
            return toChar(tree.getLeft(), word, index+1);
        } else if(word.charAt(index) == '-'){
            if(tree.getRight() == null) throw new MorseBusinessException(word);
            return toChar(tree.getRight(), word, index+1);
        } else {
            throw new MorseBusinessException(String.valueOf(word.charAt(index)));
        }
    }
}
