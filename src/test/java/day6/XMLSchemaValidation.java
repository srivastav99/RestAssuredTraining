package day6;


import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class XMLSchemaValidation {
	//Failed code
	@Test
	void xmkschemavalidation() {
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
			
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("travelers.xsd"));
			
			
	}

}
