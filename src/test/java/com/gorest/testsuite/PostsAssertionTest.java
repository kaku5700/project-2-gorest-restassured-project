package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

//    1. Verify the if the total record is 25
//            2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
//    cohaero libero.”
//            3. Check the single user_id in the Array list (5914249)
//4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
//5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
//    Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
//    vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
//    tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
//    acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”

   //1. Verify the if the total record is 25
    @Test
    public void test001() { // Test to verify the total number of records displayed on a page
        response.body(("size()"), equalTo(25));
    }

    //2.Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti cohaero libero."
    @Test
    public void test002() {
        response.body("findAll{it.id == 93997}.title", hasItem("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003() { // Test to verify that a particular ID exists or not
        response.body("findAll{it}.user_id", hasItem(5914249));
    }

    //4. Check the multiple ids in the ArrayList (5914254, 5914253, 5914251)
    @Test
    public void test004() {
        response.body("findAll{it}.user_id", hasItems(5914254, 5914253, 5914251));
    }

    //5. Verify the body of userid = 5914152 is equal to Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus.""
    @Test
    public void test005() {
        response.body("findAll{it.user_id == 5914254}.body", hasItem("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
    }
}
