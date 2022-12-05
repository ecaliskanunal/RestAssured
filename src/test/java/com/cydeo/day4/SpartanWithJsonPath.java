package com.cydeo.day4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanWithJsonPath extends SpartanTestBase {
    @DisplayName("GET one spartan with JSON Path")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        //As this response object will give us one value, it is not an array
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());

        //Assigning response to JSONpath - a custom class to save your response
        JsonPath jsonPath = response.jsonPath();
        int id =jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        System.out.println("get the json as String = " + jsonPath.get().toString());

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);

    }

}
