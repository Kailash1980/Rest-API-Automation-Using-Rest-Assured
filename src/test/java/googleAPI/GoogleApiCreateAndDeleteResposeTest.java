package googleAPI;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.files.payLoad;
import com.files.resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GoogleApiCreateAndDeleteResposeTest {

	Properties prop = new Properties();

	@BeforeTest

	public void getdData() throws IOException {

		FileInputStream file = new FileInputStream(
				"D:\\Workspace\\com.canvas.RestAPI\\src\\main\\java\\com\\files\\env.properties");
		prop.load(file);

	}

	// Post the response than get the response and finally delete the response

	@Test

	/*
	 * / 1- This is Post Request : Grab the response
	 */

	public void testApi_1() {

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res =

				given().

						queryParam("key", prop.getProperty("KEY")).

						body(payLoad.getPostData()).

				when().

						post(resources.resourcesPostData()).

				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()

						.body("status", equalTo("OK")).

				extract().response();

		String responseString = res.asString();
		
		
		/*
		 * / Task - 2 Grab the place id in a String than convert into Jason
		 */

		JsonPath js = new JsonPath(responseString);
		String placeid = js.get("place_id");

		System.out.println("Grab the Place id  " + placeid);

		/*
		 * / Task - 3 Delete the place id
		 */
		given().

				queryParam("key", prop.getProperty("KEY")).

				body("{" + "\"place_id\":\"" + placeid + "\"" + "}").

		when()

				.post(resources.resourcesDeleteData()).

		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

				body("status", equalTo("OK"));

	}
}