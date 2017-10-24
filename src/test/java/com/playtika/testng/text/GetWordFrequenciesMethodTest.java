package com.playtika.testng.text;

import com.playtika.text.Text;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetWordFrequenciesMethodTest {

    @Test(groups = "GetWordFrequencies",
            priority = 1,
            dependsOnMethods = "stringWithSameWords")
    public void stringWithSpecificSymbols() {
        Text text = new Text("kot ^   ...//%sema, ,,");
        Map<String, Long> result = text.getWordFrequencies();

        assertThat(result, hasEntry("kot", 1L));
        assertThat(result, hasEntry("sema", 1L));
        assertThat(result.keySet(), hasSize(2));
    }

    @Test(groups = "GetWordFrequencies")
    public void stringWithSameWords() {
        Text text = new Text("kot kot sema");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kot", 2L);
        expectedMap.put("sema", 1L);

        assertThat(text.getWordFrequencies(), is(equalTo(expectedMap)));
    }

    @Test(groups = "GetWordFrequencies",
            priority = 1,
            dependsOnMethods = "stringWithSameWords")
    public void stringWithUpperCases() {
        Text text = new Text("koT KOT SEma");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kot", 2L);
        expectedMap.put("sema", 1L);

        assertThat(text.getWordFrequencies().keySet(), hasItems("kot", "sema"));
    }

    @Test(groups = "GetWordFrequencies",
            dependsOnMethods = "stringWithSameWords")
    public void stringWithUniqueWords() {
        Text text = new Text("kotkot rrr");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kotkot", 1L);
        expectedMap.put("rrr", 1L);

        assertThat(text.getWordFrequencies(), equalTo(expectedMap));
    }

    @Test(groups = "GetWordFrequencies",
            priority = 2)
    public void stringWithoutWords() {
        Text text = new Text("   ");
        Map<String, Long> expectedMap = new HashMap<>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }

    @Test(groups = "GetWordFrequencies",
            priority = 2)
    public void stringIsEmpty() {
        Text text = new Text("");
        Map<String, Long> expectedMap = new HashMap<>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            groups = "GetWordFrequencies",
            priority = 3)
    public void nullTest() {
        Text text = new Text(null);
        text.getWordFrequencies();
    }
}
