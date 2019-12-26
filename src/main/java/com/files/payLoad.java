package com.files;

public class payLoad {

	public static String getPostData() {

		String postData = "{" +

				"\"location\": {" +

				"\"lat\": -33.8669710," +

				"\"lng\": 151.1958750" +

				"}," +

				"\"accuracy\": 50," +

				"\"name\": \"Google Shoes!\"," +

				"\"phone_number\": \"(02) 9374 4000\"," + "\"types\": [\"shoe_store\"]," +

				"\"website\": \"http://www.google.com.au/\"," +

				"\"language\": \"en-AU\"" +

				"}";

		return postData;
	}

	public static String createBugInJira() {

		String postData = "{\r\n" + 
				"\"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"KJ\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"Add comments in cereated bug \",\r\n" + 
				"         \"description\": \"Kailash is creating first defect through REST Assured\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"            },\r\n" + 
				"            \"assignee\": {\r\n" + 
				"            \"name\": \"kailashpathak\"\r\n" + 
				"            }\r\n" + 
				"            \r\n" + 
				"         }\r\n" + 
				"  }";

		return postData;
	}

}
