package com.vip;

public interface IText {

    /**
     * Checks whether the given {@link IText} is empty.
     * An empty text contains no characters (including no linebreaks).
     *
     * @return True if empty. False otherwise.
     */
    boolean isEmpty();

    /**
     * Fetches and returns the length of the entire {@link IText}.
     * Includes line breaks (as ONE character).
     *
     * @return The length of the text.
     */
    int getLength();

    /**
     * Fetches and returns the entire {@link IText} in a matrix form of characters.
     * The matrix form does NOT include line breaks, as these are represented by the matrix itself.
     *
     * @return The entire text's contents as a matrix of characters.
     */
    char[][] getContent();

    /**
     * Fetches and returns the entire {@link IText} as a single string, using the given line break.
     * @param linebreak The line break. Cannot be null.
     *
     * @return The entire text's contents as a single string.
     * @throws NullPointerException If the linebreak is null.
     */
    String getContentAsString(Linebreak linebreak);

    /**
     * Fetches and returns the amount of lines in the {@link IText}.
     *
     * @return The line count.
     */
    int getLineCount();

    /**
     * Fetches and returns the length of the line at the given row index.
     * Does NOT include any line breaks.
     * @param row The row index. 0 <= row < lineCount
     *
     * @return The line's length.
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     */
    int getLineLength(int row);

    /**
     * Fetches and returns the line at the given row index as an array of characters.
     * Does NOT include any line breaks.
     * @param row The row index. 0 <= row < lineCount
     *
     * @return The line's contents as an array of characters.
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     */
    char[] getLine(int row);

    /**
     * Fetches and returns the line at the given row index as a string.
     * Does NOT include any line breaks.
     * @param row The row index. 0 <= row < lineCount
     *
     * @return The line's contents as a single string.
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     */
    String getLineAsString(int row);

    /**
     * Fetches and returns the line's contents from the given row between the start (inclusive) and end (exclusive)
     * indices, as an array of characters.
     * Does NOT include any line breaks.
     * @param row The row index. 0 <= row < lineCount
     * @param start The starting index. 0 <= start < lineLength
     * @param end The ending index. 0 <= end < lineLength AND end >= start
     *
     * @return The line's selected contents, as an array of characters.
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     * @throws IndexOutOfBoundsException If start < 0 or start >= lineLength
     * @throws IndexOutOfBoundsException If end < 0 or end >= lineLength
     * @throws IllegalArgumentException If end < start
     */
    char[] getLineBetween(int row, int start, int end);

    /**
     * Inserts the given character into the text at the location with the given indices.
     * @param character The character.
     * @param row The row index. 0 <= row < lineCount
     * @param column The column index. 0 <= column <= lineLength (CAN insert at end of line)
     *
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     * @throws IndexOutOfBoundsException If column < 0 or column > lineLength
     */
    void insertCharacter(char character, int row, int column);

    /**
     * Removes the character at the given indices from the text, and returns it.
     * @param row The row index. 0 <= row < lineCount
     * @param column The column index. 0 <= column < lineLength
     *
     * @return The removed character.
     * @throws IndexOutOfBoundsException If row < 0 or row >= lineCount
     * @throws IndexOutOfBoundsException If column < 0 or column >= lineLength
     */
    char removeCharacter(int row, int column);

    /**
     * Counts the amount of occurrences found of the given array of characters in the {@link IText}.
     * @param sequence The array of characters to look for. Cannot be null.
     *
     * @return The amount of matching occurrences.
     * @throws NullPointerException If the given array is null.
     */
    int countOccurrences(char[] sequence);
}
