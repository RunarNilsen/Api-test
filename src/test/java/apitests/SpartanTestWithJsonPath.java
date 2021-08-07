package apitests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically



public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /*
    Given accept type is json
    And path param spartan id is 11
    When user sends a get request to api/spartans/{id}
    Then status code is 200
    And content-type is Json
    And "id" :11
        "name":"Nona"
        "gender":"Female"
        "phone" : 7959094216
     */

    @Test
    public void gets(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .and().get("/api/spartans/{id}");

        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200 , "Verify status code : ");
        //Verify content-type is "application/json"
        Assert.assertEquals(response.contentType(), "application/json", "Verify contant-type :");

        // Verify id and name  with path
        int id = response.path("id");
        String name = response.path("name");

        Assert.assertEquals(id, 11);
        Assert.assertEquals(name, "Nona");

        // First we assign all response in the jsonpath object
        JsonPath jsonPath = response.jsonPath();
        // getting id from json
        int idJson = jsonPath.getInt("id"); // Verify id and name  with path
        String nameJson = jsonPath.getString("name");
        String genderJson = jsonPath.getString("gender");
        long phoneJson = jsonPath.getLong("phone");
        Assert.assertEquals(idJson, 11);
        Assert.assertEquals(nameJson, "Nona");
        Assert.assertEquals(genderJson, "Female");
        Assert.assertEquals(phoneJson, 7959094216l);

    }



}
