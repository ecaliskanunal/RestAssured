package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
//make static import so that you can use all the methods of RestAssured or Assertions directly
//without given the class name

public class HrGetRequests {

    //BeforeAll is an annotation equals to @BeforeClass in testNG, we use it with STATIC method name
    //not equal to Hooks which runs before/after scenario
    //This is valid for one class, we have multiple test cases and this is gonna get executed before everything ONCE
    //@BeforeAll is completely a junit feature, it comes from junit
    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        baseURI = "http://34.239.102.62:1000/ords/hr";
        //ords is REST Api for Oracle Database
    }

    @Test
    public void test1() {
        Response response = get("/regions");
        //Here we don't give the base Url because we provided it to RestAssured.
        //Passing the endpoint is enough
        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2() {
        //make sure that accept type is application/json and user gets request to /regions/2
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/2");

        //make sure that status code is 200
        assertEquals(response.statusCode(), 200);

        //make sure contentType is json
        assertTrue(response.contentType().equals("application/json"));

        response.prettyPrint();

        //verify json body contains "Americas"
        assertTrue(response.body().asString().contains("Americas"));
    }


}
