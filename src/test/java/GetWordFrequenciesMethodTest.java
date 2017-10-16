
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GetWordFrequenciesMethodTest {


    @Test()
    public void stringWithSpecificSymbols() {
        Text text = new Text("kot ^   ...//%sema, ,,");
        Map<String, Integer> result = text.getWordFrequencies();

        assertThat(result, hasEntry("kot", 1));
        assertThat(result, hasEntry("sema", 1));
        assertThat(result.keySet(), hasSize(2));

    }

    @Test
    public void stringWithSameWords() {
        Text text = new Text("kot kot sema");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kot", 2);
        expectedMap.put("sema", 1);

        assertThat(text.getWordFrequencies(), is(equalTo(expectedMap)));
    }

    @Test
    public void stringWithUpperCases() {
        Text text = new Text("koT KOT SEma");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kot", 2);
        expectedMap.put("sema", 1);

        assertThat(text.getWordFrequencies().keySet(), hasItems("kot", "sema"));
    }

    @Test
    public void stringWithUniqueWords() {
        Text text = new Text("kotkot rrr");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();
        expectedMap.put("kotkot", 1);
        expectedMap.put("rrr", 1);

        assertThat(text.getWordFrequencies(), equalTo(expectedMap));
    }

    @Test
    public void stringWithoutWords() {
        Text text = new Text("   ");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }

    @Test
    public void stringIsEmpty() {
        Text text = new Text("");
        Map<String, Integer> expectedMap = new HashMap<String, Integer>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }


    @Test(expected = NullPointerException.class)
    public void nullTest() {
        Text text = new Text(null);
        text.getWordFrequencies();
    }

}
