package com.playtika.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class AggregateFrequencies {
    private static final Logger LOG = LoggerFactory.getLogger(AggregateFrequencies.class);

    public static void main(String[] args) {
        Path directory = Paths.get("src/main/resources/testfiles");
        LOG.debug("Wonted directory is {} ", directory.toString());

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            LOG.debug("wonted directory exist");
            try {
                Map<String, Long> collect = Files.walk(directory)
                        .filter(Files::isRegularFile)
                        .flatMap(AggregateFrequencies::getLines)
                        .map(AggregateFrequencies::getWordFrequencies)
                        .flatMap(map -> map.entrySet().stream())
                        .collect(groupingBy(Map.Entry::getKey, counting()));
                LOG.info("Merged map: ", collect);
            } catch (IOException e) {
                LOG.error("Can't work with specified directory {}", directory.toString());
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
            LOG.error("Cant get lines from file");
            LOG.error(e.getMessage());
            return Stream.of("");
        }
    }
}
