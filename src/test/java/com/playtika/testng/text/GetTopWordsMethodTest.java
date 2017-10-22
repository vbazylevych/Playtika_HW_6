package com.playtika.testng.text;


import com.playtika.text.Text;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetTopWordsMethodTest {

    @Test(groups = "GetTopWords")
    public void stringWithSpecialSymbols() {
        Text text = new Text("# ? \\%^ **() : art-, a, @kot!");
        String[] result = text.getTopWords(3).toArray(new String[0]);

        assertThat(result[0], anyOf(is("a"), is("b")));
        assertThat(result[1], is("art"));
        assertThat(result[2], allOf(containsString("ot"), startsWith("k")));
        assertThat(result, arrayWithSize(3));
    }

    @Test(groups = "GetTopWords",
            priority = 2,
            dependsOnMethods = "stringWithSpecialSymbols")
    public void stringWithNumbers() {
        Text text = new Text("123 0 art a kot 23");
        String[] expectedResult = {"a", "art", "kot"};
        String[] result = text.getTopWords(3).toArray(new String[0]);
        assertEquals(expectedResult, result);
    }

    @Test(groups = "GetTopWords",
            priority = 3,
            dependsOnMethods = "stringWithSpecialSymbols")
    public void stringWithTabulation() {
        Text text = new Text("kot\t  \nart a");
        String[] expectedResult = {"a", "art", "kot"};
        String[] result = text.getTopWords(3).toArray(new String[0]);
        assertEquals(expectedResult, result);
    }

    @Test(groups = "GetTopWords",
            priority = 4,
            dependsOnMethods = "stringWithSpecialSymbols")
    public void stringWithOtherEncoding() {
        Text text = new Text("kot кот абракакот art a");
        String[] expectedResult = {"a", "art", "kot"};
        String[] result = text.getTopWords(3).toArray(new String[0]);
        assertEquals(expectedResult, result);
    }

    @Test(groups = "GetTopWords",
            priority = 1)
    public void stringWithApperCasesSimbols() {
        Text text = new Text(" a ART Kot");
        String[] result = text.getTopWords(3).toArray(new String[0]);
        assertThat(result, arrayContainingInAnyOrder("kot", "a", "art"));
    }

    @Test(groups = "GetTopWords",
            priority = 1)
    public void stringWitSameWords() {
        Text text = new Text("art art kot kot");
        String[] result = text.getTopWords(2).toArray(new String[0]);
        assertThat(result, arrayContaining("art", "kot"));
    }

    @Test(groups = "GetTopWords",
            priority = 4)
    public void zeroN() {
        Text text = new Text("begemot art a");
        String[] result = text.getTopWords(0).toArray(new String[0]);
        assertThat(result, emptyArray());
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            groups = "GetTopWords",
            priority = 5)
    public void outOfBoundsN() {
        Text text = new Text("begemot art a");
        text.getTopWords(-1).toArray(new String[0]);
    }

    @Test(groups = "GetTopWords",
            priority = 4)
    public void emptyText() {
        Text text = new Text("");
        String[] result = text.getTopWords(3).toArray(new String[0]);
        assertThat(result, emptyArray());
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            groups = "GetTopWords",
            priority = 5)
    public void nullText() {
        Text text = new Text(null);
        text.getTopWords(3).toArray(new String[0]);
    }
}


