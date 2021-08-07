package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class hrApiParameterTests {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("hr_api_url");
    }


    /* TEST CASE
   Given accept type is JSON
   And parameters: q="region_id":2
   When user sends Get Request to "/countries"
   Then response status code should be 200
   And response content-type: application/json
   And "United States of America" should be in response payload
   {"region_id":2}
    */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200 , "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "United States of America" should be in response payload
        Assert.assertTrue(response.body().asString().contains("United States of America"));
    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200 , "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");
        // verify "IT_PROG" should be in response payload
        Assert.assertTrue(response.body().asString().contains("IT_PROG"));

        // verify only "IT_PROG" is placed in the job_id
        List<String> jobIds = response.path("items.job_id");
        for (String jobId : jobIds) {
            Assert.assertEquals(jobId, "IT_PROG");
        }


    }



}
