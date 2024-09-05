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
    public void testGetLength(){
        CharacterText emptyText = new CharacterText();
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLength());
        assertEquals(34, characterText.getLength());
    }

    @Test
    public void textGetContent(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[][] content = {
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'},
                {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'}
        };
        assertEquals(content, characterText.getContent());
    }

    @Test
    public void testGetContentAsString(){
        String dummyText = "This is a dummy text\nNo other use.";
        CharacterText characterText = new CharacterText(dummyText);
        assertEquals(dummyText, characterText.getContentAsString(Linebreak.LF));
    }

    @Test
    public void testGetContentAsStringWithNull(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(NullPointerException.class,
                () -> characterText.getContentAsString(null));
    }

    @Test
    public void testGetLineCount(){
        CharacterText emptyText = new CharacterText();
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(0, emptyText.getLineCount());
        assertEquals(2, characterText.getLineCount());
    }

    @Test
    public void testGetLineLength(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertEquals(20, characterText.getLineLength(0));
        assertEquals(13, characterText.getLineLength(1));
    }

    @Test
    public void testGetLineLengthNegativeIndex(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineLength(-1));
    }

    @Test
    public void testGetLineLengthIndexTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineLength(2));
    }

    @Test
    public void testGetLine(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line0 =
                {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char[] line1 = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line0, characterText.getLine(0));
        assertEquals(line1, characterText.getLine(1));
    }

    @Test
    public void testGetLineNegativeIndex(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLine(-1));
    }

    @Test
    public void testGetLineIndexTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLine(2));
    }

    @Test
    public void testGetLineAsString(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        String line0 = "This is a dummy text";
        String line1 = "No other use.";
        assertEquals(line0, characterText.getLineAsString(0));
        assertEquals(line1, characterText.getLineAsString(1));
    }

    @Test
    public void testGetLineAsStringNegativeIndex(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineAsString(-1));
    }

    @Test
    public void testGetLineAsStringIndexTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineAsString(2));
    }

    @Test
    public void testGetLineBetween(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] l1 = {'h', 'i', 's'};
        assertEquals(l1, characterText.getLineBetween(0, 1, 3 + 1));
        char[] l2 = {'e', 'x', 't'};
        assertEquals(l2, characterText.getLineBetween(0, 17, 19 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeIndex(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineBetween(-1, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenIndexTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineBetween(2, 1, 3 + 1));
    }

    @Test
    public void testGetLineBetweenNegativeStart(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineBetween(0, -1, 5));
    }

    @Test
    public void testGetLineBetweenEndTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.getLineBetween(0, 0, 20 + 1));
    }

    @Test
    public void testInsertCharacter(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        characterText.insertCharacter('6', 0, 3);
        char[] line = {'T', 'h', 'i', '6', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfText(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        characterText.insertCharacter('6', 0, 0);
        char[] line = {'6', 'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtStartOfLine(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        characterText.insertCharacter('6', 1, 0);
        char[] line = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        assertEquals(line, characterText.getLine(1));
    }

    @Test
    public void testInsertCharacterAtEndOfLine(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        characterText.insertCharacter('6', 0, 20);
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't', '6'};
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testInsertCharacterAtEndOfText(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        characterText.insertCharacter('6', 1, 13);
        char[] line = {'6', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.', '6'};
        assertEquals(line, characterText.getLine(1));
    }

    @Test
    public void testInsertCharacterWithNegativeRow(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.insertCharacter('6', -1, 3));
    }

    @Test
    public void testInsertCharacterWithRowTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.insertCharacter('6', 2, 3));
    }

    @Test
    public void testInsertCharacterWithNegativeColumn(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.insertCharacter('6', 0, -1));
    }

    @Test
    public void testInsertCharacterWithColumnTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.insertCharacter('6', 0, 21));
    }

    @Test
    public void testRemoveCharacter(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = characterText.removeCharacter(0, 3);
        assertEquals('s', removed);
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfText(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x', 't'};
        char removed = characterText.removeCharacter(0, 0);
        assertEquals('T', removed);
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtStartOfLine(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = characterText.removeCharacter(1, 0);
        assertEquals('N', removed);
        assertEquals(line, characterText.getLine(1));
    }

    @Test
    public void testRemoveCharacterAtEndOfLine(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ',
                'd', 'u', 'm', 'm', 'y', ' ', 't', 'e', 'x'};
        char removed = characterText.removeCharacter(0, 19);
        assertEquals('t', removed);
        assertEquals(line, characterText.getLine(0));
    }

    @Test
    public void testRemoveCharacterAtEndOfText(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e'};
        char removed = characterText.removeCharacter(1, 11);
        assertEquals('.', removed);
        assertEquals(line, characterText.getLine(1));
    }

    @Test
    public void testRemoveCharacterLineBreak(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        char[] line = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ','a', ' ', 'd', 'u', 'm', 'm', 'y', ' ', 't', 'e',
                'x', 't', 'N', 'o', ' ', 'o', 't', 'h', 'e', 'r', ' ', 'u', 's', 'e', '.'};
        char removed = characterText.removeCharacter(0, 20);
        assertEquals('\n', removed);
        assertEquals(line, characterText.getLine(0));
        assertEquals(1, characterText.getLineCount());
    }

    @Test
    public void testRemoveCharacterWithNegativeRow(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.removeCharacter(-1, 3));
    }

    @Test
    public void testRemoveCharacterWithRowTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.removeCharacter(2, 3));
    }

    @Test
    public void testRemoveCharacterWithNegativeColumn(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.removeCharacter(0, -1));
    }

    @Test
    public void testRemoveCharacterWithColumnTooLarge(){
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertThrows(IndexOutOfBoundsException.class,
                () -> characterText.removeCharacter(0, 13));
    }

    @Test
    public void testIsEmpty(){
        CharacterText emptyText = new CharacterText();
        CharacterText characterText = new CharacterText("This is a dummy text\nNo other use.");
        assertTrue(emptyText.isEmpty());
        assertFalse(characterText.isEmpty());
    }
}
