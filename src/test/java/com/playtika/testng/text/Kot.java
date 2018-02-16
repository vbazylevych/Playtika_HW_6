package com.playtika.testng.text;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class Kot {
    @Test
    public void PROSTOI_PUT() {
        JsonPath responseJson = given()
                .when()
                .get("https://swapi.co/api/people")
                .then()
                .extract().response().jsonPath();

        List<HashMap> persons = responseJson.getList("results");

        assertThat(responseJson.get("count"), is(87));
        assertTrue(personFromPlanetIsPresent(persons, "Luke Skywalker", "Tatooine"));
        assertThat(persons.get(0).get("name"), is("Luke Skywalker"));
        assertThat(persons.get(1).get("name"), is("C-3PO"));
        assertThat(persons.get(2).get("name"), is("R2-D2"));
    }

    @Test
    public void SLOJNII_PUT() {

        JsonPath initialJsonPath = given()
                .when()
                .get("https://swapi.co/api/people")
                .then()
                .extract().response().jsonPath();

        String next = initialJsonPath.getString("next");
        int size = initialJsonPath.getList("results").size();

        while (next != null) {
            JsonPath jsonPath = given()
                    .when()
                    .get(next)
                    .then()
                    .extract().response().jsonPath();
            int newSize = jsonPath.getList("results").size();
            size = size + newSize;
            next = jsonPath.getString("next");
        }

        Assert.assertThat(size, is(87));
    }

    private boolean personFromPlanetIsPresent(List<HashMap> persons, String personName, String planet) {
        return persons
                .stream()
                .filter((p) -> (p.get("name").equals(personName)) && (getPlanetName(p.get("homeworld")).equals(planet)))
                .findFirst().isPresent();
    }

    private String getPlanetName(Object url) {

        return given()
                .when()
                .get(url.toString())
                .then()
                .extract().response().jsonPath().getString("name");
    }
}
