package jiraAPI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.files.resources;
import com.files.util.ResourcesUrl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * JIRA API Automation
 * 
 * @author kailash.pathak
 *
 */

public class CreateEditDeleteBugTest{

	static String cookiesVal;
	Properties prop = new Properties();	
	
	@BeforeTest

	public void getData() throws IOException {

		FileInputStream file = new FileInputStream(
				"D:\\Workspace\\com.canvas.RestAPI\\src\\main\\java\\com\\files\\env.properties");
		prop.load(file);

	}

	/**
	 * Create Bug using Post request
	 * 
	 * @throws IOException
	 */

	@Test
	public void CreateEditAndDeleteBugTest() throws IOException

	{
		cookiesVal = resources.getCookiesValue(); // Get Cookies to Login into Jira
	
		RestAssured.baseURI = prop.getProperty("JIRAHOST"); // Base URL

		String postBody = ResourcesUrl.get("T01"); // Get Pay Load for Test Case
		
		Response res =

			given().header("Content-Type", "application/json").

						header("Cookie", "JSESSIONID=" + (cookiesVal)).

						body(postBody)

			.when().post(resources.createJiraBugsResources()).

			 then().assertThat().statusCode(201).

						extract().response();

		String responseString = res.asString();
		JsonPath js = new JsonPath(responseString); 
		String bugId = js.get("id");
		System.out.println("Bug Id is " + bugId);
		
		System.out.println("KP the URL for Add comments is " + (resources.createJiraBugsResources() + "" + bugId));
		
		/**
		 * 
		 * Update Created bug with Summary,description,Issue type
		 */
		
		String postBodyUpdateComponent = ResourcesUrl.get("T05");
		String requestUrl = resources.createJiraBugsResources()+""+bugId;
		Response updateComponentResponse =
				
		given().header("Content-Type", "application/json").

				header("Cookie", "JSESSIONID=" + (cookiesVal)).

				body(postBodyUpdateComponent).

		when().
		
		put(requestUrl).

		then().assertThat().extract().response();
		
		assertEquals(updateComponentResponse.statusCode(), 204);
		
		

		/**
		 * Delete the above created Bug
		 * 
		 */
		given().

				header("Cookie", "JSESSIONID=" + (cookiesVal)).

				when()

				.delete(resources.createJiraBugsResources() + "" + bugId).

				then().assertThat().statusCode(204);

	}
}
