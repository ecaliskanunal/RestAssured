package com.cydeo.day7;

import com.cydeo.day6.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {
    @DisplayName("Spartan Post Request")
    @Test
    public void test1() {
        String requestJsonBody = "{\"gender\":\"Female\",\"name\":\"Elif\",\"phone\":23243243555}";
        Response response = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON) // I am gonna send JSON to you API
                .body(requestJsonBody)         // pass the json into this body method
                .when()
                .post("/api/spartans");   // here we are trying to simulate POST request

        //Same request can be chained with status code and then data type becomes int
        int statusCode = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON) // I am gonna send JSON to you API
                .body(requestJsonBody)         // pass the json into this body method
                .when()
                .post("/api/spartans")
                .statusCode();                 // here we are trying to simulate POST request

        //Same request can be chained with content type and then data type becomes string
        String contentType = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON) // I am gonna send JSON to you API
                .body(requestJsonBody)         // pass the json into this body method
                .when()
                .post("/api/spartans")
                .contentType();

        ValidatableResponse validatableBody = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON) // I am gonna send JSON to you API
                .body(requestJsonBody)         // pass the string json into this body method
                .when()
                .post("/api/spartans")
                .then()
                .assertThat()
                .body(("data.name"), is("Elif"))
                .body(("data.gender"), is("Female"));


        //Verify that
        assertThat(response.statusCode(), is(201));

        // Verify the success message
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        //                          success is the key name in json body

        // Get the name path
        assertThat(response.path("data.name"), is("Elif"));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(23243243555L));
        //assertThat(validatableBody.toString(), is("{\"gender\":\"Female\",\"name\":\"Elif\",\"phone\":23243243555}"));


        //is method from hamcrest matchers, assertThat method is from hamcrest MatcherAssert
        /*
            {
                "success": "A Spartan is Born!",
                "data": {
                    "id": 107,
                    "name": "Elif",
                    "gender": "Female",
                    "phone": 23243243555
                }
            }
         */
    }

    @DisplayName("Spartan Post Request with MAP to JSON")
    @Test
    public void test2() {

        //create a map to keep request json body information
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        //then I create my data here and convert it to json so that I can send it as a post request
        requestJsonMap.put("name", "Elif");
        requestJsonMap.put("gender", "Female");
        requestJsonMap.put("phone", 93742384089L);

        //Here, serialization needs to be done, which will be automaticall done by the body() method
        Response response = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON)              // I am gonna send JSON to you API
                .body(requestJsonMap).log().all()           // pass the JAVA map into this body method
                .when()
                .post("/api/spartans");                // here we are trying to simulate POST request

        // Verify the success message
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        //                          success is the key name in json body

        // Get the name path
        assertThat(response.path("data.name"), is("Elif"));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(93742384089L));

        response.prettyPrint();
    }


    @DisplayName("Spartan Post Request with SPARTAN object to JSON")
    @Test
    public void test3() {

        //Instead of Map, we can create a spartan object and body() method is gonna serialize it too
        Spartan spartan = new Spartan();
        spartan.setName("Elif");
        spartan.setGender("Female");
        spartan.setPhone(12345678900L);

        Response response = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON)              // I am gonna send JSON to you API
                .body(spartan).log().all()                  // pass the json into this body method
                .when()
                .post("/api/spartans");                // here we are trying to simulate POST request

        // Verify the success message
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        //                          success is the key name in json body

        // Get the name path
        assertThat(response.path("data.name"), is("Elif"));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(12345678900L));

        response.prettyPrint();
    }

    @Test
    public void test4() {
        /* In this example:
            - we implement serialization with creating spartan object and sending it as a post request body
            - we also implement deserialization getting the id, sending get request and saving that body as a response
        */

        //Create a spartan POJO class object and send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("ECU");
        spartan.setGender("Female");
        spartan.setPhone(12345678900L);

        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON) // Could you please send me JSON? I want JSON
                .and()
                .contentType(ContentType.JSON)              // I am gonna send JSON to you API
                .body(spartan)                              // pass the json into this body method
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);

        //as id is gonna be dynamic, send a request to id - this is to get info about what you have created
        Spartan spartanDynamic = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().as(Spartan.class);

        assertThat(spartanDynamic.getName(), is(spartan.getName()));
        assertThat(spartanDynamic.getGender(), is(spartan.getGender()));
        assertThat(spartanDynamic.getPhone(), is(spartan.getPhone()));
        assertThat(spartanDynamic.getId(), is(idFromPost));

    }
}