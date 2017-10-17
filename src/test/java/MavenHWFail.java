import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class MavenHWFail {
    @Test()
    public void stringWithSpecificSymbols() {
        Text text = new Text("kot ^   ...//%sema, ,,");
        Map<String, Long> result = text.getWordFrequencies();

        assertThat(result, hasEntry("kot111", 1L));
        assertThat(result, hasEntry("sema", 1L));
        assertThat(result.keySet(), hasSize(2));
    }
}
