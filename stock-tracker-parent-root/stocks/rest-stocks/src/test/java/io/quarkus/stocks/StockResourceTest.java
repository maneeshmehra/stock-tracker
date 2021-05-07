package io.quarkus.stocks;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StockResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/stocks/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}