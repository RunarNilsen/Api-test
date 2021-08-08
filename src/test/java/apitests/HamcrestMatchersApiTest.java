package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.hamcrest.Matchers.*; // importing matcher statically to use as a shortcut

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically

public class HamcrestMatchersApiTest {

        /* this is the one of the way for verifying json response like path, jsonPath.
        This method is called chaining method.
        HamcrestMatcher is a library that has lots of powerful verification methods.
        Hamcrest is one of the Restassured libraries.
         */

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }



    /* TEST CASE
   Given accept type is json
   And path param spartan id is 15
   When user sends a get request to api/spartans/{id}
   Then status code is 200
   And content-type is Json
   And "id" :15
       "name":"Meta"
       "gender":"Female"
       "phone" : 1938695106
    */
    @Test
    public void oneSpartanWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15),
                                    "name", equalTo("Meta"),
                                            "gender", equalTo("Female"),
                                            "phone", equalTo(1938695106));


    }

    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("teacherId", 4714)
                .when().get("http://api.cybertektraining.com/teacher/{teacherId}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().header("Content-Length", equalTo("282"))
                .and().header("Connection", equalTo("Keep-Alive"))
                .and().header("Date", notNullValue())
                .and().body("teachers.firstName[0]", equalTo("Carlie"),
                       "teachers.lastName[0]", equalTo("Bauch"),
                                "teachers.gender[0]", equalTo("Male"))
                .log().all();
    }


    @Test
    public void teacherWithDepartments(){
        // "hasItems" check if the given names inside the List or not

        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName", hasItems("Alexander", "Marteen"));
    }


}
