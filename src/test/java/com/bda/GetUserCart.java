package com.bda;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserCart {
    
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://fakestoreapi.com";        
    }

    @Test
    public void testGetSingleCart() {
        System.out.println("================================");
        System.out.println("Get User Cart");
        int userId = 2;
        Response response = RestAssured.given().contentType(ContentType.JSON).get("/carts/user/" + userId);
        System.out.println("Response: " + response.prettyPrint());
        
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
        System.out.println("Assertion passed: Status code is 200");

        List<Integer> userIds = response.jsonPath().getList("userId");
        Assert.assertEquals(userIds.get(0).intValue(), userId, "User ID mismatch");
        System.out.println("User ID in response: " + userIds.get(0));

    }
}
