package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        baseURI = "http://34.239.102.62:8000";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        //We can use this line of code without filler words/syntactic sugar
        //Response response = given().accept(ContentType.JSON)
        //        .pathParam("id", 5)
        //        .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify the name blythe
        assertTrue(response.body().asString().contains("Blythe"));
    }

    @DisplayName("GET request /api/spartans/{id}")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 404)
                .when().get("/api/spartans/{id}");
        assertTrue(response.statusCode() == 404);
        assertTrue(response.contentType().equals("application/json"));
        assertTrue(response.body().asString().contains("Not Found"));
    }


    @DisplayName("GET request /api/spartans/search with Query params")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/api/spartans/search");
        assertTrue(response.statusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @DisplayName("GET request /api/spartans/search with Query params (MAP)")
    @Test
    public void test4() {
        //Create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        //Instead of this:
        //Response response = given().accept(ContentType.JSON)
        //        .and().queryParam("nameContains", "e")
        //        .and().queryParam("gender", "Female")
        //        .when().get("/api/spartans/search");

        //You can use queryParamS for MAP   //.log().all() will give you all the details of your request
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");
        assertTrue(response.statusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        //"Female" should be in body response
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }
}
