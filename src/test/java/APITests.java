package test.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import main.java.data.Statuses;
import main.java.entities.Category;
import main.java.entities.Pet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getInfoAboutDartVader() {
        given().baseUri("https://swapi.dev/api")
                .basePath("people")
                .when()
                .get("4")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .log()
                .all()
                .and()
                .body("name", equalTo("Darth Vader"));
    }

    @Test
    public void addPetToTheStore() {
//        Preparing test data
        Category dogs = new Category(1, "dogs");
        Category patrol = new Category(43, "patrol");

        Pet newPet = new Pet(
                100000 + (long) (Math.random() * 999999),
                dogs,
                "Crazy " + RandomStringUtils.randomAlphabetic(5),
                Collections.singletonList("urls"),
                Arrays.asList(dogs, patrol),
                Statuses.AVAILABLE.name());

//        Tests
        Response responseAddPet = given()
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .body(newPet)
                .post();

        Assert.assertEquals("Wrong status code", 200, responseAddPet.getStatusCode());
        System.out.println("Response for adding a new pet: \n" + responseAddPet.asString() + "\n"); // log info
        Pet addedPet = responseAddPet.as(Pet.class);

        Pet foundPetById = given()
                .basePath("/pet/" + addedPet.getID())
                .accept("application/json")
                .get()
                .as(Pet.class);
        System.out.println("Response for getting pet by Id: \n" + foundPetById.toString()); // log info

        // final assert
        Assert.assertEquals("Something wrong with pet..", addedPet.getName(), foundPetById.getName());

        // удалить своего питомца в конце теста, чтобы не засорять базу
    }

    @Test
    public void validationJSONSchemaOfResponseGetPet() {
        //        Preparing test data
        Category dogs = new Category(1, "dogs");
        Category patrol = new Category(43, "patrol");

        Pet newPet = new Pet(
                100000 + (long) (Math.random() * 999999),
                dogs,
                "Crazy " + RandomStringUtils.randomAlphabetic(5),
                Collections.singletonList("urls"),
                Arrays.asList(dogs, patrol),
                Statuses.AVAILABLE.name());

//        Tests
        Pet responseAddPet = given()
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .body(newPet)
                .post()
                .as(Pet.class);

        given()
                .basePath("/pet/" + responseAddPet.getID())
                .accept("application/json")
                .get()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("main/java/data/json/schema.json"));
    }
}