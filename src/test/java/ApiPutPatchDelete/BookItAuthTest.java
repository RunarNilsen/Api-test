package ApiPutPatchDelete;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class BookItAuthTest {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI="https://cybertek-reservation-api-qa2.herokuapp.com";
    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NyIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.a_N9URDBPGOMcDdEVoaMHsJtk3jOnig0v0SCtSWcsGE";

    @Test
    public void getAllCampuses(){
        //Reaching API with access token. We use "header(key, value)"
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/campuses");

        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(response.statusCode(), 200);


    }



}
