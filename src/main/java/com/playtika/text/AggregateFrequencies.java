package com.playtika.text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class AggregateFrequencies {
    public static void main(String[] args) {
        Path directory = Paths.get("src/main/resources/testfiles");

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try {
                Map<String, Long> collect = Files.walk(directory)
                        .filter(Files::isRegularFile)
                        .flatMap(AggregateFrequencies::getLines)
                        .map(AggregateFrequencies::getWordFrequencies)
                        .flatMap(map -> map.entrySet().stream())
                        .collect(groupingBy(Map.Entry::getKey, counting()));
                System.out.println("Merge maps: " + collect);
            } catch (IOException e) {
                System.out.println("Upssss");
            }
        }
    }

    private static Map<String, Long> getWordFrequencies(String line) {
        return new Text(line).getWordFrequencies();
    }

    private static Stream<? extends String> getLines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.of("");
        }
    }
}
