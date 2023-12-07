package day1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
RestAsssured uses BDD style gherkin language, keywords in BDD are:
given() : we have keep information like - content type, set cookies, add auth, add parm, set headers info etc
when() : we have to keep all http requests like - get, put, patch, delete, patch
then() : we have to keep all validation like - validate status code, extract response, extract header cookies and response body...

To use the above three methods we have to add 3 static packages, they are called static because we have to add them manually unlike normal packages
*/
public class HTTPRequests {
	
	
	int id; //This will be used to store id obtained from http request and will be used in update and delete user methos.
	
	//for get() given() is not requited
	@Test(priority = 1)
	void getUsers() {
				
		when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
			
		
	}
	
	//post()
	@Test(priority = 2)
	void createUser() {
		
		
		HashMap data = new HashMap(); //normally we use other methods to give data in request body
		data.put("name", "pavan");
		data.put("job", "trainer");
		
		id = given() //here we are trying to capture id value from the http response(but for this we cannot have then())
			.contentType("application/json")
			.body(data)
			
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id"); //here we are trying to capture id value from the http response
		/*
		.then()
			.statusCode(201)		
			.log().all();
		*/
	}
	
	//put()
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "john");
		data.put("job", "engineer");
		
		given() //here we are trying to capture id value from the http response
			.contentType("application/json")
			.body(data)
			
		
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)		
			.log().all();
	}
	
	@Test(priority = 4)
	void deleteUser() {
		
	
		when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
		
	}

}
