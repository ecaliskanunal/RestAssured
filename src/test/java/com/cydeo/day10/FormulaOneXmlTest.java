package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FormulaOneXmlTest {
   @BeforeAll
    public static void setUp() {
        baseURI = "http://ergast.com";
        basePath = "/api/f1";
    }

    @Test
    public void test1() {
        Response response = given()
                .pathParam("driver", "alonso")
                .when()
                .get("/drivers/{driver}")
                .then()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("Given Name = " + givenName);

        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("Family Name = " + familyName);

        String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("Driver ID = " + driverId);

        String code = xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println("Code = " + code);

        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println("URL = " + url);
    }
}
