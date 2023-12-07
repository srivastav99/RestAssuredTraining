package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	//put()
	@Test
	void test_updateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender","Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "e38ebdbf6fb4092b072ae32e2a74d81796ce1a2128c4394af62cd066aa843a6f";
		//The above token is taken from gorest.com - https://gorest.co.in/my-account/access-tokens
		
		int id=(int) context.getSuite().getAttribute("user_id");//This value will come from test_createUser test method
		
		given()
			.header("Authorization","Bearer "+bearerToken)
			.contentType("application/json")//if we are sending this as a header then we should sent it as application.json but here we are just sending it as content-type
			.pathParam("id", id)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
					
		.then()
			.statusCode(200)
			.log().all();
		
		
	}

}
