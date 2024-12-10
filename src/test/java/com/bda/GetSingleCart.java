package com.bda;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
// import io.restassured.response.ValidatableResponse;
// import io.restassured.specification.RequestSpecification;

public class GetSingleCart {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://fakestoreapi.com";        
    }

    @Test
    public void testGetSingleCart() {
        System.out.println("================================");
        System.out.println("Get Single Cart");
        int cartId = 2;
        Response response = RestAssured.given().contentType(ContentType.JSON).get("/carts/" + cartId);
        // System.out.println("Response: " + response.prettyPrint());
        
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
        System.out.println("Assertion passed: Status code is 200");

        Assert.assertEquals(response.jsonPath().getInt("id"), cartId, "Cart ID missmatch");
        System.out.println("Assertion passed: Cart ID is Expected");
    }
}