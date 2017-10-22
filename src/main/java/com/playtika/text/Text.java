package com.playtika.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Text {

    private final String text;
    private final List<String> words;
    private static final Logger LOG = LoggerFactory.getLogger(AggregateFrequencies.class);

    public Text(String text) {
        if (text == null) {
            throw new IllegalArgumentException("com.playtika.text.Text should not be null");
        } else {
            LOG.debug("text with string {} was created", text);
            this.text = text;
            this.words = Stream.of(text.toLowerCase().split("[^A-Za-z]")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            LOG.debug("text was split to words");
        }
    }

    public List<String> getTopWords(int n) {
        LOG.debug("get ton {} words", n);
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
