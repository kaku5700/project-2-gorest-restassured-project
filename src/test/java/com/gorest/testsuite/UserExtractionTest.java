package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

        static ValidatableResponse response;

        @BeforeClass
        public static void inIt() {
            RestAssured.baseURI = "https://gorest.co.in";
            response = given()
                    .when()
                    .get("/public/v2/users?page=1&per_page=20")
                    .then().statusCode(200);
        }

        //1. Extract the All Ids
        @Test
        public void test001() { // Test to extract all IDs
            List<?> allIds = response.extract().path("id");
            System.out.println("The List Of All IDs : " + allIds);
        }

        //2. Extract the all Names
        @Test
        public void test002() {
            List<String> allNames = response.extract().path("name");
            System.out.println("The List Of All Names : " + allNames);
        }

        //3. Extract the name of 5th object
        @Test
        public void test003() {
            String name = response.extract().path("name[4]");
            System.out.println("The Name On The 5th Object : " + name);
        }

        //4. Extract the names of all object whose status = inactive
        @Test
        public void test004() {
            List<String> allNamesWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.name");
            System.out.println("The List Of Names Having Inactive Status : " + allNamesWithStatusInactive);
        }

        //5. Extract the gender of all the object whose status = active
        @Test
        public void test005() {
            List<String> allGendersWithStatusActive = response.extract().path("findAll{it.status == 'active'}.gender");
            System.out.println("The List Of All Genders Having Active Status : " + allGendersWithStatusActive);
        }

        //6. Print the names of the object whose gender = female
        @Test
        public void test006() {
            List<String> allNamesWithGenderFemale = response.extract().path("findAll{it.gender == 'female'}.name");
            System.out.println("The List Of All Names Having Female Gender : " + allNamesWithGenderFemale);
        }

        //7. Get all the emails of the object where status = inactive.
        @Test
        public void test007() {
            List<String> allEmailsWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.email");
            System.out.println("The List Of Emails having Inactive Status : " + allEmailsWithStatusInactive);
        }

        //8. Get the ids of the object where gender = male
        @Test
        public void test008() {
            List<?> allIdsWithMaleGender = response.extract().path("findAll{it.gender == 'male'}.id");
            System.out.println("The List Of All IDs Having Male Gender : " + allIdsWithMaleGender);
        }

        //9. Get all the status
        @Test
        public void test009() {
            List<String> allStatuses = response.extract().path("status");
            System.out.println("The List Of All Statuses : " + allStatuses);
        }


        //10. Get email of the object where name = Abani Butt
        @Test
        public void test010() {
            System.out.println("The Email Of Abani Butt : " + response.extract().path("findAll{it.name == 'Abani Butt'}.email"));
        }

        //11. Get gender of id = 5914144
        @Test
        public void test011() {
            System.out.println("The Gender Of ID 5914144 : " + response.extract().path("findAll{it.id == 5914144}.gender"));
        }

    }
