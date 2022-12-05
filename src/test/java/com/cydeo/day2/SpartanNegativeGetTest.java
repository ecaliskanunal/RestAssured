package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        baseURI = "http://34.239.102.62:8000";
    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans/10");

        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8", response.contentType());

        //Brings all the headers belonging to RESPONSE
        System.out.println("response.headers() = " + response.headers().asList());
    }



}
