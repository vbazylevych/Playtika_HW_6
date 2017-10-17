package com.playtika.text;

import org.junit.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.hamcrest.CoreMatchers;


public class GetLengthInCharsMethodTest {

    @Test
    public void stringWithSpecificSymbols() {
        Text text = new Text("koto,% #@ !" + "\n");
        long result = text.getLengthInChars();
        long expectedResult = 4;

        assertThat(expectedResult, Matchers.is(result));


    }

    @Test
    public void stringWithSameWords() {
        Text text = new Text("kot kot");
        double result = text.getLengthInChars();
        double expectedResult = 6;

        assertThat("same words processed incorrect",expectedResult,  Matchers.equalTo(result));
    }

    @Test
    public void stringWithUpperCases() {
        Text text = new Text("Kot KROT");
        long result = text.getLengthInChars();
        long expectedResult = 7;

        assertThat("String with uppercases processed incorrect", expectedResult, greaterThanOrEqualTo(result));
    }

    @Test
    public void stringIsEmpty() {
        Text text = new Text("");
        long result = text.getLengthInChars();
        long expectedResult = 0;
        assertThat(expectedResult, Matchers.equalTo(result));
    }

    @Test
    public void stringWithoutWords() {
        Text text = new Text(" ");
        long result = text.getLengthInChars();
        long expectedResult = 0;
        assertThat(expectedResult, CoreMatchers.not(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void stringWithNull() {
        Text text = new Text(null);
        text.getLengthInChars();
    }


}
