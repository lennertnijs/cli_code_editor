package com.vip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ListText implements IText {


    private final List<StringBuilder> builders;
    private final Pattern regex = Pattern.compile("\\r?\\n|\\r");

    public ListText(String s){
        Objects.requireNonNull(s, "String is null.");
        this.builders = new ArrayList<>();
        String[] lines = regex.split(s);
        for(String str : lines){
            this.builders.add(new StringBuilder(str));
        }
    }

    public ListText(char[] characters){
        this(new String(characters));
    }

    @Override
    public boolean isEmpty() {
        return builders.size() == 1 && builders.get(0).length() == 0;
    }

    @Override
    public int getLength() {
        int count = 0;
        for(StringBuilder builder : builders){
            count += builder.length() + 1;
        }
        if(count != 0){
            count -= 1; // last line doesn't have a line break
        }
        return 0;
    }

    @Override
    public char[][] getContent() {
        return new char[0][];
    }

    @Override
    public String getContentAsString(Linebreak linebreak) {
        StringBuilder concatBuilder = new StringBuilder();
        for(StringBuilder builder : builders){
            concatBuilder.append(builder);
            concatBuilder.append(linebreak.getValue());
        }
        concatBuilder.deleteCharAt(concatBuilder.length() - 1);
        return concatBuilder.toString();
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
