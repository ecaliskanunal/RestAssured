package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestingWithPath extends HRTestBase {
    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");
        //response.prettyPrint();

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print first country id
        System.out.println("first country id = " + response.path("items[0].country_id"));

        //print second country name
        System.out.println("second country name = " + response.path("items[1].country_name"));

        //print href with in links http://34.239.102.62:1000/ords/hr/countries/US
        System.out.println("response.path(\"item[2].links.href\") = " + response.path("items[2].links[0].href"));

        //get all country names
        List<String> countries = response.path("items.country_name");
        System.out.println("response.path(\"name\") = " + response.path("items.country_name"));
        System.out.println("countries = " + countries);

        //if you pass just the name for an array, it will give you the whole list as a List<E>
        //if you pass an index with it, you can get the specific one
        String firstCountryName = response.path("items[0].country_name");
        System.out.println("firstCountryName = " + firstCountryName);

        //EVEN IF you have one value in an array, you need to give an index, or else you'll get an arraylist

        //assert that all region_id is 2
        List<Integer> allRegionIDs = response.path("items.region_id");
        System.out.println("allRegionIDs = " + allRegionIDs);
        for (Integer regionID : allRegionIDs) {
            System.out.println("regionID = " + regionID);
            assertEquals(2, regionID);
        }
    }

    @DisplayName("GET request to job_id IT_PROG with Path method")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        //response.prettyPrint();

        //make sure we have only IT_PROG as a job_id
        List<String> jobIDs = response.path("items.job_id");
        System.out.println("jobIDs = " + jobIDs);
        int number = 1;
        for (String jobID : jobIDs) {
            System.out.println("jobID " + number + "= " + jobID);
            assertEquals("IT_PROG", jobID);
            number++;
        }

        //print each name of IT_PROG
        Map<String, String> fullNames = new HashMap<>();
        for (int i = 0; i < jobIDs.size(); i++) {
            fullNames.put(response.path("items[" + i + "].first_name"), response.path("items[" + i + "].last_name"));
        }
        for (Map.Entry<String, String> fullName : fullNames.entrySet()) {
            System.out.println("fullName = " + fullName.getKey()+ " " + fullName.getValue());
        }

    }
}
