package com.testcase;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;

public class ForeignExchangeAPI {

    @BeforeEach
    public void setup(){
        baseURI = "https://api.exchangeratesapi.io/v1/";
    }
    @Test
    @Description("Test case for foreign exchange rates -owner: iffhrzl")
    public void ForeignExchangeRate(){
        given()
                .when()
                .get("/latest/?access_key=fce49b9b39569fea9e7d855659cd3700&base=USD&symbols=GBP")
                .then()
//                .statusCode(200)
                .body("status", equalTo("success"));
    }
}
