package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.testng.Assert.*;


public class JsonToJavaCollection {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void spartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);

        // we will convert Json response to Java Map (Deserialization)
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        // getting the name
        String name = (String) jsonDataMap.get("name");
        assertEquals(name, "Meta");
        // getting phone number
        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);


    }


    }

