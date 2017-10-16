package com.playtika.hw2;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;


public class GetWordFrequenciesMethodTest {


    @Test()
    public void getWordFrequenciesReturnsCorrectNumbersOfWordsForStringWithSpecificSymbols() {
        Text text = new Text("kot ^   ...//%sema, ,,");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kot", 1);
        expectedMap.put("sema", 1);

        assertEquals(expectedMap, text.getWordFrequencies());
    }

    @Test
    public void getWordFrequenciesReturnsCorrectNumbersOfWordsForStringWithSameWords() {
        Text text = new Text("kot kot ^   %sema, ,,");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kot", 2);
        expectedMap.put("sema", 1);

        assertEquals(expectedMap, text.getWordFrequencies());
    }

    @Test
    public void getWordFrequenciesReturnsCorrectNumbersOfWordsForStringWithUpperCases() {
        Text text = new Text("koT KOT   %SEma, ,,");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kot", 2);
        expectedMap.put("sema", 1);

        assertEquals(expectedMap, text.getWordFrequencies());
    }

    @Test
    public void getWordFrequenciesReturnsCorrectNumbersOfWordsForStringWithUniqueWords() {
        Text text = new Text("kotkot, ,," + "rrr");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kotkot", 1);
        expectedMap.put("rrr1", 1);
    }

    @Test
    public void getWordFrequenciesReturnsEmptyMapIfStringWithoutWords() {
        Text text = new Text("   ");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();

        assertEquals(expectedMap, text.getWordFrequencies());
    }

    @Test
    public void getWordFrequenciesReturnsEmptyMapIfStringIsEmpty() {
        Text text = new Text("");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();

        assertEquals(expectedMap, text.getWordFrequencies());
    }


@Test (expected = IllegalArgumentException.class)
    public void nullTest( ){
        Text text = new Text(null);
         text.getWordFrequencies();
    }

}
