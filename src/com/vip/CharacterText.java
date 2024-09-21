package com.vip;

import java.util.Objects;
import java.util.regex.Pattern;

public final class CharacterText implements IText{


    private char[][] matrix;
    private final static Pattern regex = Pattern.compile("\\r?\\n|\\r");
    private int length;
    private int lastRow = -1;
    private int lastCol = -1;
    private int lastColNext = -1;

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
        if(row != lastRow){
            return matrix[row].length;
        }
        int count = 0;
        for(char c : matrix[row]){
            if(c != '\u0000'){
                count++;
            }
        }
        return count;
    }

    public char[] getLine(int row){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(row != lastRow){
            return matrix[row];
        }
        char[] subArray = new char[getLineLength(row)];
        int i = 0;
        for(char c : matrix[row]){
            if(c != '\u0000'){
                subArray[i++] = c;
            }
        }
        return subArray;
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
        if(row != lastRow && lastRow >= 0){
            cleanUpLineBuffer();
        }
        if(c == '\n' || c == '\r'){
            insertNewLine(row, col);
            this.length++;
            return;
        }
        if(lastRow == row && lastCol == col - 1){
            if (matrix[row][col] != '\u0000') {
                addBufferSpaceToRow(row, col);
            }
            matrix[row][col] = c;
            this.length++;
            this.lastCol++;
            return;
        }
        addBufferSpaceToRow(row, col);
        matrix[row][col] = c;
        this.length++;
        this.lastRow = row;
        this.lastCol = col;
    }

    private void insertNewLine(int row, int col){
        char[][] newMatrix = new char[matrix.length + 1][];
        for(int i = 0; i < matrix.length; i++){
            if(i < row){
                newMatrix[i] = matrix[i];
                continue;
            }
            if(i == row){
                newMatrix[i] = new char[col];
                newMatrix[i + 1] = new char[matrix[i].length - col];
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, col);
                System.arraycopy(matrix[i], col, newMatrix[i + 1], 0, matrix[i].length - col);
                continue;
            }
            newMatrix[i + 1] = matrix[i];
        }
        this.matrix = newMatrix;
    }

    private void addBufferSpaceToRow(int row, int col){
        char[] oldLine = matrix[row];
        char[] newLine = new char[oldLine.length + 128]; // GETS MASSIVELY FASTER FOR LARGE LINES OF TEXT!
        System.arraycopy(oldLine, 0, newLine, 0, col); // works fine
        int current = col;
        for(int i = newLine.length - (oldLine.length - col); i < newLine.length; i++){
            newLine[i] = oldLine[current++];
        }
        this.matrix[row] = newLine;
    }

    public char removeCharacter(int row, int col){
        if(row < 0 || row >= matrix.length){
            throw new IndexOutOfBoundsException("Row index is out of bounds.");
        }
        if(col < 0 || col > matrix[row].length){
            throw new IndexOutOfBoundsException("Column index is out of bounds.");
        }
        if(row != lastRow && lastRow >= 0){
            cleanUpLineBuffer();
        }
        boolean newLineRemoved = col == matrix[row].length;
        if(newLineRemoved){
            removeNewLine(row);
            return '\n';
        }
        if(row == lastRow){
            if(col == lastCol - 1){
                char c = matrix[row][col];
                matrix[row][col] = '\u0000';
                this.length--;
                this.lastCol--;
                return c;
            }
            if(col == lastCol){
                char c = matrix[row][lastColNext];
                matrix[row][lastColNext] = '\u0000';
                this.length--;
                this.lastColNext++;
                return c;
            }
        }
        // reset buffer!
        char c = matrix[row][col];
        matrix[row][col] = '\u0000';
        this.length--;
        this.lastRow = row;
        this.lastCol = col;
        this.lastColNext = col + 1;
        return c;
    }

    private void cleanUpLineBuffer(){
        matrix[lastRow] = getLine(lastRow);
    }

    private void removeNewLine(int row){
        boolean lastRow = row == matrix.length - 1;
        if(lastRow){
            throw new IndexOutOfBoundsException("Cannot remove a line break from the final line.");
        }
        char[][] newMatrix = new char[matrix.length - 1][];
        for(int i = 0; i < matrix.length; i++){
            char[] line = matrix[i];
            if(i < row){
                newMatrix[i] = line;
                continue;
            }
            if(i == row){
                int length = line.length + matrix[row + 1].length;
                char[] newLine = new char[length];
                System.arraycopy(line, 0, newLine, 0, line.length);
                System.arraycopy(matrix[row + 1], 0, newLine, line.length, length - line.length);
                newMatrix[i] = newLine;
                i++;
                continue;
            }
            newMatrix[i - 1] = matrix[i];
        }
        this.length--;
        this.matrix = newMatrix;
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
