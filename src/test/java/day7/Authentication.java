package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//Generally developers will create, give and guide testers in authentication process
public class Authentication {
	
	@Test(priority = 1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
			
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
			
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 3)
	void testPreemptastiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
			
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority = 4)
	void testBearerTokenAuthentication() {
		
		String bearerToken = "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";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	//Cannot execute this method as we need some details from client - consumerKey, consumerSecret, accessToken, tokenSecret, url 
	@Test(priority = 5)
	void testOAuth1Authentication() {
		
		
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")//This is for OAuth1.0 authentication
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	//Here we have just taken github bearer token but actually we will get another oauth2 token generated to make this work 
	@Test(priority = 6)
	void testOAuth2Authentication() {
		
		
		given()
			.auth().oauth2("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")//This is for OAuth2.0 authentication
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	@Test(priority = 7)
	void testAPIKeyAuthentication() {
		//api key value : 195efc7ee2e0abfc020191baf2d253d4
		//api key name : apikey1
		//from website : openweathermap.org -- 	https://home.openweathermap.org/api_keys
		//method 1
		/*
		given()
			.queryParam("apikey1", "195efc7ee2e0abfc020191baf2d253d4")
		
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
		*/
		
		//Method 2
		given()
			.queryParam("apikey1", "195efc7ee2e0abfc020191baf2d253d4")
			.pathParam("mypath","data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
	
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
		
		
	}

}
