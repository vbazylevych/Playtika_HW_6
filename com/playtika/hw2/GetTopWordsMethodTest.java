package com.playtika.hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetTopWordsMethodTest {

    @Test
    public void methodReturnsNTopWordsForStringWithSpesialSimbols() {

        Text text = new Text("## ,123 яяя" + "\n" + ",art-, a, @kot! @#");
        String[] result = text.getTopWords(3);
        String[] expectedResult = {"a", "art", "kot"};

        assertEquals(result, expectedResult);
    }

    @Test
    public void methodReturnsNTopWordsForStringWithApperCasesSimbols() {

        Text text = new Text("## , Art, a," + "Kot! @#");
        String[] result = text.getTopWords(3);
        String[] expectedResult = {"a", "art", "kot"};

        assertEquals(result, expectedResult);
    }

    @Test
    public void methodReturnsUniqueNTopWordsForStringWitSameWords() {

        Text text = new Text("##, Art, art, Kot!,a begemot, BEgimot @#");
        String[] result = text.getTopWords(3);
        String[] expectedResult = {"a", "art", "begemot"};

        assertEquals(result, expectedResult);
    }

    @Test
    public void methodReturnsEmptyArrayIfNisZero() {

        Text text = new Text("begemot art a");
        String[] result = text.getTopWords(0);
        String[] expectedResult = {};

        assertEquals(result, expectedResult);
    }

    @Test
    public void methodReturnsEmptyArrayIfNIsOutOfBounds() {

        Text text = new Text("begemot art a");
        String[] result = text.getTopWords(-1);
        String[] expectedResult = {};

        assertEquals(result, expectedResult);
    }

    @Test
    public void methodReturnsEmptyArrayIfTextIsEmpty() {

        Text text = new Text("");
        String[] result = text.getTopWords(3);
        String[] expectedResult = {};

        assertEquals(result, expectedResult);
    }


}


