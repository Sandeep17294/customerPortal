package com.aetins.customerportal.web.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSONUtils Helper class is to process json string
 * 
 * @author avinash
 *
 */
public class JSONUtils {

	/**
	 * <i>To get string from given JSON node</i><br>
	 * 
	 * input: jsonString: {"test1":"sample1","test2":"sample2"}<br>
	 * jsonNode: test2 <br>
	 * output: sample2
	 * 
	 * @param jsonString
	 * @return
	 */
	public static String getJSONString(String jsonString, String jsonNode) {

		String nodeString = null;

		try {

			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNodeRoot = mapper.readTree(jsonString);
			JsonNode jsonNodeYear = jsonNodeRoot.get(jsonNode);

			nodeString = jsonNodeYear.asText();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodeString;
	}
	

	public String convertAsJSON(Class<?> entity) {

		String json = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			//mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return json = mapper.writeValueAsString(entity);

		} catch (Exception e) {

		}
		return json;

	}
	
	
	public static void main(String[] args) {

		/*
		 * String jsonString = "{\r\n" +
		 * "    \"access_token\": \"ca45b72c-0b3d-41d2-adb3-338c9e7f982c\",\r\n" +
		 * "    \"token_type\": \"bearer\",\r\n" +
		 * "    \"refresh_token\": \"6acdbb77-1278-44d2-9d98-6f7f632138ac\",\r\n" +
		 * "    \"expires_in\": 1827,\r\n" + "    \"scope\": \"read write trust\"\r\n" +
		 * "}"; String accestoken = "access_token";
		 * 
		 * String token = getJSONString(jsonString, accestoken);
		 * System.out.println(token);
		 */
		
		
	}
}
