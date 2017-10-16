
import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GetTopWordsMethodTest {

    @Test
    public void stringWithSpesialSimbols() {

        Text text = new Text("## ,123 яяя" + "\n" + ",art-, a, @kot! @#");
        String[] result = text.getTopWords(3);

        assertThat(result[0], anyOf(is("a"), is("b")));
        assertThat(result[1], is("art"));
        assertThat(result[2], allOf(containsString("ot"), startsWith("k")));
        assertThat(result, arrayWithSize(3));
    }

    @Test
    public void stringWithApperCasesSimbols() {

        Text text = new Text(" a ART Kot");
        String[] result = text.getTopWords(3);

        assertThat(result, arrayContainingInAnyOrder("kot", "a", "art"));
    }

    @Test
    public void stringWitSameWords() {

        Text text = new Text("art art kot kot");
        String[] result = text.getTopWords(2);

        assertThat(result, arrayContaining("art", "kot"));
    }

    @Test
    public void zeroN() {

        Text text = new Text("begemot art a");
        String[] result = text.getTopWords(0);

        assertThat(result, emptyArray());
    }

    @Test
    public void outOfBoundsN() {

        Text text = new Text("begemot art a");
        String[] result = text.getTopWords(-1);

        assertThat(result, emptyArray());
    }

    @Test
    public void emptyText() {

        Text text = new Text("");
        String[] result = text.getTopWords(3);

        assertThat(result, emptyArray());
    }

    @Test(expected = NullPointerException.class)
    public void nullText() {

        Text text = new Text(null);
        String[] result = text.getTopWords(3);

    }

}


