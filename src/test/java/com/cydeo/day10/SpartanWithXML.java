package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanAuthTestBase {
    @DisplayName("GET request to /api/spartans and verify XML")
    @Test
    public void getSpartanXML() {
        //ask for xml repsonse
        //assert status code 200
        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("MAle"))
                .log().all();
    }

    @DisplayName("GET request to /api/spartans with xmlPath")
    @Test
    public void testXmlPath() {
        Response response = given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        //get response xml body/payload and save it inside the xmlPath object
        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        int id = xmlPath.getInt("List.item[0].id");
        System.out.println("id = " + id);

        //get ALL names and save into list of String
        List<String> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);
        

    }

}
