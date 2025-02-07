package org.sahni;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AppStatusTest {
    @Test
    void testAppStatusEndpoint() {
        given()
          .when().get("/app_status")
          .then()
             .statusCode(200)
             .body(is("All Okay."));
    }

}