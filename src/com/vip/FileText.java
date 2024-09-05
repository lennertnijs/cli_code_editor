package com.vip;

public final class FileText {

    public FileText(){

    }

    public FileText(String text){

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
}
