package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        System.out.println("The Title Of ID 94000 : " + response.extract().path("title"));
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        System.out.println("The Total Number Of Records : " + response.extract().path("size()"));
    }

    //3. Extract the body of 3rd record
    @Test
    public void test003() {
        System.out.println("The Response Body Of Record Number 3 : " + response.extract().path("[2].body"));
    }

    //Extract the user_id of all the records5. Extract the title of all the records
    @Test
    public void test004() {
        System.out.println("The User IDs Of All Records : " + response.extract().path("user_id"));
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        System.out.println("All Titles From The Response Body : " + response.extract().path("title"));
    }

    // 6. Extract the title of all records whose user_id = 5914254
    @Test
    public void test006() {
        System.out.println("The Response Body Of Record Having ID As 5914254 : " + response.extract().path("findAll{it.user_id == 5914254}.title"));
    }

    //7. Extract the body of all records whose id = 93999
    @Test
    public void test007() {
        System.out.println("The Response Body Of Record Having ID As 93999 : " + response.extract().path("findAll{it.id == 93999}.body"));
    }
}
