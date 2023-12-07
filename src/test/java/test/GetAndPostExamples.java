package test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {
	
	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name", equalTo("George")).
			body("data.first_name", hasItems("George","Byron","Rachel"));
		
	}
	
	@Test
	public void testPost() {
		/*
		Map<String, Object> map = new HashMap<>();
		
		
		map.put("name", "Vastav");
		map.put("job", "Tester");
		
		System.out.println(map);
		*/
		
		
		JSONObject request = new JSONObject();
		request.put("name", "Vastav");
		request.put("job", "Tester");
		
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			contentType(ContentType.JSON). //saying the we are sending json format
			accept(ContentType.JSON).      //saying that we are accepting json format
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}

}
