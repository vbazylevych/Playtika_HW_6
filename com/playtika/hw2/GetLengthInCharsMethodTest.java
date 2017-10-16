package com.playtika.hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GetLengthInCharsMethodTest {

    @Test
    public void getLenghtMethodReturnsCorrectLengthFotStringWithSpecificSymbols() {


        Text text = new Text("ko kot, !" + "\n");
        int result = text.getLengthInChars();
        int expectedResult = 5;

        assertEquals(result, expectedResult);
    }

    @Test
    public void getLenghtMethodReturnsCorrectLengthFotStringWithSameWords() {
        Text text = new Text("kot kot kot, !" + "\n");
        int result = text.getLengthInChars();
        int expectedResult = 9;

        assertEquals(result, expectedResult);
    }

    @Test
    public void getLenghtMethodReturnsCorrectLengthFotStringWithUpperCases() {
        Text text = new Text("Kot KROT !" + "\n");
        int result = text.getLengthInChars();
        int expectedResult = 7;

        assertEquals(result, expectedResult);
    }

    @Test
    public void getLenghtMethodReturnsZeroIfStringIsEmpty() {
        Text text = new Text("");
        int result = text.getLengthInChars();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

    @Test
    public void getLenghtMethodReturnsZeroIfStringWithoutWords() {
        Text text = new Text(" ");
        int result = text.getLengthInChars();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }
}
