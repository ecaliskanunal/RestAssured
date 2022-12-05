package com.cydeo.day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {
    @BeforeAll
    public static void init() {
        //Save base URL inside this variable so that we don't need to type it in each http method
        baseURI = "https://api.training.cydeo.com";
    }

    @DisplayName("GET request to individual student")
    @Test
    public void test1(){
        Response response = given().pathParam("id",23)
                .when().get("/students/{id}");

        //response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertTrue(response.statusCode()==200);

        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/hal+json;charset=UTF-8", response.contentType());

        String contentEncodingText = response.header("Content-Encoding");
        assertEquals("chunked", response.header("transfer-encoding"));

        assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.path(\"firstName\") = " + response.path("firstName"));
        assertEquals("Derick", response.path("firstName"));

    }
//    {
//        "firstName": "Derick",
//            "lastName": "Pacocha",
//            "batch": 19,
//            "joinDate": "05/07/2021",
//            "birthDate": "09/07/1980",
//            "password": "LvThYqsHu3F",
//            "subject": "N/A",
//            "gender": "Male",
//            "admissionNo": "N/A",
//            "major": "Metrics",
//            "section": "N/A",
//            "contact": {
//        "phone": "5719811399",
//                "emailAddress": "cpfeffel9@fema.gov",
//                "permanentAddress": "87537 Kingsford Pass",
//                "_links": {
//            "student": {
//                "href": "https://api.training.cydeo.com/students/23"
//            }
//        }
//    },
//        "company": {
//        "companyName": "Quinu",
//                "title": "Operator",
//                "startDate": "12/9/2019",
//                "address": {
//            "street": "451 8th Plaza",
//                    "city": "Vienna",
//                    "state": "Virginia",
//                    "zipCode": 69807
//        },
//        "_links": {
//            "student": {
//                "href": "https://api.training.cydeo.com/students/23"
//            }
//        }
//    },
//        "_links": {
//        "self": {
//            "href": "https://api.training.cydeo.com/students/23"
//        },
//        "student": {
//            "href": "https://api.training.cydeo.com/students/23"
//        }
//    }
//    }
}
