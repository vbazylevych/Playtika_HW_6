import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.Map.Entry.*;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Path directory = Paths.get("src/main/resources/testfiles");

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try {
                Map<String, Long> collect = Files.walk(directory)
                        .filter(Files::isRegularFile)
                        .flatMap(Main::getLines)
                        .map(Main::getWordFrequencies)
                        .flatMap(map -> map.entrySet().stream())
                        .collect(groupingBy(Entry::getKey, counting()));
                System.out.println("Merge maps: " + collect);
            } catch (IOException e) {
                System.out.println("Upssss");
            }
        }

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Kot", 96, "Kiev"));
        persons.add(new Person("Kot", 26, "Kiev"));
        persons.add(new Person("Kot", 26, "London"));
        persons.add(new Person("Alex", 28, "London"));
        persons.add(new Person("Krot", 96, "Odessa"));
        persons.add(new Person("Begemot", 15, "London"));
        persons.add(new Person("Igorek", 13, "Kiev"));

        //кол-во людей с именем кот
        long count = persons.stream()
                .filter(p -> p.getName().equals("Kot"))
                .count();
        System.out.println("Kol-vo Kotov: " + count);

        //средний возраст
        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        System.out.println("Average age of all people: " + averageAge);

        //самый старый
        persons.stream()
                .max(comparingInt(Person::getAge))
                .ifPresent(p -> System.out.println("The oldest man is " + p.getName()));

        //коллекция возраст - список людей
        Map<Integer, List<Person>> personOnAge = persons.stream()
                .collect(groupingBy(Person::getAge));

        //средний возраст по городу
        Map<String, Double> averageAgeOfCity = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .collect(groupingBy(Person::getCity, averagingDouble(Person::getAge)));
        System.out.println("Average age per city: " + averageAgeOfCity);

        //мегаполис
        persons.stream()
                .collect(groupingBy(Person::getCity, counting()))
                .entrySet().stream()
                .max(comparingByValue())
                .ifPresent(p -> System.out.println("Megapolis is: " + p.getKey()));
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

