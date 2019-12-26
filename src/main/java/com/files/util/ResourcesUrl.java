package com.files.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.path.json.JsonPath;

public class ResourcesUrl {

	private static JSONObject jsonData;

	static {
		JSONParser parser = new JSONParser();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File("src/main/resources/data.json"));
			Object obj = parser.parse(fileReader);
			jsonData = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String get(String testId) {
		return ((JSONObject) jsonData.get(testId)).toJSONString();
	}
}
