package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify if the total record is 20
    @Test
    public void test1() {
        response.body("size()", equalTo(20));
    }

    //2. Verify  if the name of id = 7015089 is equal to ”Dev Tagore”
    @Test
    public void test2() {
        response.body("[0].name", equalTo("Dev Tagore"));
    }

    //3. Check the single ‘Name’ in the Array list (Somu Arora)
    @Test
    public void test3() {
        response.body("[1].name", equalTo("Somu Arora"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dev Tagore, Somu Arora, Oormila Shah )
    @Test
    public void test4() {
        response.body("name", hasItems("Dev Tagore", "Somu Arora", "Oormila Shah"));

    }

    //5. Verify the email of userid =  7015089 is equal “dev_tagore@kessler-lebsack.test”
    @Test
    public void test5() {
        response.body("[0].email", equalTo("dev_tagore@kessler-lebsack.test"));

    }

    //6. Verify the status is “Active” of user name is “Enakshi Gowda”
    @Test
    public void test6() {
        response.body("[5].status", equalTo("active"));


    }

    //7. Verify the Gender = male of user name is “Dev Tagore”
    @Test
    public void test7() {
        response.body("[0].gender", equalTo("male"));

    }

}