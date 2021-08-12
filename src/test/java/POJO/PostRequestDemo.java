package POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /*TEST CASE
    Given content-type and accept-type is JSON
    And request json body is
           {
            "gender": "Male",
            "name": "Mike",
            "phone": 1231232133
                }
    When user sends POST request to "/api/spartans"
    Then status code is 201
    And content-type should be application/json
    And json payload/response should include:
            "A Spartan is Born!" message
    And same json data what is posted
     */

    @Test
    public void PostNewSpartan(){
        // 1st way of sending json as a post is to write json as a string and add it inside the body
        String jsonBody = "{\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"name\": \"Mike\",\n" +
                "            \"phone\": 1231232133\n" +
                "                }";
        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        // verify status code is 201
        assertEquals(response.statusCode(), 201);
        //Verify content type is application/json
        assertEquals(response.contentType(), "application/json");

        // Verify that json payload/response should include: "A Spartan is Born!" message
        String actualMessage = response.path("success");
        String expectedMessage = "A Spartan is Born!";
        assertEquals(actualMessage, expectedMessage);

        // Verify that same json data what was posted, is received as a response
        String actualName = response.path("data.name");
        String actualGender = response.path("data.gender");
        int actualPhone = response.path("data.phone");

        assertEquals(actualName, "Mike");
        assertEquals(actualGender, "Male");
        assertEquals(actualPhone, 1231232133);

    }

    @Test
    public void PostNewSpartan2(){
        /*
        We can Json information as a key, value pairs in a map
        we can use log().all() to see the details
          */
        //create a map to keep request json body information
        Map<String,Object> requestMap = new HashMap<>();
        //adding value that we want to post
        requestMap.put("name","MikeEU2");
        requestMap.put("gender","Male");
        requestMap.put("phone",8877445596l);

        given().log().all()
                .accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().post("/api/spartans")
        .then().log().all()
                .statusCode(201)
                .and().contentType("application/json")
                .and().body("success", Matchers.equalTo("A Spartan is Born!"),
                            "data.name", Matchers.equalTo("MikeEU2"),
                                     "data.gender", Matchers.equalTo("Male"),
                                     "data.phone", Matchers.equalTo(8877445596l));



    }

    @Test
    public void PostNewSpartan3(){
        /*
        In here we will use POJO for Post request
        1. We will create an instance(a spartan) from Spartan class(POJO)
        2. Then we will pass the spartan class in the body() method
        3. body() will serialize the spartan class and post it to the API
         */
        // creating spartan object
        Spartan spartanEU = new Spartan();
        spartanEU.setName("MikeEU3");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        // Posting the Spartan object to the API
        given().log().all()
                .accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(spartanEU)
        .when()
                .post("/api/spartans")
        .then().log().all()
                .statusCode(201)
                .and().contentType("application/json")
                .and().body("success", Matchers.equalTo("A Spartan is Born!"),
                "data.name", Matchers.equalTo("MikeEU3"),
                "data.gender", Matchers.equalTo("Male"),
                "data.phone", Matchers.equalTo(8877445596l));


    }


    @Test
    public void PostNewSpartan4(){
        /*
        we have two request in one Test
         */
        // creating spartan object
        Spartan spartanEU = new Spartan();
        spartanEU.setName("MikeEU3");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        // Posting the Spartan object to the API
        Response response = given().log().all()
                .accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(spartanEU)
                .when()
                .post("/api/spartans");

        // getting id number of the spartan that we generated and posted to the API
        int idFromPost  = response.path("data.id");
        System.out.println("id = " + idFromPost);

        // After Post request, sen a Get request to generated spartan
        given().accept(ContentType.JSON)
                .pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all();

    }





}
