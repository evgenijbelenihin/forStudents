package test.java;

import io.restassured.http.ContentType;
import main.java.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSum() {
        int firstNumber = 31;
        int secondNumber = 13;
        Calculator calculator = new Calculator();
        int actualResult = calculator.getSum(firstNumber, secondNumber);
        Assert.assertEquals("Error in calculating", 44, actualResult);
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
    public void getMultiple() {
    }
}