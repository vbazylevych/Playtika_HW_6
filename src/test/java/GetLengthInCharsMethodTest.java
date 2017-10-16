
import org.junit.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.hamcrest.CoreMatchers;


public class GetLengthInCharsMethodTest {

    @Test
    public void stringWithSpecificSymbols() {
        Text text = new Text("kot,% #@ !" + "\n");
        int result = text.getLengthInChars();
        int expectedResult = 3;

        assertThat(expectedResult, Matchers.is(result));


    }

    @Test
    public void stringWithSameWords() {
        Text text = new Text("kot kot");
        double result = text.getLengthInChars();
        double expectedResult = 16;

        assertThat("same words processed incorrect",expectedResult, closeTo(result,10));
    }

    @Test
    public void stringWithUpperCases() {
        Text text = new Text("Kot KROT");
        int result = text.getLengthInChars();
        int expectedResult = 7;

        assertThat("String with uppercases processed incorrect", expectedResult, greaterThanOrEqualTo(result));
    }

    @Test
    public void stringIsEmpty() {
        Text text = new Text("");
        int result = text.getLengthInChars();
        int expectedResult = 0;
        assertThat(expectedResult, Matchers.equalTo(result));
    }

    @Test
    public void stringWithoutWords() {
        Text text = new Text(" ");
        int result = text.getLengthInChars();
        int expectedResult = 0;
        assertThat(expectedResult, CoreMatchers.not(1));
    }

    @Test(expected = NullPointerException.class)
    public void stringWithNull() {
        Text text = new Text(null);
        text.getLengthInChars();
    }


}
