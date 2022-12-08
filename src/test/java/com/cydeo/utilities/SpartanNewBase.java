package com.cydeo.utilities;

import io.restassured.filter.log.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification userSpec;
    public static RequestSpecification adminSpec;

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        //baseURI = "http://34.239.102.62:7000"; da yazabilirdim topluca
        baseURI = "http://34.239.102.62";
        port = 7000;
        basePath ="/api"; // what is common for all endpoints

        requestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);  //logging with response specification
    }

    @AfterAll
    public static void close(){
        // Reset the info we set above, method comes from RestAssured
        // Reset method resets everything to default
        reset();
    }
}