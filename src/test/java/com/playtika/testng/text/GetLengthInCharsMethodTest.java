package com.playtika.testng.text;


import com.playtika.text.Text;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

public class GetLengthInCharsMethodTest {

    @Test(groups = {"GetLengthInChars"})
    public void stringWithSpecificSymbols() {
        Text text = new Text("koto,% #@ !" + "\n");
        long result = text.getLengthInChars();
        long expectedResult = 4;

        assertThat(expectedResult, Matchers.is(result));
    }

    @Test(groups = {"GetLengthInChars"})
    public void stringWithSameWords() {
        Text text = new Text("kot kot");
        double result = text.getLengthInChars();
        double expectedResult = 6;

        assertThat("same words processed incorrect", expectedResult, Matchers.equalTo(result));
    }

    @Test(groups = {"GetLengthInChars"})
    public void stringWithUpperCases() {
        Text text = new Text("Kot KROT");
        long result = text.getLengthInChars();
        long expectedResult = 7;

        assertThat("String with uppercases processed incorrect", expectedResult, greaterThanOrEqualTo(result));
    }

    @Test(groups = {"GetLengthInChars"},
            priority = 1)
    public void stringIsEmpty() {
        Text text = new Text("");
        long result = text.getLengthInChars();
        long expectedResult = 0;
        assertThat(expectedResult, Matchers.equalTo(result));
    }

    @Test(groups = {"GetLengthInChars"},
            priority = 1,
            dependsOnMethods = "stringIsEmpty")
    public void stringWithoutWords() {
        Text text = new Text(" ");
        long result = text.getLengthInChars();
        long expectedResult = 0;
        assertThat(expectedResult, CoreMatchers.not(1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            groups = {"GetLengthInChars"},
            priority = 2)
    public void stringWithNull() {
        Text text = new Text(null);
        text.getLengthInChars();
    }
}
