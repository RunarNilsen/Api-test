package ApiPutPatchDelete;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;


public class DeleteRequestDemo {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void delete1(){

        given().pathParam("id", 120)
        .when().delete("/api/spartans/{id}")
        .then().statusCode(204).log().all();
    }

    @Test
    public void randomDelete(){
        Random rnd = new Random();
        int idToDelete = rnd.nextInt(100)+1;
        System.out.println("Say good bye to Spartan " + idToDelete );

        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }






}
