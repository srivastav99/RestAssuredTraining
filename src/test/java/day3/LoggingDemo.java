package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	@Test
	void testLogs() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2&id=5")
		.then()
			//.log().body();
			//.log().cookies();//In this case, for this url, no cookies are getting generated so it does not print anything
			//.log().headers();
			.log().all();
	}

}
