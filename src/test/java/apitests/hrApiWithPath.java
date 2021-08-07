package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
public class hrApiWithPath {

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

         assertEquals(response.statusCode(), 200);
         // getting the limit value from the json
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        // We get first country id inside the items in the json file
        System.out.println("response.path(\"country id\") = " + response.path("items.country_id[0]"));

        // We get first country name inside the items in the json file
        String firstCountryName = response.path("items.country_name[0]");
        System.out.println("first country name= " + firstCountryName);
        String secondCountryName = response.path("items.country_name[1]");
        System.out.println("second country name= " + secondCountryName);

        // We get all countries name inside the items in the json file
        List<String> countryNames = response.path("items.country_name");
        System.out.println("country names= " + countryNames);

        // We get all href inside the items.links in the json file
        List<String> hrefs = response.path("items.links.href"); // return 2D array
        System.out.println("all hrefs in the links= " + hrefs);

        // We get third href inside the items.links in the json file
        String thirdHref = response.path("items.links[2].href[0]").toString();
        System.out.println("Third href in the links= " + thirdHref);

        // assert that all region_ids is equal to 2
        List<Integer> regionIds = response.path("items.region_id");
        for (int regionId : regionIds) {
            assertEquals(regionId, 2);
        }


    }






}
