package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanHamcrest extends SpartanTestBase {

    @DisplayName("GET spartan and chaining")
    @Test
    public void test1() {
        List<String> list = given().accept(ContentType.JSON)
                .queryParam("nameContains", "j",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body("totalElement", is(6))
                .extract().response().jsonPath().getList("content.name");
        //I send my request, verift status code and total num of elements, and get the names a a list

        //save status code
        int statusCode = given().accept(ContentType.JSON)
                .queryParam("nameContains", "j",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body("totalElement", is(6))
                .extract().response().statusCode();
    }


}
