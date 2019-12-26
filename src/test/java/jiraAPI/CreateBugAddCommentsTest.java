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

public class CreateBugAddCommentsTest {

	static String cookiesVal;
	Properties prop = new Properties();

	@BeforeTest

	public void getdData() throws IOException {

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
	public void CreateBugAndAddComments() throws IOException

	{

		cookiesVal = resources.getCookiesValue();
	
		RestAssured.baseURI = prop.getProperty("JIRAHOST");

		String postBody = ResourcesUrl.get("T01");
		
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
		
		System.out.println("KP the URL for Add comments is " + (resources.createJiraBugsResources() + "" + bugId +"/comment"));

		/**
		 * Add Comments against the Bug
		 */

		String postBodyAddComments = ResourcesUrl.get("T02");
		String requestUrl = resources.createJiraBugsResources()+""+bugId+"/"+"comment";
		Response rs =
				
		given().header("Content-Type", "application/json").

				header("Cookie", "JSESSIONID=" + (cookiesVal)).

				body(postBodyAddComments).

		when().
		
		post(requestUrl).

		then().assertThat().extract().response();

		 responseString = rs.asString();
		
		assertEquals(rs.statusCode(), 201);

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