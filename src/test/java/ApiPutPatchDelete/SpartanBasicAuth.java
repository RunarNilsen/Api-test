package ApiPutPatchDelete;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanBasicAuth {
    /*
    AUTHENTICATION --> verifies if you are who you claim you are.
    AUTHORIZATION --> verifies if you have access/permission to certain API/actions.
    401 --> invalid credentials, API doesn't know who you are
    403 --> valid credentials but not enough access level/permission to do action
     */


    @Test
    public void test1(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin") // username, password
        .when()
                .get("http://18.232.145.26:8000/api/spartans")
        .then().log().all()
                .statusCode(200);
    }







}
