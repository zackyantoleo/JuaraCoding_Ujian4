package com.bda;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
// import io.restassured.response.ValidatableResponse;
// import io.restassured.specification.RequestSpecification;

public class GetAllCart {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://fakestoreapi.com";        
    }

    @Test
    public void testGetAllCart() {
        System.out.println("================================");
        System.out.println("Get all cart");
        Response response = RestAssured.given().contentType(ContentType.JSON).get("/carts");
        // System.out.println("Response: " + response.prettyPrint());
        
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
        System.out.println("Assertion passed: Status code is 200");
    }
}