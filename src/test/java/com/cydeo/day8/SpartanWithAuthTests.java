package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(401)
                .log().all();
    }

    @DisplayName("GET /api/spartans as an admin expect 200")
    @Test
    public void test2() {
        given()

                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();
    }

    @DisplayName("DELETE /spartans/{id} as editor expect 403")
    @Test
    public void tesEditorDelete() {
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403) //editor cannot delete, authorized user but not authorized to do it
                .log().body();  //prints only body
    }
}
