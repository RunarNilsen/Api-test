package POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /*TEST CASE
    Given content-type and accept-type is JSON
    And request json body is
           {
            "gender": "Male",
            "name": "Mike",
            "phone": 1231232133
                }
    When user sends POST request to "/api/spartans"
    Then status code is 201
    And content-type should be application/json
    And json payload/response should include:
            "A Spartan is Born!" message
    And same json data what is posted
     */

    @Test
    public void PostNewSpartan(){
        // 1st way of sending json as a post is to write json as a string and add it inside the body
        String jsonBody = "{\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"name\": \"Mike\",\n" +
                "            \"phone\": 1231232133\n" +
                "                }";
        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        // verify status code is 201
        assertEquals(response.statusCode(), 201);
        //Verify content type is application/json
        assertEquals(response.contentType(), "application/json");

        // Verify that json payload/response should include: "A Spartan is Born!" message
        String actualMessage = response.path("success");
        String expectedMessage = "A Spartan is Born!";
        assertEquals(actualMessage, expectedMessage);

        // Verify that same json data what was posted, is received as a response
        String actualName = response.path("data.name");
        String actualGender = response.path("data.gender");
        int actualPhone = response.path("data.phone");

        assertEquals(actualName, "Mike");
        assertEquals(actualGender, "Male");
        assertEquals(actualPhone, 1231232133);

    }

    @Test
    public void PostNewSpartan2(){



    }




}
