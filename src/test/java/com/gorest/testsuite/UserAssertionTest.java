package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    // 1. Verify the if the total record is 10
    @Test
    public void test001() { // Test to verify that total number of records is 10
        response.body(("size()"), equalTo(20));
    }

    // 2. Verify the if the name of id = 5914155 is equal to ”Bhishma Josh”
    @Test
    public void test002() {
        response.body("findAll{it.id == 5914144}.name", hasItem("Manikya Asan MD"));
    }

   //3. Check the single ‘Name’ in the Array list (Dandapaani Agarwal)
    @Test
    public void test003() {
        response.body("findAll{it.id == 5914122}.name", hasItem("Ekaksh Asan"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004() {
        response.body("name", hasItems("Manikya Asan MD", "Prof. Chakrika Embranthiri", "Himadri Banerjee"));
    }

    //5. Verify the emai of userid = 5914155 is equal “joshi_bhishma@huel-parker.test”
    @Test
    public void test005() {
        response.body("findAll{it.id == 5914144}.email", hasItem("manikya_asan_md@price.test"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test006() {
        response.body("findAll{it.name == 'Asha Pandey'}.status", hasItem("active"));
    }

    //7. Verify the Gender = male of user name is “Aanjaneya Iyer”
    @Test
    public void test007() {
        response.body("findAll{it.name == 'Akula Kakkar'}.gender", hasItem("male"));
    }
}
