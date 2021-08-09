package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
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


    @Test
    public void allSpartansToListOfMap(){
        /*
        When we have multiple key and values inside the Json response, we use List of Map for deserialization
         */
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(response.statusCode(), 200);

        // We need to deseralize JSON response to List of Maps
        List<Map<String, Object>> allSpartansList = response.body().as(List.class);
        System.out.println("allSpartansList = " + allSpartansList);

        // print second spartans first name
        // here first "get()" is a List method while second "get()" is a Map method
        System.out.println("name= " + allSpartansList.get(1).get("name"));

        // Save one Map from the List of Map to a Map
        Map<String, Object> spartan3 = allSpartansList.get(2);
        System.out.println("spartan3 = " + spartan3);

    }

    @Test
    public void regionToMap(){
        Response response = when().get("http://18.232.145.26:1000/ords/hr/regions");
        Assert.assertEquals(response.statusCode(), 200);

        // deserializing json response to Map
        Map<String, Object> regionMap = response.body().as(Map.class);
        //In here "hasMore", "limit", "offset", "count" are in first level of the Map and they just keep String value
        // Therefore we can reach their values like this
        boolean hasMoreValue = (boolean) regionMap.get("hasMore");
        System.out.println("hasMoreValue = " + hasMoreValue);
        double countValue = (double) regionMap.get("count");
        System.out.println("countValue = " + countValue);

        // "item" key inside the Json has value of List. How can we reach any key inside the "items" key.
        // NOTE: IF WE HAVE LIST AS A VALUE OF THE KEY, WE HAVE TO USE LIST OF MAP AND CAST THE OBJECT
        // "regionMap.get("items")" is an Object and we can cast it as List of Map to reach the information inside it.
       List<Map<String, Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");
       // getting first region name
        String firstRegionName = (String) itemsList.get(0).get("region_name");
        System.out.println("region_name = "  + firstRegionName);


    }






    }

