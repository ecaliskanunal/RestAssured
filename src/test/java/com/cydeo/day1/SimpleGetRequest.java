package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://34.226.211.4:8000/";

    @Test
    public void test1() {
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);
        //Once we send request with get using RestAssured library, we save it in Response object
        //All the info postman is getting is inside the Response object

        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body
        response.prettyPrint();
    }

}

