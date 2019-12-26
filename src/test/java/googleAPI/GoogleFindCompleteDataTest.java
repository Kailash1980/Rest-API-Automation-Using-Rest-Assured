package googleAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GoogleFindCompleteDataTest {

	@Test
	public void testApi_1() {

		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res =
			given(). //Pre-condition

				param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyBX46ZBqCa8i4LnibUL0Tyfdz5Iwkstt4Y").

			when(). //Action you perform e.g Hit the Api

				get("maps/api/place/nearbysearch/json").

			then(). //Verification part where we implement the Assertion

				assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).
				
			extract().response();

		/*String responseString = res.asString();
		System.out.println("Response data is " + responseString);

		JsonPath js = new JsonPath(responseString);
		int arraySize = js.get("results.size()");

		for (int i = 0; i < arraySize; i++) {
			System.out.println(js.get("results[" + i + "].name"));
		}

		System.out.println("Array Size " + arraySize);*/

	}

}
