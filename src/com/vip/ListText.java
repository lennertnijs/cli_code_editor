package com.vip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ListText implements IText {


    private final List<StringBuilder> builders;
    private int length = 0;
    private static final Pattern regex = Pattern.compile("\\r?\\n|\\r");

    public ListText(String s){
        Objects.requireNonNull(s, "String is null.");
        this.builders = new ArrayList<>();
        String[] lines = regex.split(s);
        for(String str : lines){
            this.builders.add(new StringBuilder(str));
            this.length += str.length() + 1;
        }
        this.length = Math.max(length - 1, 0);
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
        return length == 0;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public char[][] getContent() {
        char[][] matrix = new char[builders.size()][];
        for(int i = 0; i < builders.size(); i++){
            matrix[i] =  builders.get(i).substring(0, builders.get(i).length()).toCharArray();
        }
        return matrix;
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
        if(character == '\n' || character == '\r'){
            StringBuilder before = new StringBuilder();
            for(int i = 0; i < column; i++){
                before.append(builders.get(row).charAt(i));
            }
            StringBuilder after = new StringBuilder();
            for(int i = column; i < builders.get(row).length(); i++){
                after.append(builders.get(row).charAt(i));
            }
            builders.remove(row);
            builders.add(row, before);
            builders.add(row + 1, after);
            this.length++;
            return;
        }
        builders.get(row).insert(column, character);
        this.length++;
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
            this.length--;
            return c;
        }
        if(row == builders.size() - 1 && column == builders.get(builders.size() - 1).length()){
            throw new IndexOutOfBoundsException("Cannot remove a line break from the final line.");
        }
        StringBuilder concatBuilder = new StringBuilder();
        concatBuilder.append(builders.get(row));
        concatBuilder.append(builders.get(row + 1));
        builders.remove(row + 1);
        builders.remove(row);
        builders.add(row, concatBuilder);
        this.length--;
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
