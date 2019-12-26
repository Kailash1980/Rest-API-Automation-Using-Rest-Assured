package com.files;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class resources {

	public static String resourcesPostData() {
		String resources = "/maps/api/place/add/json";

		return resources;
	}

	public static String resourcesDeleteData() {
		String resources = "/maps/api/place/delete/json";

		return resources;
	}

	public static String generateCookies() {
		String resources = "/rest/auth/1/session";

		return resources;
	}

	public static String createJiraBugsResources() {
		String resources = "/rest/api/2/issue/";

		return resources;
	}
	
	

	public static String createJiraComponentResources() {
		String resources = "/rest/api/2/component/";

		return resources;
	}
	
	public static String deleteJiraComponentResources() {
		String resources = "/rest/api/2/component/";

		return resources;
	}

	/**
	 * Get cookies value for JIRA API
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getCookiesValue() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"D:\\Workspace\\com.canvas.RestAPI\\src\\main\\java\\com\\files\\env.properties");
		prop.load(file);
		RestAssured.baseURI = prop.getProperty("JIRAHOST");

		Response rs = given().

				header("Content-Type", "application/json")
				.body("{\"username\": \"kailashpathak\", \"password\": \"Kp@1234\"}").

				when().

				post(resources.generateCookies()).

				then().assertThat().statusCode(200).and().extract().response();

		// Convert raw to String

		String responseString = rs.asString();

		// Convert string into Json
		JsonPath js = new JsonPath(responseString);
		String valueCookies = js.get("session.value");
		return valueCookies;

	}
}
