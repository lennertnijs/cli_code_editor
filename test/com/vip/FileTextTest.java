package com.vip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class FileTextTest {

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class,
                () -> new FileText(null));
    }

    @Test
    public void testGetLength(){
        FileText emptyText = new FileText();
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLength());
        assertEquals(34, fileText.getLength());
    }

    @Test
    public void textGetContent(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[][] content = {
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'},
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'}
        };
        assertEquals(content, fileText.getContent());
    }

    @Test
    public void testGetContentAsString(){
        String dummyText = "This is a dummy text\nNo other use.";
        FileText fileText = new FileText(dummyText);
        assertEquals(dummyText, fileText.getContentAsString(Linebreak.LF));
    }

    @Test
    public void testGetContentAsStringWithNull(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(NullPointerException.class,
                () -> fileText.getContentAsString(null));
    }

    @Test
    public void testGetLineCount(){
        FileText emptyText = new FileText();
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLineCount());
        assertEquals(2, fileText.getLineCount());
    }

    @Test
    public void testGetLineLength(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertEquals(20, fileText.getLineLength(0));
        assertEquals(13, fileText.getLineLength(1));
    }

    @Test
    public void testGetLineLengthNegativeIndex(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineLength(-1));
    }

    @Test
    public void testGetLineLengthIndexTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineLength(2));
    }

    @Test
    public void testGetLine(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line0, fileText.getLine(0));
        assertEquals(line1, fileText.getLine(1));
    }

    @Test
    public void testGetLineNegativeIndex(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLine(-1));
    }

    @Test
    public void testGetLineIndexTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLine(2));
    }

    @Test
    public void testGetLineAsString(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        String line0 = "This is a dummy text";
        String line1 = "No other use.";
        assertEquals(line0, fileText.getLineAsString(0));
        assertEquals(line1, fileText.getLineAsString(1));
    }

    @Test
    public void testGetLineAsStringNegativeIndex(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineAsString(-1));
    }

    @Test
    public void testGetLineAsStringIndexTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineAsString(2));
    }

    @Test
    public void testGetLineBetween(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] l1 = {'h', 'i', 's'};
        assertEquals(l1, fileText.getLineBetween(0, 1, 3 + 1));
        char[] l2 = {'e', 'x', 't'};
        assertEquals(l2, fileText.getLineBetween(0, 17, 19 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeIndex(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineBetween(-1, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenIndexTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineBetween(2, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeStart(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineBetween(0, -1, 5));
    }

    @Test
    public void testGetLineBetweenEndTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.getLineBetween(0, 0, 20 + 1));
    }

    @Test
    public void testInsertCharacter(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        fileText.insertCharacter('6', 0, 3);
        char[] line = {'T', 'h', 'i', '6', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfText(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        fileText.insertCharacter('6', 0, 0);
        char[] line = {'6', 'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfLine(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        fileText.insertCharacter('6', 1, 0);
        char[] line = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line, fileText.getLine(1));
    }

    @Test
    public void testInsertCharacterAtEndOfLine(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        fileText.insertCharacter('6', 0, 20);
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't', '6'};
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtEndOfText(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        fileText.insertCharacter('6', 1, 13);
        char[] line = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.', '6'};
        assertEquals(line, fileText.getLine(1));
    }

    @Test
    public void testInsertCharacterWithNegativeRow(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.insertCharacter('6', -1, 3));
    }

    @Test
    public void testInsertCharacterWithRowTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.insertCharacter('6', 2, 3));
    }

    @Test
    public void testInsertCharacterWithNegativeColumn(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.insertCharacter('6', 0, -1));
    }

    @Test
    public void testInsertCharacterWithColumnTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.insertCharacter('6', 0, 21));
    }

    @Test
    public void testRemoveCharacter(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = fileText.removeCharacter(0, 3);
        assertEquals('s', removed);
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfText(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = fileText.removeCharacter(0, 0);
        assertEquals('T', removed);
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfLine(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = fileText.removeCharacter(1, 0);
        assertEquals('N', removed);
        assertEquals(line, fileText.getLine(1));
    }

    @Test
    public void testRemoveCharacterAtEndOfLine(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x'};
        char removed = fileText.removeCharacter(0, 19);
        assertEquals('t', removed);
        assertEquals(line, fileText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtEndOfText(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e'};
        char removed = fileText.removeCharacter(1, 11);
        assertEquals('.', removed);
        assertEquals(line, fileText.getLine(1));
    }

    @Test
    public void testRemoveCharacterLineBreak(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e',
                'x', 't', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = fileText.removeCharacter(0, 20);
        assertEquals('\n', removed);
        assertEquals(line, fileText.getLine(0));
        assertEquals(1, fileText.getLineCount());
    }

    @Test
    public void testRemoveCharacterWithNegativeRow(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.removeCharacter(-1, 3));
    }

    @Test
    public void testRemoveCharacterWithRowTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.removeCharacter(2, 3));
    }

    @Test
    public void testRemoveCharacterWithNegativeColumn(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.removeCharacter(0, -1));
    }

    @Test
    public void testRemoveCharacterWithColumnTooLarge(){
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> fileText.removeCharacter(0, 13));
    }

    @Test
    public void testIsEmpty(){
        FileText emptyText = new FileText();
        FileText fileText = new FileText("This is a dummy text\nNo other use.");
        assertTrue(emptyText.isEmpty());
        assertFalse(fileText.isEmpty());
    }
}
