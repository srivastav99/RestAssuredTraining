package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	//post()
	@Test
	void test_createUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender","Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "e38ebdbf6fb4092b072ae32e2a74d81796ce1a2128c4394af62cd066aa843a6f";
		//The above token is taken from gorest.com - https://gorest.co.in/my-account/access-tokens
		
		int id = given()
			.header("Authorization","Bearer "+bearerToken)
			.contentType("application/json")//if we are sending this as a header then we should sent it as application.json but here we are just sending it as content-type
			.body(data.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		System.out.println("Generated id:"+id);
			
		//Here context.setAttribute scope is only within a test(test level of xml suite)
		//context.setAttribute("user_id", id);
		//Thus to make its scope suite level we need to use:
		context.getSuite().setAttribute("user_id", id);
		
		
	}

}
