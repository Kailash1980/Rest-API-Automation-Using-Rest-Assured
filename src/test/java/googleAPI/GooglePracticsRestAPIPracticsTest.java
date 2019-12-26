package googleAPI;

import io.restassured.RestAssured;
import com.files.resources;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GooglePracticsRestAPIPracticsTest {

	//@Test
	public void testApi_1() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().

				param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyBX46ZBqCa8i4LnibUL0Tyfdz5Iwkstt4Y").

		when().

				get("maps/api/place/nearbysearch/json").
				
		then().

				assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and().body("results[0].id",equalTo("044785c67d3ee62545861361f8173af6c02f4fae")).and()
				.header("server", "scaffolding on HTTPServer2");
	}


	// @Test 
	 public void testApi_2() {
	 
	 RestAssured.baseURI = "https://maps.googleapis.com";
	 
	  given().
	 
	 param("location", "-33.8670522,151.1957362"). param("radius", "500").
	 param("key","AIzaSyBX46ZBqCa8i4LnibUL0Tyfdz5Iwkstt4Y").
	  
	  when(). get("maps/api/place/nearbysearch/json").
	 
	  then().
	  assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("results[8].id",equalTo("2e3dec069aed3a50278a0f8556d7520d84d3c4e6")).and()
		.header("server", "scaffolding on HTTPServer2");
	  
	  }
	 

}