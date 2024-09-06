package com.vip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ListText implements IText {


    private final List<StringBuilder> builders;
    private static final Pattern regex = Pattern.compile("\\r?\\n|\\r");

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

    public ListText(){
        this.builders = new ArrayList<>();
        builders.add(new StringBuilder());
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
        count = Math.max(count-1, 0);
        return count;
    }

    @Override
    public char[][] getContent() {
        char[][] matrix = new char[builders.size()][];
        for(int i = 0; i < builders.size(); i++){
            StringBuilder builder = builders.get(i);
            char[] line = new char[builder.length()];
            for(int j = 0; j < builder.length(); j++){
                line[j] = builder.charAt(j);
            }
            matrix[i] = line;
        }
        return matrix;
    }

    @Override
    public String getContentAsString(Linebreak linebreak) {
        Objects.requireNonNull(linebreak, "Line break is null.");
        StringBuilder concatBuilder = new StringBuilder();
        for(int i = 0; i < builders.size(); i++){
            concatBuilder.append(builders.get(i));
            if(i != builders.size() - 1){
                concatBuilder.append(linebreak.getValue());
            }
        }
        return concatBuilder.toString();
    }

    @Override
    public int getLineCount() {
        return builders.size();
    }

    @Override
    public int getLineLength(int row) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        return builders.get(row).length();
    }

    @Override
    public char[] getLine(int row) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        return builders.get(row).toString().toCharArray();
    }

    @Override
    public String getLineAsString(int row) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        return builders.get(row).toString();
    }

    @Override
    public char[] getLineBetween(int row, int start, int end) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        int lineLength = builders.get(row).length();
        if(start < 0 || start >= lineLength){
            throw new IndexOutOfBoundsException("Start index is out of bounds.");
        }
        if(end < 0 || end >= lineLength){
            throw new IndexOutOfBoundsException("End index is out of bounds.");
        }
        if(start >= end){
            throw new IllegalArgumentException("Start index is larger than end index.");
        }
        char[] line = builders.get(row).toString().toCharArray();
        char[] subLine = new char[end - start];
        System.arraycopy(line, start, subLine, 0, end - start);
        return subLine;
    }

    @Override
    public void insertCharacter(char character, int row, int column) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(column < 0 || column > builders.get(row).length()){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        builders.get(row).insert(column, character);
    }

    @Override
    public char removeCharacter(int row, int column) {
        if(row < 0 || row >= builders.size()){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(column < 0 || column > builders.get(row).length()){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        if(column != builders.get(row).length()){
            char c = builders.get(row).charAt(column);
            builders.get(row).deleteCharAt(column);
            return c;
        }
        if(row == builders.size() - 1){
            throw new IndexOutOfBoundsException("Cannot remove a line break from the final line.");
        }
        StringBuilder concatBuilder = new StringBuilder();
        concatBuilder.append(builders.get(row));
        concatBuilder.append(builders.get(row + 1));
        builders.remove(row + 1);
        builders.remove(row);
        builders.add(row, concatBuilder);
        return '\n';
    }

    @Override
    public int countOccurrences(char[] sequence) {
        Objects.requireNonNull(sequence, "Character sequence is null.");
        if(sequence.length > getLength()){
            return 0;
        }
        int occurrenceCount = 0;
        int sequenceIndex = 0;
        for(int i = 0; i < builders.size(); i++){
            StringBuilder builder = builders.get(i);
            for(int j = 0; j < builder.length(); j++){
                if(builder.charAt(j) == sequence[sequenceIndex] && sequenceIndex == sequence.length - 1){
                    sequenceIndex = 0;
                    occurrenceCount += 1;
                    continue;
                }
                if(builder.charAt(j) == sequence[sequenceIndex]){
                    sequenceIndex++;
                    continue;
                }
                i = Math.min(i + sequenceIndex, builders.size() - 1);
                sequenceIndex = 0;
            }
        }
        return occurrenceCount;
    }
}
