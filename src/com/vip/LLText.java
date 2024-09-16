package com.vip;

import java.util.Objects;
import java.util.regex.Pattern;

public final class LLText implements IText{

    private LinkedList[] lls;
    private static final Pattern regex = Pattern.compile("\\r?\\n|\\r");

    public LLText(){

    }

    public LLText(String text){
        Objects.requireNonNull(text, "String is null.");
        String[] lines = regex.split(text);
        this.lls = new LinkedList[lines.length];
        for(int i = 0; i < lines.length; i++){
            LinkedList list = new LinkedList();
            for(char c : lines[i].toCharArray()){
                Node node = new Node(c);
                list.add(node);
            }
            lls[i] = list;
        }
    }

    @Override
    public boolean isEmpty() {
        return lls.length == 1 && lls[0].isEmpty();
    }

    @Override
    public int getLength() {
        int count = 0;
        for(LinkedList list : lls){
            count += list.length() + 1;
        }
        count = Math.max(count - 1, 0);
        return count;
    }

    @Override
    public char[][] getContent() {
        return new char[0][];
    }

    @Override
    public int getLineCount() {
        return 0;
    }

    @Override
    public int getLineLength(int row) {
        return 0;
    }

    @Override
    public char[] getLine(int row) {
        return new char[0];
    }


    @Override
    public char[] getLineBetween(int row, int start, int end) {
        return new char[0];
    }

    @Override
    public void insertCharacter(char character, int row, int column) {
        if(row < 0 || row >= lls.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(column < 0 || column > lls[row].length()){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        lls[row].insertAtIndex(column, character);
    }

    @Override
    public char removeCharacter(int row, int column) {
        return 0;
    }

    @Override
    public int countOccurrences(char[] sequence) {
        return 0;
    }
}
