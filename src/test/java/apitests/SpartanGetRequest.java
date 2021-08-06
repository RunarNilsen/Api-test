package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically

public class SpartanGetRequest {

    String spartanUrl = "http://18.232.145.26:8000";


    @Test
    public void test1(){

        Response response = when().get(spartanUrl + "/api/spartans");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    /*
    When user sends a get request to /api/spartans/3
    Then status code should be 200
    And content type should be application/json
    And json body should contain Fidole
     */

    @Test
    public void test2(){
        Response response = when().get(spartanUrl + "/api/spartans/3");

        //Verify response status code is 200
        Assert.assertEquals(response.statusCode(), 200, "Verify the status code : ");
        //Verify content-type is application/json
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify response body contains Americas
        Assert.assertTrue(response.body().asString().contains("Fidole"));
    }

    /*
    Given no headers provider
    When user sends get request to api/hello
    Then response status code should be 200
    And content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And content-length should be 17
    And body should be "Hello from Sparta"

     */

    @Test
    public void helloTest(){
        Response response = when().get(spartanUrl + "/api/hello");
        //Verify response status code is 200
        Assert.assertEquals(response.statusCode(), 200, "Verify the status code : ");
        //Verify content-type is application/json
        Assert.assertEquals(response.contentType(), "text/plain;charset=UTF-8", "Verify contant-type :");
        //Verify header should contain date
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        // Verify the length of the header content-length
        Assert.assertEquals(response.headers().getValue("Content-Length"),"17", "Verify content-length :");
        // Verify body should be "Hello from Sparta"
        Assert.assertEquals(response.body().asString(), "Hello from Sparta", "Verify body is : ");
    }




}
