package io.quarkus.stocks;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
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