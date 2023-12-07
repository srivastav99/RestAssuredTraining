package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
Different ways to create POST request body
1.Post request body using HashMap
2.Post request body creation using Org.JSON
3.Post request body creation using POJO(Plain Old Java Object) class
4.Post request using external json file data
 */

public class DiffWaysToCreatePostRequestBody {
	
	//1.Post request body using HashMap
	//@Test(priority = 1)
	void testPostusingHashMap() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String[] courseArr = {"c","c++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]", equalTo("c"))
			.body("courses[1]", equalTo("c++"))
			.header("Content-type", "application/json; charset=utf-8")
			.log().all();			
		
	}
	
	//2.Post request body creation using Org.JSON(for this we have to add a dependency json of org.json from maven repo
	//@Test(priority = 1)
	void testPostusingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String[] courseArr = {"c","c++"};
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]", equalTo("c"))
			.body("courses[1]", equalTo("c++"))
			.header("Content-type", "application/json; charset=utf-8")
			.log().all();			
		
	}
	
	//3.Post request body creation using POJO(Plain Old Java Object) class
	//@Test(priority = 1)
	void testPostusingPOJO() {
		
		POJO_PostRequest data = new POJO_PostRequest();
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		
		String []coursesArr = {"c","c++"};
		data.setCourses(coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]", equalTo("c"))
			.body("courses[1]", equalTo("c++"))
			.header("Content-type", "application/json; charset=utf-8")
			.log().all();			
		
	}
	
	
	//4.Post request using external json file data
	@Test(priority = 1)
	void testPostusingExternalJsonFile() throws FileNotFoundException {
		
		File f = new File(".\\body.json"); //We have created a file named as data.json in this project in eclipse having some data. 
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]", equalTo("c"))
			.body("courses[1]", equalTo("c++"))
			.header("Content-type", "application/json; charset=utf-8")
			.log().all();			
		
	}
	
	
	@Test(priority = 2)
	void testDelete() {
		
		when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200);
	}
	
	
	

}
