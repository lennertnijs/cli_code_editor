package com.vip;

import java.util.Objects;
import java.util.regex.Pattern;

public final class CharacterText implements IText{


    private char[][] matrix;
    private final Pattern regex = Pattern.compile("\\r?\\n|\\r");
    private int length;

    public CharacterText(){
        this.matrix = new char[1][0];
    }

    public CharacterText(String text){
        String[] lines = regex.split(text);
        this.length = 0;
        this.matrix = new char[lines.length][];
        for(int i = 0; i < lines.length; i++){
            this.matrix[i] = new char[lines[i].length()];
            for(int j = 0; j < lines[i].length(); j++){
                this.matrix[i][j] = lines[i].charAt(j);
            }
            this.length += lines[i].length() + 1;
        }
        this.length = Math.max(this.length - 1, 0);
    }

    public boolean isEmpty(){
        return length == 0;
    }


    public int getLength(){
        return length;
    }

    public char[][] getContent(){
        return matrix;
    }

    public int getLineCount(){
        return matrix.length;
    }

    public int getLineLength(int row){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        return matrix[row].length;
    }

    public char[] getLine(int row){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        return matrix[row];
    }

    public char[] getLineBetween(int row, int start, int end){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        int lineLength = matrix[row].length;
        if(start < 0 || start >= lineLength){
            throw new IndexOutOfBoundsException("Start index is out of bounds.");
        }
        if(end < 0 || end >= lineLength){
            throw new IndexOutOfBoundsException("End index is out of bounds.");
        }
        if(start >= end){
            throw new IllegalArgumentException("Start index is larger than end index.");
        }
        char[] line = matrix[row];
        char[] subLine = new char[end - start];
        System.arraycopy(line, start, subLine, 0, end - start);
        return subLine;
    }

    public void insertCharacter(char c, int row, int col){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(col < 0 || col > matrix[row].length){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        char[] oldLine = matrix[row];
        char[] newLine = new char[oldLine.length + 1];
        for(int i = 0; i < oldLine.length; i++){
            if(i < col){
                newLine[i] = oldLine[i];
            }else{
                newLine[i+1] = oldLine[i];
            }
        }
        newLine[col] = c;
        this.matrix[row] = newLine;
        this.length++;
    }

    public char removeCharacter(int row, int col){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(col < 0 || col > matrix[row].length){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        if(col != matrix[row].length){
            char c = matrix[row][col];
            char[] oldLine = matrix[row];
            int newLineLength = Math.max(oldLine.length - 1, 0);
            char[] newLine = new char[newLineLength];
            for(int i = 0; i < newLineLength; i++){
                if(i < col){
                    newLine[i] = oldLine[i];
                }else{
                    newLine[i] = oldLine[i + 1];
                }
            }
            this.matrix[row] = newLine;
            this.length--;
            return c;
        }
        if(row == matrix.length - 1){
            throw new IndexOutOfBoundsException("Cannot remove a line break from the final line.");
        }
        char[][] newMatrix = new char[matrix.length - 1][];
        for(int i = 0; i < matrix.length; i++){
            char[] line = matrix[i];
            if(i != row){
                newMatrix[i] = line;
                continue;
            }
            int length = line.length + matrix[row + 1].length;
            char[] newLine = new char[length];
            System.arraycopy(line, 0, newLine, 0, line.length);
            System.arraycopy(matrix[row + 1], 0, newLine, line.length, length - line.length);
            newMatrix[i] = newLine;
            i++;
        }
        this.length--;
        this.matrix = newMatrix;
        return '\n';
    }
    public int countOccurrences(char[] sequence){
        Objects.requireNonNull(sequence, "Character sequence is null.");
        if(sequence.length > getLength()){
            return 0;
        }
        int occurrenceCount = 0;
        int sequenceIndex = 0;
        for(int i = 0; i < matrix.length; i++){
            char[] line = matrix[i];
            for (char c : line) {
                if (c == sequence[sequenceIndex] && sequenceIndex == sequence.length - 1) {
                    sequenceIndex = 0;
                    occurrenceCount += 1;
                    continue;
                }
                if (c == sequence[sequenceIndex]) {
                    sequenceIndex++;
                    continue;
                }
                i = Math.min(i + sequenceIndex, matrix.length - 1);
                sequenceIndex = 0;
            }
        }
        return occurrenceCount;
    }
}
