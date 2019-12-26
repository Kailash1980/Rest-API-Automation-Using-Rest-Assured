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

public class AddEditDeleteComponentTest{

	static String cookiesVal;
	Properties prop = new Properties();	
	
	@BeforeTest

	public void getdData() throws IOException {

		FileInputStream file = new FileInputStream("D:\\Workspace\\com.canvas.RestAPI\\src\\main\\java\\com\\files\\env.properties");
		prop.load(file);

	}

	/**
	 * Create Component ----  using Post request
	 * 
	 * @throws IOException
	 */

	@Test
	public void AddEditAndDeleteComponent() throws IOException

	{
		cookiesVal = resources.getCookiesValue();
	
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		
		String postAddComponentBody = ResourcesUrl.get("T03");
		
		Response addComponentResponse =

			given().header("Content-Type", "application/json").

						header("Cookie", "JSESSIONID=" + (cookiesVal)).

						body(postAddComponentBody)

			.when().post(resources.createJiraComponentResources()).

			 then().assertThat().statusCode(201).

						extract().response();
		
		String addComponentResponseString = addComponentResponse.asString();
	
		JsonPath js = new JsonPath(addComponentResponseString);
		String compenentID = js.get("id");


		/**
		 * Update  ---- --   Component  -----
		 * 
		 */

		String postBodyUpdateComponent = ResourcesUrl.get("T04");
		String requestUrl = resources.createJiraComponentResources()+""+compenentID;
		Response updateComponentResponse =
				
		given().header("Content-Type", "application/json").

				header("Cookie", "JSESSIONID=" + (cookiesVal)).

				body(postBodyUpdateComponent).

		when().
		
		put(requestUrl).

		then().assertThat().extract().response();
		
		assertEquals(updateComponentResponse.statusCode(), 200);
		


		/**
		 * 
		 * Delete ---  Component ---
		 * 
		 */
		given().

				header("Cookie", "JSESSIONID=" + (cookiesVal)).

				when()

				.delete(resources.deleteJiraComponentResources() + "" + compenentID).

				then().assertThat().statusCode(204);

	}
}