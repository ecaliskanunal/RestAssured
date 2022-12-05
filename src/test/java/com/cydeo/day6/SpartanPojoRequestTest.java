package com.cydeo.day6;

import com.cydeo.day6.pojo.Search;
import com.cydeo.day6.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPojoRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();
        
        //Deserialize WAY 1: from JSON to POJO (Java custom class) with the help of jackson
        //using as() method: as() method uses jackson to deserialize
        //We use Spartan.class to convert it to Spartan object
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println("spartan15 = " + spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getName() = " + spartan15.getName());

        //Deserialize WAY 2:
        //using JSON path
        JsonPath jsonPath = response.jsonPath();
        Spartan s15 = jsonPath.getObject("", Spartan.class);
        System.out.println(s15);
        System.out.println("s15.getId() = " + s15.getId());

        //get the first spartan from content array in json object
        JsonPath jsonPath1 = given().accept(ContentType.JSON)
                .and().queryParam("namesContains", "a", "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //put jsonPath inside spartan object
        Spartan s1 = jsonPath1.getObject("content[0]", Spartan.class);
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
    }

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());
        //If we had response.as instead jsonPath.getObject, we would need to create another class
        //holding for content array in our json object (Search pojo class). Then, I would need to
        //user get method to reach the first ing the list, then the getters and setters
    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        //another way of deserialization
        List<Spartan> spartanList = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("items", Spartan.class);

        System.out.println(spartanList.get(1).getName());

    }

}
