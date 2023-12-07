package day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//https://jsonformatter.org/json-to-jsonschema -- json schema converter
public class JSONSchemaValidation {
	
	@Test
	void jsonschemavalidation() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
			
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storejsonschema.json.json"));//The name is the file name that we copy pasted in src/test/resources
			
			
	}
}
