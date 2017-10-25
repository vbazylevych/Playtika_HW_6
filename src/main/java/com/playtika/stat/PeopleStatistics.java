package com.playtika.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static java.util.Comparator.*;
import static java.util.Map.Entry.*;
import static java.util.stream.Collectors.*;

public class PeopleStatistics {
    private static final Logger LOG = LoggerFactory.getLogger(PeopleStatistics.class);

    public static void main(String[] args) throws IOException {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Kot", 96, "Kiev"));
        persons.add(new Person("Kot", 26, "Kiev"));
        persons.add(new Person("Kot", 26, "London"));
        persons.add(new Person("Alex", 28, "London"));
        persons.add(new Person("Krot", 96, "Odessa"));
        persons.add(new Person("Begemot", 15, "London"));
        persons.add(new Person("Igorek", 13, "Kiev"));
        LOG.info("List was created");
        LOG.debug("List elements: {} ", persons.toString());

        //кол-во людей с именем кот
        long count = persons.stream()
                .filter(p -> p.getName().equals("Kot"))
                .count();
        LOG.info("Kol-vo Kotov: {} ", count);

        //средний возраст
        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        LOG.info("Average age of all people: {}", averageAge);

        //самый старый
        persons.stream()
                .max(comparingInt(Person::getAge))
                .ifPresent(p -> LOG.info("The oldest man is {} ", p.getName()));

        //коллекция возраст - список людей
        Map<Integer, List<Person>> personOnAge = persons.stream()
                .collect(groupingBy(Person::getAge));
        LOG.debug("age-list of people: {} ", personOnAge.entrySet().toString());

        //средний возраст по городу
        Map<String, Double> averageAgeOfCity = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .collect(groupingBy(Person::getCity, averagingDouble(Person::getAge)));
        LOG.info("Average age per city: {}", averageAgeOfCity);

        //мегаполис
        persons.stream()
                .collect(groupingBy(Person::getCity, counting()))
                .entrySet().stream()
                .max(comparingByValue())
                .ifPresent(p -> LOG.info("Megapolis is: {}", p.getKey()));
    }
}

