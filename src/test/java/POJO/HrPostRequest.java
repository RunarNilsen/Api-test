package POJO;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class HrPostRequest {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("hr_api_url");
    }


    @Test
    public void PostRegion1(){

    RegionPost regionPost = new RegionPost(10, "Cybertek Germany");

    // Posting the region object to the API

        given().log().all()
                .accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(regionPost)
        .when()
                .post("/regions/")
        .then().log().all()
                .statusCode(201)
                .and().contentType("application/json")
                .and().body("region_id",Matchers.is(10));

    }




}
