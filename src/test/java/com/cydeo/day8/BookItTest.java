package com.cydeo.day8;

import com.cydeo.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookItTest {

    @BeforeAll
    public static void init() {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4MiIsImF1ZCI6InN0dWRlbnQtdGVhbS1tZW1iZXIifQ.zIcFXhVng5REMvXmUGrJRSPMp87ImMqxVoM6ofeDpZA";

    @DisplayName("GET all campuses")
    @Test
    public void testAuth1() {
        given().accept(ContentType.JSON)
                .and()
                .header("Authorization", accessToken)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .log().all();
    }
}


