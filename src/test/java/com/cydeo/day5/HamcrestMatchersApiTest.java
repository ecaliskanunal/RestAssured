package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
    @DisplayName("One Spartan with hamcrest and chaining")
    @Test
    public void test1() {
        //This sends the request, gets the response and does the verification
        given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when()
                .get("http://34.239.102.62:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(15),
                        "name", equalTo("Meta"),
                        "gender", is(equalTo("Female")));
    }

    @DisplayName("Cydeo Api Training reacher request with chaining and matchers")
    @Test
    public void test2() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
        .when()
                .get("https://api.training.cydeo.com/teacher/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .header("Content-Length", is(null))
                .and()
                .header("Date", notNullValue()) //check the existence of Date in the header
                .and().assertThat()
                .body("teachers[8].firstName", is("Ron"))
                .body("teachers[8].lastName", is("Tona"))
                .body("teachers[8].gender", equalTo("male"));
    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void test3() {

        given()
                .accept(ContentType.JSON)
        .when()
                .get("https://api.training.cydeo.com/teacher/all")
                .then()
        .statusCode(200)
                .and()
                .body("teachers.firstName", hasItems("Tory", "Port"));
        //check if we have these names in the body or not
    }


}
