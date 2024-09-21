package com.vip;

import org.junit.jupiter.api.Test;

public class TextSpeedTest {

    private String getMadLongLineString(){
        return "This short text omega lul.".repeat(100000);
    }


    private String getTextWithAFewMadLongLines(){
        StringBuilder textBuilder = new StringBuilder();
        for(int i = 0; i < 10; i++){
            textBuilder.append(getMadLongLineString()).append('\n');
        }
        return textBuilder.toString();
    }


    private String getTextWithMadManyShortLines(){
        return "This short text omega lul.\n".repeat(100000);
    }

    private String getMadnessText(){
        StringBuilder textBuilder = new StringBuilder();
        for(int i = 0; i < 1000; i++){
            if(i % 100 == 0){
                textBuilder.append(getMadLongLineString()).append('\n');
            }else{
                textBuilder.append("short string\n");
            }
        }
        return textBuilder.toString();
    }

    @Test
    public void testSpeedComparisonMassiveText(){
        System.out.println("Starting isEmpty test: ");
        compareResults(testIsEmptyListText(), testIsEmptyCharacterText());
        System.out.println("------------------------------");

        System.out.println("Starting getLength() test: ");
        compareResults(testGetLengthListText(), testGetLengthCharacterText());
        System.out.println("------------------------------");

        System.out.println("Starting getContent() test: ");
        compareResults(testGetContentListText(), testGetContentCharacterText());
        System.out.println("------------------------------");

        System.out.println("Starting getLineCount() test: ");
        compareResults(testGetLineCountListText(), testGetLineCountCharacterText());
        System.out.println("------------------------------");

        System.out.println("Starting getLineLength() test: ");
        System.out.println("First row: ");
        compareResults(testGetLineLengthListTextFIRSTROW(), testGetLineLengthCharacterTextFIRSTROW());
        System.out.println();
        System.out.println("Last row: ");
        compareResults(testGetLineLengthListTextLASTROW(), testGetLineLengthCharacterTextLASTROW());
        System.out.println("------------------------------");

        System.out.println("Starting getLine() test: ");
        System.out.println("First row: ");
        compareResults(testGetLineListTextFIRSTROW(), testGetLineCharacterTextFIRSTROW());
        System.out.println();
        System.out.println("Last row: ");
        compareResults(testGetLineListTextLASTROW(), testGetLineCharacterTextLASTROW());
        System.out.println("------------------------------");

        System.out.println("Starting insertCharacter() test: ");
        System.out.println("First row: ");
        compareResults(testInsertCharacterListTextFIRSTLINE(), testInsertCharacterCharacterTextFIRSTLINE());

        System.out.println();
        System.out.println("Last row: ");
        compareResults(testInsertCharacterListTextLASTLINE(), testInsertCharacterCharacterTextLASTLINE());

        System.out.println();
        System.out.println("New line inserts: ");
        compareResults(testInsertCharacterListTextNEWLINE(), testInsertCharacterCharacterTextNEWLINE());
        System.out.println("------------------------------");

        System.out.println("Starting getLineBetween() test: ");
        compareResults(testGetLineBetweenListText(), testGetLineBetweenCharacterText());
        System.out.println("------------------------------");

        System.out.println("Starting removeCharacter() test: ");
        System.out.println("Start of row: ");
        compareResults(testRemoveCharacterLineText(), testRemoveCharacterCharacterText());
        System.out.println();
        System.out.println("End of row: ");
        compareResults(testRemoveCharacterListTextEND(), testRemoveCharacterCharacterTextEND());
        System.out.println();
        System.out.println("Newline: ");
        compareResults(testRemoveCharacterListTextNEWLINE(), testRemoveCharacterCharacterTextNEWLINE());
        System.out.println("------------------------------");


        System.out.println("Starting countOccurrences() test: ");
        compareResults(testCountOccurrencesLineText(), testCountOccurrencesCharacterText());
        System.out.println("------------------------------");
    }

    private void compareResults(double listTextSpeed, double characterTextSpeed){
        System.out.format("ListText time (ms): %f\n", listTextSpeed/1_000_000);
        System.out.format("CharacterText time (ms): %f\n", characterTextSpeed   /1_000_000);
        if(listTextSpeed < characterTextSpeed){
            System.out.format("ListText is %f faster than CharacterText\n", characterTextSpeed / listTextSpeed);
        }else{
            System.out.format("CharacterText is %f faster than ListText\n", listTextSpeed / characterTextSpeed);
        }
    }



    public double testIsEmptyListText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        boolean XOR = false;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.isEmpty();
        }
        double endNanos = System.nanoTime();
        if(XOR){
            System.out.println("IGNORE");
        }
        return endNanos - startNanos;
    }

    public double testIsEmptyCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        boolean XOR = false;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.isEmpty();
        }
        if(XOR){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLengthListText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLength();
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLengthCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLength();
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetContentListText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getContent().length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetContentCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getContent().length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineCountListText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLineCount();
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineCountCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLineCount();
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineLengthListTextFIRSTROW(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLineLength(0);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineLengthCharacterTextFIRSTROW(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLineLength(0);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineLengthListTextLASTROW(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLineLength(999);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineLengthCharacterTextLASTROW(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLineLength(999);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineListTextFIRSTROW(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLine(0).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineCharacterTextFIRSTROW(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLine(0).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineListTextLASTROW(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLine(999).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineCharacterTextLASTROW(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLine(999).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterListTextFIRSTLINE(){
        char c = 'p';
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            listText.insertCharacter(c, 0, 0);
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterCharacterTextFIRSTLINE(){
        char c = 'p';
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            characterText.insertCharacter(c, 0, i);
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterListTextNEWLINE(){
        char c = '\n';
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            listText.insertCharacter(c, i,0);
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterListTextLASTLINE(){
        char c = 'p';
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            listText.insertCharacter(c, 999, 0);
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterCharacterTextLASTLINE(){
        char c = 'p';
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 100; i++){
            characterText.insertCharacter(c, 999, i);
        }
        return System.nanoTime() - startNanos;
    }

    public double testInsertCharacterCharacterTextNEWLINE(){
        char c = '\n';
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        double startNanos = System.nanoTime();
        for(int i = 0; i < 100; i++){
            characterText.insertCharacter(c, i, 0);
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineBetweenListText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.getLineBetween(0, 100, 1000).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testGetLineBetweenCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.getLineBetween(0, 100, 1000).length;
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterLineText(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.removeCharacter(0, 0);
        }
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterCharacterText(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.removeCharacter(0, 0);
        }
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterListTextEND(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        int length = listText.getLineLength(0);
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.removeCharacter(0, length - i - 3);
        }
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterCharacterTextEND(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        int length = characterText.getLineLength(0);
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.removeCharacter(0, length - i - 3);
        }
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterListTextNEWLINE(){
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        int length = listText.getLineLength(0);
        XOR ^= listText.removeCharacter(0, length);
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testRemoveCharacterCharacterTextNEWLINE(){
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        double startNanos = System.nanoTime();
        int length = characterText.getLineLength(0);
        XOR ^= characterText.removeCharacter(0, length);
        if(XOR == 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    // TEST REMOVE AT END, AND ESPECIALLY TEST REMOVE LINEBREAK


    public double testCountOccurrencesLineText(){
        char[] word = {'o', 'm', 'e', 'g', 'a', 'l', 'u', 'l'};
        double startNanos = System.nanoTime();
        String madnessText = getMadnessText();
        ListText listText = new ListText(madnessText);
        int XOR = 0;
        for(int i = 0; i < 1000; i++){
            XOR ^= listText.countOccurrences(word);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }

    public double testCountOccurrencesCharacterText(){
        char[] word = {'o', 'm', 'e', 'g', 'a', 'l', 'u', 'l'};
        double startNanos = System.nanoTime();
        String madnessText = getMadnessText();
        CharacterText characterText = new CharacterText(madnessText);
        int XOR = 0;
        for(int i = 0; i < 1000; i++){
            XOR ^= characterText.countOccurrences(word);
        }
        if(XOR != 0){
            System.out.println("IGNORE");
        }
        return System.nanoTime() - startNanos;
    }
}
