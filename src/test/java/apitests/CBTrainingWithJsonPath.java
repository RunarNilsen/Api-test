package apitests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static io.restassured.RestAssured.baseURI;

public class CBTrainingWithJsonPath {
    // Cybertek training with jsonPath

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("cpt_api_url");
    }

    @Test
    public void test1(){
        Response response = given().contentType(ContentType.JSON)
                .and().pathParam("id", 21742)
                .when().get("/student/{id}");

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

        // getting first name of the student
        String firstName = jsonPath.get("students[0].firstName");
        System.out.println("firstName = " + firstName );

        // getting phone number
        String phone = jsonPath.get("students[0].contact.phone");
        System.out.println("phone = " + phone);
        // second way
        String phone2 = jsonPath.getString("students.contact[0].phone");
        System.out.println("phone2 = " + phone2);

        // getting the city and zipcode
        String city = jsonPath.getString("students.company[0].address.city");
        int zipCode = jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("city = " + city);
        System.out.println("zipCode = " + zipCode);
        Assert.assertEquals(city, "Chicago");
        Assert.assertEquals(zipCode, 60606);


    }





}
