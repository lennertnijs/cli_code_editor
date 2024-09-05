package com.vip;

public final class LineText implements IText{


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public char[][] getContent() {
        return new char[0][];
    }

    @Override
    public String getContentAsString(Linebreak linebreak) {
        return null;
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
    public String getLineAsString(int row) {
        return null;
    }

    @Override
    public char[] getLineBetween(int row, int start, int end) {
        return new char[0];
    }

    @Override
    public void insertCharacter(char character, int row, int column) {

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
