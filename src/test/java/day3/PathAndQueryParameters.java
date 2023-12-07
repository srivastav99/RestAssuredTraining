package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
//path parameters will specify the route that we are supposed to go where as query parameters will filter the data that we requested

public class PathAndQueryParameters {
	//https://reqres.in/api/users?page=2&id=5
	@Test
	void testQueryAndPathParameters() {
		
		given()
			.pathParam("mypath", "users")//path parameters(here 'users' is the parameter and we are assigning it to mypath
			.queryParam("page", 2) //query parameter
			.queryParam("id", 5) //query parameter
			//here api/ is also path parameter but it is common for all requests so we are not storing it as a path parameter
		.when()
			.get("https://reqres.in/api/{mypath}") //here we dont have to add the query parameter in the url because it goes automatically with request but path parameter we have to specify in {}
			
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
