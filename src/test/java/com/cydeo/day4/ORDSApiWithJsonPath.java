package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries")
    @Test
    public void test1() {
        //Short way without specifying body content type
        Response response = get("/countries");

        //use jsonPath object to navigate 
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids with GETLIST
        List<String> allCountries = jsonPath.getList("items.country_name");
        System.out.println("allCountries = " + allCountries);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionId2 = " + countryNameWithRegionId2);
    }

    @DisplayName("GET request to countries")
    @Test
    public void test2() {
        //Short way without specifying body content type
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        //use jsonPath object to navigate
        JsonPath jsonPath = response.jsonPath();

        //get me all emails of IT_PROG employees
        List<String> emailsOfIT = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(emailsOfIT);

        //get me first names of who are making more than 10k
        List<String> empNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("empNames = " + empNames);

        //get the max salary first name with JsonPath
        String maxSalaryPerson = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("maxSalaryPerson = " + maxSalaryPerson);
        //get the max salary first name with .path()
        String kingSalary = response.path("items.max {it.salary}.first_name");
        System.out.println("kingSalary = " + kingSalary);
    }
}
