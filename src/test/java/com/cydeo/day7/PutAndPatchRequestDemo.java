package com.cydeo.day7;

import com.cydeo.day6.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {
    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request, we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Rose Geller");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 12345678900L);

        given().contentType("application/json")
                .body(putRequestMap)                // this map will be serialized
                .pathParam("id", 104)
                .when().put("/api/spartans/{id} ")
                .then().statusCode(204);

        Spartan spartanUpdated = given()
                .pathParam("id", 104)
                .when().get("/api/spartans/{id} ")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        System.out.println("spartanUpdated.getPhone() = " + spartanUpdated.getPhone());

        assertThat(putRequestMap.get("phone"), is(spartanUpdated.getPhone()));

    }


    @DisplayName("PATCH request to one spartan for update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request, we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("phone", 12345678901L);

        given().contentType("application/json")
                .body(putRequestMap).log().body()             // this map will be serialized
                .pathParam("id", 104)
                .when().patch("/api/spartans/{id} ") //dont forget to update request, make it PATCH
                .then().statusCode(204);

        Spartan spartanUpdated = given()
                .pathParam("id", 104)
                .when().get("/api/spartans/{id} ")
                .then().statusCode(200)
                .extract().as(Spartan.class);

        System.out.println("spartanUpdated.getPhone() = " + spartanUpdated.getPhone());

        assertThat(putRequestMap.get("phone"), is(spartanUpdated.getPhone()));

    }

    @DisplayName("DELETE request to one spartan")
    @Test
    public void DELETERequest() {
        given()
                .pathParam("id", 121)
                .when().delete("/api/spartans/{id} ")//dont forget to change the request word to DELETE
                .then().statusCode(204);
    }
}
