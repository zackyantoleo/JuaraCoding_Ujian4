package com.bda;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

public class SortResult {
    
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";    
    }

    @Test
    public void testSortResult(){
          // Parameter untuk sorting (ascending)
        String sortDirection = "asc";

        // Mengirim request GET /carts?sort={sort}
        Response response = RestAssured.given()
            .queryParam("sort", sortDirection)
            .when()
            .get("/carts");

        // Menampilkan respons (opsional untuk debugging)
        response.prettyPrint();

        // Verifikasi status code
        Assert.assertEquals(response.statusCode(), 200, "Status code tidak sesuai!");

        // Mengambil daftar ID dari hasil untuk memverifikasi sorting
        List<Integer> cartIds = response.jsonPath().getList("id", Integer.class);

        // Verifikasi bahwa ID sudah terurut dengan benar
        for (int i = 1; i < cartIds.size(); i++) {
            Assert.assertTrue(cartIds.get(i) >= cartIds.get(i - 1), 
                "Hasil tidak terurut secara ascending!");
        }
    }

}
