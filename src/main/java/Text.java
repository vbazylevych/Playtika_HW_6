import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Text {

    private final String text;
    private final List<String> words;

    public Text(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text should not be null");
        } else {
            this.text = text;
            this.words = Stream.of(text.toLowerCase().split("[^A-Za-z]")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        }
    }

    public List<String> getTopWords(int n) {

        return words.stream()
                .sorted()
                .distinct()
                .limit(n)
                .collect(toList());
    }

    public Map<String, Long> getWordFrequencies() {
        return words.stream()
                .collect(groupingBy(s -> s, counting()));
    }

    public long getLengthInChars() {
        return words.stream()
                .mapToInt(String::length)
                .sum();
    }
}
