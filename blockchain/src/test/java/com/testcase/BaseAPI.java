package com.testcase;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BaseAPI {
    @BeforeEach
    public void setup() {
        baseURI = "https://api.geckoterminal.com/api/v2";
    }

    @Test
    @Description("Test case for getting ETH pools -owner: iffhrzl")
    public void testGetEthPools() {
        given()
                .when()
                .get("/networks/eth/pools")
                .then()
                .statusCode(200)
                .body("data", not(empty()))
                .body("data[0].id", notNullValue())
                .body("data[0].attributes.name", notNullValue());
    }

    @Test
    @Description("Test case for invalid network -owner: iffhrzl")
    public void testInvalidNetwork() {
        given()
                .when()
                .get("/networks/fakenet/pools")
                .then()
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    @Description("Test case for pool pagination -owner: iffhrzl")
    public void testPoolPagination() {
        given()
                .queryParam("page", 2)
                .when()
                .get("/networks/eth/pools")
                .then()
                .statusCode(200)
                .body("meta.page", equalTo(2));
    }
}
