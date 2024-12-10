package com.bda;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetCartwithDate {
     @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://fakestoreapi.com";        
    }

    @Test
    public void testGetCartwithDate() {
        System.out.println("================================");
        System.out.println("Get Cart with Date");

        String startdate = "2019-12-10";
        String enddate = "2020-10-10";
          Response response = RestAssured.given()
            .queryParam("startdate", startdate)
            .queryParam("enddate", enddate)
            .when()
            .get("/carts");
        // System.out.println("Response: " + response.prettyPrint());
        
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
        System.out.println("Assertion passed: Status code is 200");
        

         // Mengambil daftar carts dari respons
        List<Map<String, Object>> carts = response.jsonPath().getList("$");
        System.out.println("Response: " + carts);

        // Memverifikasi bahwa setiap cart berada dalam rentang tanggal
        carts.forEach(cart -> {
            String date = (String) cart.get("date");
            Assert.assertTrue(isDateInRange(date, startdate, enddate),
                "Cart dengan tanggal " + date + " berada di luar rentang tanggal!");
        });
    }   

    private boolean isDateInRange(String date, String startDate, String endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }
}
