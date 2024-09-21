package com.vip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListTextTest {

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class,
                () -> new ListText((String) null));
    }

    @Test
    public void testConstructorWithLF(){
        ListText lf = new ListText("This is a dummy text\nNo other use.");
        assertEquals(34, lf.getLength());
    }

    @Test
    public void testConstructorWithCR(){
        ListText cr = new ListText("This is a dummy text\rNo other use.");
        assertEquals(34, cr.getLength());
    }

    @Test
    public void testConstructorWithCRLF(){
        ListText crlf = new ListText("This is a dummy text\r\nNo other use.");
        assertEquals(34, crlf.getLength());
    }

    @Test
    public void testIsEmpty(){
        ListText emptyText = new ListText();
        ListText listText = new ListText("This is a dummy text\nNo other use.");
        assertTrue(emptyText.isEmpty());
        assertFalse(listText.isEmpty());
    }

    @Test
    public void testGetLength(){
        ListText emptyText = new ListText();
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLength());
        assertEquals(34, text.getLength());
    }

    @Test
    public void textGetContent(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[][] content = {
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'},
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'}
        };
        assertArrayEquals(content, text.getContent());
    }

    @Test
    public void testGetLineCount(){
        ListText emptyText = new ListText();
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertEquals(1, emptyText.getLineCount());
        assertEquals(2, text.getLineCount());
    }

    @Test
    public void testGetLineLength(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertEquals(20, text.getLineLength(0));
        assertEquals(13, text.getLineLength(1));
    }

    @Test
    public void testGetLineLengthNegativeIndex(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineLength(-1));
    }

    @Test
    public void testGetLineLengthIndexTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineLength(2));
    }

    @Test
    public void testGetLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char[] line1 =
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertArrayEquals(line0, text.getLine(0));
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testGetLineNegativeIndex(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLine(-1));
    }

    @Test
    public void testGetLineIndexTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLine(2));
    }

    @Test
    public void testGetLineBetween(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line = {'h', 'i', 's'};
        assertArrayEquals(line, text.getLineBetween(0, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeIndex(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(-1, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenIndexTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(2, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeStart(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, -1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenStartTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 20, 20));
    }

    @Test
    public void testGetLineBetweenNegativeEnd(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 0, -1));
    }

    @Test
    public void testGetLineBetweenEndTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 0, 20 + 1));
    }

    @Test
    public void testGetLineBetweenEndSmallerThanStart(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IllegalArgumentException.class, () -> text.getLineBetween(0, 5, 5));
        assertThrows(IllegalArgumentException.class, () -> text.getLineBetween(0, 5, 0));
    }

    @Test
    public void testInsertCharacter(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 3);
        char[] line =
                {'T', 'h', 'i', '6', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertArrayEquals(line, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfText(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 0);
        char[] line0 = {'6', 'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertArrayEquals(line0, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 1, 0);
        char[] line1 = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testInsertCharacterAtEndOfLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 20);
        char[] line0 = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't', '6'};
        assertArrayEquals(line0, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtEndOfText(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 1, 13);
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.', '6'};
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testInsertCharacterNewLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('\n', 0, 3);
        char[] line0 = {'T', 'h', 'i'};
        char[] line1 = {'s', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(3, text.getLineCount());
        assertEquals(34 + 1, text.getLength());
        assertEquals(3, text.getLineLength(0));
        assertEquals(17, text.getLineLength(1));
        assertArrayEquals(line0, text.getLine(0));
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testInsertCharacterMultipleNewLines(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        text.insertCharacter('\n', 0, 3);
        text.insertCharacter('\n', 0, 0);
        text.insertCharacter('\n', 2, 2);
        text.insertCharacter('p', 4, 1);
        char[] line0 = {};
        char[] line1 = {'T', 'h', 'i'};
        char[] line2 = {'s', ' '};
        char[] line3 = {'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char[] line4 = {'N', 'p', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(5, text.getLineCount());
        assertEquals(34 + 4, text.getLength());
        assertArrayEquals(line0, text.getLine(0));
        assertArrayEquals(line1, text.getLine(1));
        assertArrayEquals(line2, text.getLine(2));
        assertArrayEquals(line3, text.getLine(3));
        assertArrayEquals(line4, text.getLine(4));
    }

    @Test
    public void testInsertCharacterWithNegativeRow(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', -1, 3));
    }

    @Test
    public void testInsertCharacterWithRowTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 2, 3));
    }

    @Test
    public void testInsertCharacterWithNegativeColumn(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 0, -1));
    }

    @Test
    public void testInsertCharacterWithColumnTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 0, 21));
    }

    @Test
    public void testRemoveCharacter(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', ' ', 'i', 's', ' ', 'a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = text.removeCharacter(0, 3);
        assertEquals('s', removed);
        assertArrayEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfText(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = text.removeCharacter(0, 0);
        assertEquals('T', removed);
        assertArrayEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line1 = {'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = text.removeCharacter(1, 0);
        assertEquals('N', removed);
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testRemoveCharacterAtEndOfLine(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x'};
        char removed = text.removeCharacter(0, 19);
        assertEquals('t', removed);
        assertArrayEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtEndOfText(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e'};
        char removed = text.removeCharacter(1, 12);
        assertEquals('.', removed);
        assertArrayEquals(line1, text.getLine(1));
    }

    @Test
    public void testRemoveCharacterLineBreak(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        char[] line =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e',
                'x', 't', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = text.removeCharacter(0, 20);
        assertEquals('\n', removed);
        assertArrayEquals(line, text.getLine(0));
        assertEquals(1, text.getLineCount());
    }

    @Test
    public void testRemoveCharacterWithNegativeRow(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(-1, 3));
    }

    @Test
    public void testRemoveCharacterWithRowTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(2, 3));
    }

    @Test
    public void testRemoveCharacterWithNegativeColumn(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(0, -1));
    }

    @Test
    public void testRemoveCharacterWithColumnTooLarge(){
        ListText text = new ListText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(1, 13));
    }

    @Test
    public void testCountOccurrences(){
        ListText text = new ListText("This is a dummy text\nThisThis This.");
        char[] query1 = {'T', 'h', 'i', 's'};
        char[] query2 = {'t', 'h', 'i', 's'};
        char[] query3 = {'i', 's'};
        assertEquals(4, text.countOccurrences(query1));
        assertEquals(0, text.countOccurrences(query2));
        assertEquals(5, text.countOccurrences(query3));
    }

    @Test
    public void testCountOccurrencesWithNull(){
        ListText text = new ListText("This is a dummy text\nThisThis This.");
        assertThrows(NullPointerException.class, () -> text.countOccurrences(null));
    }
}
