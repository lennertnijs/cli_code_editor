package com.vip;

import java.util.regex.Pattern;

public final class CharacterText implements IText{


    private char[][] matrix;
    private final Pattern regex = Pattern.compile("\\r?\\n|\\r");

    public CharacterText(){
        this.matrix = new char[0][0];
    }

    public CharacterText(String text){

    }

    private boolean isLineBreak(char c, char d){
        return (c == '\r' && d == '\n') || isLineBreak(c);
    }

    private boolean isLineBreak(char c){
        return c == '\n' || c == '\r';
    }

    public int getLength(){
        return 0;
    }

    public char[][] getContent(){
        return null;
    }

    public String getContentAsString(Linebreak linebreak){
        return null;
    }

    public int getLineCount(){
        return 0;
    }

    public int getLineLength(int index){
        return 0;
    }

    public char[] getLine(int index){
        return null;
    }

    public String getLineAsString(int index){
        return null;
    }

    public char[] getLineBetween(int index, int start, int end){
        return null;
    }

    public void insertCharacter(char c, int row, int col){
        return;
    }

    public char removeCharacter(int row, int col){
        return '\0';
    }

    public boolean isEmpty(){
        return false;
    }

    public int countOccurrences(char[] sequence){
        return 0;
    }
}
