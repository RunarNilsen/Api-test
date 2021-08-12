package ApiPutPatchDelete;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;


public class PutRequestDemo {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }


    @Test
    public void testPutRequest(){
        // Create one map for the put-request JSON body
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1231231231l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 117)
                .and()
                .body(putRequestMap).
        when()
                .put("/api/spartans/{id}")
        .then().log().all()
                .statusCode(204);

        // send get request to check if updating is done
        given().accept(ContentType.JSON)
                .and().pathParam("id", 117)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(117),
                "name", equalTo("PutName"),
                "gender", equalTo("Male"),
                "phone", equalTo(1231231231));
    }

    @Test
    public void testPatchRequest() {
        // Create one map for the put-request JSON body
        Map<String, Object> patchRequestMap = new HashMap<>();
        patchRequestMap.put("name", "TJ"); // we just update the name


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 117)
                .and()
                .body(patchRequestMap).
                when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .statusCode(204);

        // send get request to check if updating is done
        given().accept(ContentType.JSON)
                .and().pathParam("id", 117)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(117),
                "name", equalTo("TJ"),
                "gender", equalTo("Male"),
                "phone", equalTo(1231231231));
    }






}
