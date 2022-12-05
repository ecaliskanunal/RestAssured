package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("GET one spartan and Deserialize to Map")
    @Test
    public void oneSpartanToMap() {

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
        //Here, we get only one row of info, which requires a map.

        //get the json and convert it to the map
        Map<String, Object> jsonMap = response.as(Map.class);

        //After we get the map, we can use hamcrest or junit assertions to do assertion
        System.out.println(jsonMap.toString());
        String actualName = (String) jsonMap.get("name");
        System.out.println("actualName = " + actualName);
        assertThat(actualName, is("Meta"));
    }


    @DisplayName("GET all spartans json to java")
    @Test
    public void getAllSpartans() {
        Response response = RestAssured.get("/api/spartans")
                .then().statusCode(200).extract().response();
        //Here, we get ALL spartans, so we put all the rows' info in a list

        //We need to convert json response to java, which is called deserialization
        List <Map<String, Object>> jsonList = response.as(List.class);
        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String, Object> spartan3 = jsonList.get(2);
        System.out.println("spartan3 = " + spartan3);
    }

}
