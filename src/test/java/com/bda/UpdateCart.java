package com.bda;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateCart {
       @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    private String generateRequestBody() {
        return "{\n" +
                "  \"userId\": 5,\n" +
                "  \"date\": \"2024-12-10\",\n" +
                "  \"products\": [\n" +
                "    { \"productId\": 1, \"quantity\": 3 },\n" +
                "    { \"productId\": 2, \"quantity\": 2 }\n" +
                "  ]\n" +
                "}";
    }

    @Test
    public void testUpdateCart() {
        System.out.println("================================");
        System.out.println("Update Cart");
        String requestBody = generateRequestBody();

        int CartId = 7;

        // Send PUT request
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("/carts/" + CartId);

        // Log response
        // System.out.println("Response: " + response.prettyPrint());

        // Validate status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);

        // Validate response body
        Assert.assertNotNull(response.jsonPath().get("id"), "Cart ID should not be null");
        System.out.println("Assertion passed: Cart ID is not null");

        Assert.assertNotNull(response.jsonPath().get("date"), "Date should not be null");
        System.out.println("Assertion passed: Date is not null");

        Assert.assertEquals(response.jsonPath().getInt("userId"), 5, "User ID mismatch");
        System.out.println("Assertion passed: User ID matches");

        Assert.assertTrue(response.jsonPath().getList("products").size() > 0, "Products list should not be empty");
        System.out.println("Assertion passed: Products list is not empty");

    }
}
