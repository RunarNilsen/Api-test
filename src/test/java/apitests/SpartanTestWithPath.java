package apitests;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically


import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI="http://18.232.145.26:8000";
    }

    /* TEST CASE
     Given accept type is Json
     And path param is 10
     When user sends Get Request to /api/spartans/{id}}
     Then response status code should be 200
     And response content-type: application/json
     And response payload values match the following:
            id is 10,
            name is "Lorenza",
            gender is "Female",
            phone is 3312820936
     */
    @Test
    public void getOneSpartanWithPathParam() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        // Verify response status code should be 404
        Assert.assertEquals(response.statusCode(), 200, "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");

        response.prettyPrint();
        //Printing each key value in the Json of body/ payload
        System.out.println("response.path(\"id\") = " + response.path("id").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"gender\").toString() = " + response.path("gender").toString());
        System.out.println("response.path(\"phone\").toString() = " + response.path("phone").toString());

        // save json key values
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");


        // printing them
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        // Verify one by one
        Assert.assertEquals(id, 10);
        Assert.assertEquals(name, "Lorenza");
        Assert.assertEquals(gender, "Female");
        Assert.assertEquals(phone, 3312820936l); // this number has Long type


    }







}
