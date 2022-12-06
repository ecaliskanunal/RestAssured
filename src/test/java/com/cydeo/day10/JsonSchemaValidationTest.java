package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("singleSpartanJsonSchema.json"))
                .log().all();
                // If your json file is under the resources directory, use this method
    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json")));
                // If the file is not under the resources, if it is somewhere else, GIVE the PATH
    }

    @DisplayName("POST one Spartan")
    @Test
    public void test(){
        String requestJsonBody = "{\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"name\": \"Ross Geller\",\n" +
                "    \"phone\" : 1234566788\n" +
                "}";
        System.out.println("requestJsonBody = " + requestJsonBody);
        given()
                .contentType(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .and()
                .body(requestJsonBody)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("spartan PostJsonSchema.json"));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema


}

