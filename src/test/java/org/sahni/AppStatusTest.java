package org.sahni;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AppStatusTest {
    @Test
    @DisplayName("test app status endpoint for 200")
    void testAppStatusEndpoint() {
        given()
          .when().get("/app_status")
          .then()
             .statusCode(200)
             .body(is("All Okay."));
    }

}