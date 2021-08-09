package POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*; // adding Restassured to all test cases statically
import static org.testng.Assert.*;

public class PojoDeserialize {

    @Test
    public void oneSpartanPojo(){
        // We create custom class for getting all information of one Spartan from the Json response
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .and().get("http://18.232.145.26:8000/api/spartans/{id}");

        // Verify response status code should be 200
        Assert.assertEquals(response.statusCode(), 200 , "Verify status code : ");

        // Convert Json to POJO( Our custom Spartan java class )
        Spartan spartan15 = response.body().as(Spartan.class);
        System.out.println("spartan15 = " + spartan15);
        System.out.println("spartan15.getNames() = " + spartan15.getName());
        System.out.println("spartan15.getId() = " + spartan15.getId());


    }



    @Test
    public void regionToPojo(){
        Response response = when().get("http://18.232.145.26:1000/ords/hr/regions");
        Assert.assertEquals(response.statusCode(), 200);

        // JSON to POJO(Regions class)
        Regions regions = response.body().as(Regions.class);
        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        // reaching the first region name in the items key
        System.out.println("regions.getItems().get(0).getRegionName() = " + regions.getItems().get(0).getRegionName());
        // YA DA Bu şekilde de ulaşabiliriz.
        List<Item> items = regions.getItems();
        System.out.println("items.get(0).getRegionName() = " + items.get(0).getRegionName());


    }

    // ============ SERIALIZATION ========================

    @Test
    public void gson(){
        // we will see how to serialize the data (from data to json)
        Gson gson = new Gson();
        Spartan spartan = new Spartan(200, "Mike", "Male", 123123123); // create instance
        // convert instance to json (serialization)
        String jsonSpartanEU = gson.toJson(spartan);
        System.out.println("jsonSpartanEU = " + jsonSpartanEU);


    }


}
