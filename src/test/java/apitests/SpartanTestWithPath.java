package apitests;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashMap;
import java.util.List;
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

    @Test
    public void getAllSpartansWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        Assert.assertEquals(response.statusCode(), 200);
        //Verify content-type is "application/json"
        //Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");

        // information of the first person in the json file
        int firstId =  response.path("id[1]");
        String firstName = response.path("name[1]");
        System.out.println("firstId = " + firstId);
        System.out.println("firstName = " + firstName);
        // information of the last person in the json file
        int lastId =  response.path("id[-1]");
        String lastFirstName = response.path("name[-1]");
        System.out.println("lastId = " + lastId);
        System.out.println("lastName = " + lastFirstName);

        // print all names in the jason file
        List<String> names = response.path("name");// it return all names as string list
        System.out.println("names = " + names);
        
        // print all phone numbers
        List<Object> phones = response.path("phone");
        for (Object phone : phones) {
            System.out.println("phone = " + phone);
        }

    }







}
