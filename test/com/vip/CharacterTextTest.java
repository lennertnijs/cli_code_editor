package com.vip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTextTest {

    @Test
    public void testConstructorWithNull(){
        assertThrows(NullPointerException.class,
                () -> new CharacterText(null));
    }

    @Test
    public void testConstructorWithLF(){
        CharacterText lf = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(35, lf.getLength());
    }

    @Test
    public void testConstructorWithCR(){
        CharacterText cr = new CharacterText("This is a dummy text\rNo other use.");
        assertEquals(35, cr.getLength());
    }

    @Test
    public void testConstructorWithCRLF(){
        CharacterText crlf = new CharacterText("This is a dummy text\r\nNo other use.");
        assertEquals(35, crlf.getLength());
    }

    @Test
    public void testIsEmpty(){
        CharacterText emptyText = new CharacterText();
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertTrue(emptyText.isEmpty());
        assertFalse(characterText.isEmpty());
    }

    @Test
    public void testGetLength(){
        CharacterText emptyText = new CharacterText();
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLength());
        assertEquals(35, text.getLength());
    }

    @Test
    public void textGetContent(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[][] content = {
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'},
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'}
        };
        assertEquals(content, text.getContent());
    }

    @Test
    public void testGetContentAsStringWithLF(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals("This is a dummy text\nNo other use.", text.getContentAsString(Linebreak.LF));
    }

    @Test
    public void testGetContentAsStringWithCR(){
        CharacterText text = new CharacterText("This is a dummy text\rNo other use.");
        assertEquals("This is a dummy text\rNo other use.", text.getContentAsString(Linebreak.CR));
    }

    @Test
    public void testGetContentAsStringWithCRLF(){
        CharacterText text = new CharacterText("This is a dummy text\r\nNo other use.");
        assertEquals("This is a dummy text\r\nNo other use.", text.getContentAsString(Linebreak.CRLF));
    }

    @Test
    public void testGetContentAsStringWithNull(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(NullPointerException.class, () -> text.getContentAsString(null));
    }

    @Test
    public void testGetLineCount(){
        CharacterText emptyText = new CharacterText();
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLineCount());
        assertEquals(2, text.getLineCount());
    }

    @Test
    public void testGetLineLength(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(20, text.getLineLength(0));
        assertEquals(13, text.getLineLength(1));
    }

    @Test
    public void testGetLineLengthNegativeIndex(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineLength(-1));
    }

    @Test
    public void testGetLineLengthIndexTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineLength(2));
    }

    @Test
    public void testGetLine(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char[] line1 =
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line0, text.getLine(0));
        assertEquals(line1, text.getLine(1));
    }

    @Test
    public void testGetLineNegativeIndex(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLine(-1));
    }

    @Test
    public void testGetLineIndexTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLine(2));
    }

    @Test
    public void testGetLineAsString(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        String line0 = "This is a dummy text";
        String line1 = "No other use.";
        assertEquals(line0, text.getLineAsString(0));
        assertEquals(line1, text.getLineAsString(1));
    }

    @Test
    public void testGetLineAsStringNegativeIndex(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineAsString(-1));
    }

    @Test
    public void testGetLineAsStringIndexTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineAsString(2));
    }

    @Test
    public void testGetLineBetween(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'h', 'i', 's'};
        assertEquals(line, text.getLineBetween(0, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeIndex(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(-1, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenIndexTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(2, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeStart(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, -1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenStartTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 20, 20));
    }

    @Test
    public void testGetLineBetweenNegativeEnd(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 0, -1));
    }

    @Test
    public void testGetLineBetweenEndTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 0, 20 + 1));
    }

    @Test
    public void testGetLineBetweenEndSmallerThanStart(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 5, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> text.getLineBetween(0, 5, 0));
    }

    @Test
    public void testInsertCharacter(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 3);
        char[] line =
                {'T', 'h', 'i', '6', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfText(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 0);
        char[] line0 = {'6', 'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line0, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfLine(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 1, 0);
        char[] line1 = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line1, text.getLine(1));
    }

    @Test
    public void testInsertCharacterAtEndOfLine(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 0, 20);
        char[] line0 = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't', '6'};
        assertEquals(line0, text.getLine(0));
    }

    @Test
    public void testInsertCharacterAtEndOfText(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        text.insertCharacter('6', 1, 13);
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.', '6'};
        assertEquals(line1, text.getLine(1));
    }

    @Test
    public void testInsertCharacterWithNegativeRow(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', -1, 3));
    }

    @Test
    public void testInsertCharacterWithRowTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 2, 3));
    }

    @Test
    public void testInsertCharacterWithNegativeColumn(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 0, -1));
    }

    @Test
    public void testInsertCharacterWithColumnTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.insertCharacter('6', 0, 21));
    }

    @Test
    public void testRemoveCharacter(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', ' ', 'i', 's', ' ', 'a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = text.removeCharacter(0, 3);
        assertEquals('s', removed);
        assertEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfText(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = text.removeCharacter(0, 0);
        assertEquals('T', removed);
        assertEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfLine(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line1 = {'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = text.removeCharacter(1, 0);
        assertEquals('N', removed);
        assertEquals(line1, text.getLine(1));
    }

    @Test
    public void testRemoveCharacterAtEndOfLine(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x'};
        char removed = text.removeCharacter(0, 19);
        assertEquals('t', removed);
        assertEquals(line0, text.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtEndOfText(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e'};
        char removed = text.removeCharacter(1, 12);
        assertEquals('.', removed);
        assertEquals(line1, text.getLine(1));
    }

    @Test
    public void testRemoveCharacterLineBreak(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        char[] line =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e',
                'x', 't', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = text.removeCharacter(0, 20);
        assertEquals('\n', removed);
        assertEquals(line, text.getLine(0));
        assertEquals(1, text.getLineCount());
    }

    @Test
    public void testRemoveCharacterWithNegativeRow(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(-1, 3));
    }

    @Test
    public void testRemoveCharacterWithRowTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(2, 3));
    }

    @Test
    public void testRemoveCharacterWithNegativeColumn(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(0, -1));
    }

    @Test
    public void testRemoveCharacterWithColumnTooLarge(){
        CharacterText text = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class, () -> text.removeCharacter(0, 13));
    }

    @Test
    public void testCountOccurrences(){
        CharacterText text = new CharacterText("This is a dummy text\nThisThis This.");
        char[] query1 = {'T', 'h', 'i', 's'};
        char[] query2 = {'t', 'h', 'i', 's'};
        assertEquals(4, text.countOccurrences(query1));
        assertEquals(0, text.countOccurrences(query2));
    }

    @Test
    public void testCountOccurrencesWithNull(){
        CharacterText text = new CharacterText("This is a dummy text\nThisThis This.");
        assertThrows(NullPointerException.class, () -> text.countOccurrences(null));
    }
}
