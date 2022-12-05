package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {

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

        //verify each json key has specific value
        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"gender\").toString() = " + response.path("gender").toString());
        System.out.println("response.path(\"phone\").toString() = " + response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(332342343, phone);
    }

    @DisplayName("GET all spartans and print all names")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        int firstNameInJson = response.path("id[0]");
        System.out.println("firstNameInJson = " + firstNameInJson);

        //Print all names
        List<String> allNames = response.path("name");
        System.out.println(allNames);
        for (int i = 0; i < allNames.size(); i++) {
            System.out.println(allNames.get(i));
        }

    }
}
