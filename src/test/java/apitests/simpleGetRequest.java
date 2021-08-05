package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {
    /*
    Rest Assured library is used for testing API
     */

    String hrUrl = "http://3.216.124.61:1000/ords/hr/regions";

    @Test
    public void test1(){
        // get method send GET REQUEST and take the response
        Response response = RestAssured.get(hrUrl);
        int statCode = response.statusCode(); // It returns the status code of the request  like 200, 400.
        System.out.println("status Code = " + statCode);
        response.prettyPrint(); // It prints the response in a pretty way
    }

     /* TEST CASE
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        And body is json format
         */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                                        .when().get(hrUrl);
        //Verify response status code is 200
        Assert.assertEquals(response.statusCode(), 200, "Verify the status code : ");

        //Verify content-type is application/json
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");

    }




}
