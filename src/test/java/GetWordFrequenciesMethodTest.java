
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GetWordFrequenciesMethodTest {


    @Test()
    public void stringWithSpecificSymbols() {
        Text text = new Text("kot ^   ...//%sema, ,,");
        Map<String, Long> result = text.getWordFrequencies();

        assertThat(result, hasEntry("kot", 1L));
        assertThat(result, hasEntry("sema", 1L));
        assertThat(result.keySet(), hasSize(2));

    }

    @Test
    public void stringWithSameWords() {
        Text text = new Text("kot kot sema");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kot", 2L);
        expectedMap.put("sema", 1L);

        assertThat(text.getWordFrequencies(), is(equalTo(expectedMap)));
    }

    @Test
    public void stringWithUpperCases() {
        Text text = new Text("koT KOT SEma");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kot", 2L);
        expectedMap.put("sema", 1L);

        assertThat(text.getWordFrequencies().keySet(), hasItems("kot", "sema"));
    }

    @Test
    public void stringWithUniqueWords() {
        Text text = new Text("kotkot rrr");
        Map<String, Long> expectedMap = new HashMap<>();
        expectedMap.put("kotkot", 1L);
        expectedMap.put("rrr", 1L);

        assertThat(text.getWordFrequencies(), equalTo(expectedMap));
    }

    @Test
    public void stringWithoutWords() {
        Text text = new Text("   ");
        Map<String, Long> expectedMap = new HashMap<>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }

    @Test
    public void stringIsEmpty() {
        Text text = new Text("");
        Map<String, Long> expectedMap = new HashMap<>();

        assertThat(text.getWordFrequencies().entrySet(), empty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void nullTest() {
        Text text = new Text(null);
        text.getWordFrequencies();
    }
}
