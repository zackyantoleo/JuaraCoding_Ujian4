package com.bda;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LimitResult {
     @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://fakestoreapi.com";        
    }

    @Test
    public void testGetLimitResult() {
        System.out.println("================================");
        System.out.println("Limit Result");

        int limit = 2;
          Response response = RestAssured.given()
            .queryParam("limit", limit)
            .when()
            .get("/carts");
        // System.out.println("Response: " + response.prettyPrint());
        
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
        System.out.println("Assertion passed: Status code is 200");

        int resultSize = response.jsonPath().getList("$").size();
        Assert.assertEquals(resultSize, limit, "Jumlah hasil tidak sesuai dengan limit!");
        System.out.println("Assertion passed: Total Data is match with expected");
    }
    
}
