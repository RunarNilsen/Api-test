package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically

public class SpartanTestWithParameters {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI="http://18.232.145.26:8000";
    }

    /* TEST CASE
    Given accept type is JSON
    And Id parameter value is 5
    When user sends Get Request to api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Blythe" should be in response payload
     */
    @Test
    public void getSpartanIdPositivePathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");
        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200 , "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "Blythe" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Blythe"));

    }

    /* NEGATIVE TEST CASE
    Given accept type is Json
    And Id parameter value is 500
    When user sends Get Request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json
    And "Not Found" message should be in response payload

     */
    @Test
    public void getSpartanIdNegativePathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");
        // Verify response status code should be 404
        Assert.assertEquals(response.statusCode(), 404 , "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "Not Found" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Not Found"));

    }

    /* TEST CASE for Query parameters
    Given accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|e
    When user sends Get Request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void positiveTestWithQueryParam() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");
        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200, "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "Female" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Female"));
        // verify "Janette" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }

    /* TEST CASE for Query parameters with Maps
    Given accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|e
    When user sends Get Request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void positiveTestWithQueryParamWtihMaps() {
        // create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        // It will add key and values in order.
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");
        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200, "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "Female" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Female"));
        // verify "Janette" should be in response payload
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }








}
