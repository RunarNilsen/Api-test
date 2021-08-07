package apitests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.baseURI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically


public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1(){
        /*
        Look at GPath methods. findAll is one of them.
         */

        Response response = get("/countries");

        // assign to jsonPath
        JsonPath jsonPath = response.jsonPath();

        // get  the first name inside the items in the json
        String firstName = jsonPath.get("items.country_name[0]");
        System.out.println("firstName = " + firstName);

        // get all country ids
        List<String> countryIds = jsonPath.getList("items.country_id");
        System.out.println("countryIds = " + countryIds);

        // get all country names where their region id is equal to 2
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.c{it.region_id==2}.country_name");
        System.out.println("countryNamesWithRegionId2 = " + countryNamesWithRegionId2);

    }


    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");
        JsonPath jsonPath = response.jsonPath();

        // get all first name of employees who is working as IT_PROG
        List<String> firstNames = jsonPath.getList("items.findAll{it.job_id == \"IT_PROG\"}.first_name");
        System.out.println("firstNames = " + firstNames);

        // get all first name of employees who is making more than 2200
        List<String> firstNamesRich = jsonPath.getList("items.findAll{it.salary <2200}.first_name");
        System.out.println("firstNames = " + firstNamesRich);

        // get first name of employee who is making highest salary
        String richestMann = jsonPath.get("items.max{it.salary}.first_name");
        System.out.println("richestMann = " + richestMann);

        // get first name of employee who is making lowest salary
        String poorMann = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println("poor mann = " + poorMann);

    }







}
