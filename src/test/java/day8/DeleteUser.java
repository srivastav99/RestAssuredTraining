package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	//delete()
	@Test
	void test_deleteUser(ITestContext context) {
		
		String bearerToken = "e38ebdbf6fb4092b072ae32e2a74d81796ce1a2128c4394af62cd066aa843a6f";
		//The above token is taken from gorest.com - https://gorest.co.in/my-account/access-tokens
	
		int id=(int) context.getSuite().getAttribute("user_id");//This value will come from test_createUser test method
		
		given()
			.header("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
		
	
	}

}
