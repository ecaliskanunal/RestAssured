package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to employees IT_PROG and chaining")
    @Test
    public void test1() {

        List<String> names = Arrays.asList("Ali", "Veli");
        given()
                .accept(ContentType.JSON)
        .when()
                .queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
                .get("/employees")
        .then()
                .statusCode(200)
                .and()
                .body("items.job_id", everyItem (is(equalTo("IT_PROG"))))
                //everyItem is like for each loop, it checks each item for the condition
                .body("items.first_name", containsInRelativeOrder("Ali", "Veli"))
                .body("items.email", containsInAnyOrder("dee"))
                .body("items.first_name", equalToObject(names));
    }

    @Test
    public void test2() {
        //We want to chain and also get response object

        List<String> names = Arrays.asList("Ali", "Veli");

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .extract().response();

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .extract().jsonPath();

        List<String> namesList = given()
                .accept(ContentType.JSON)
                .when()
                .queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .extract().response().jsonPath().getList("content.name");

        int statusCode = given()
                .accept(ContentType.JSON)
                .when()
                .queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .extract().statusCode();

        //Extract method gives whatever you want in whatever format depending on what you choose after extract

        System.out.println("namesList = " + namesList);
        System.out.println("statusCode = " + statusCode);

        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

        //assert that firstnames are in order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Ali", "Veli"));
    }

}
