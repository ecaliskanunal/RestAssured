package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseUrl = "http://34.226.211.4:8000";
    //your ip

    @Test
    public void test() {
        //Given Accept type application/json
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans"); //When user sends GET request to api/spartans end point

        //Then status code must be 200
        System.out.println("response.statusCode() = " + response.statusCode());

        //And response content type must be application/json
        System.out.println("response.contentType() = " + response.contentType());

        //And response body should include spartan result
        response.prettyPrint();

        //how to do API testing
        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");
    }

    @DisplayName("Get one spartan, /api/spartans/3 and verify")
    @Test
    public void test2(){
        //my class name, then header with .given(), when I send my url
        Response response = RestAssured.given().accept(ContentType.JSON) //my header part first
                .when().get(baseUrl + "/api/spartans/3"); //my get request

        //verify status code 200
        Assertions.assertEquals( 200, response.statusCode());
        //I am expecting to get 200, actual is from response object

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

        //verify json body contains "Fidole"//turn this json file to String and then compare
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

    @DisplayName("Get request to /api/hello")
    @Test
    public void test3(){
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        Assertions.assertTrue(response.statusCode()==200);

        //Is there a Date key here?
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //how to retrieve any header from response using header key
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify length is 17
        Assertions.assertEquals("17", response.header("Content-Length") );

        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.getBody().asString());
    }



}
