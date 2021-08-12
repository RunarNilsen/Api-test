package ApiPutPatchDelete;

import POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpartanFLow {
    /*
    In here we will create new spartan and then update its information and finally delete it.
     */

    int id;

    @BeforeClass
    public void beforeclass(){
        // this will be use base url in any @Test and we just add end-point to use desired url
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test(priority = 1)
    public void createNewSpartanWithPost() {

        /*
        we have two request in one Test
         */
        // creating spartan object
        Spartan spartanEU = new Spartan();
        spartanEU.setName("MikeEU4");
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

        id = response.path("data.id");
        System.out.println("id = " + id);
    }

    @Test(priority = 2)
    public void updateSpartanWithPutRequest() {
        // Create one map for the put-request JSON body
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("name", "MikeEU4");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1231231231l);


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 119)
                .and()
                .body(putRequestMap).
                when()
                .put("/api/spartans/{id}")
                .then().log().all()
                .statusCode(204);
    }

    @Test(priority = 3)
    public void getSpartan(){
        // send get request to check if updating is done
        given().accept(ContentType.JSON)
                .and().pathParam("id", 119)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(119),
                "name", equalTo("MikeEU4"),
                "gender", equalTo("Male"),
                "phone", equalTo(1231231231));
    }

    @Test(priority = 4)
    public void deleteSpartan(){

        given().pathParam("id", 119)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204).log().all();
    }







}
