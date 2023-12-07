package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class TestsExamples {
	
	@Test
	public void test1() {
		
		Response response = get("https://reqres.in/api/users?page=2");
		
		System.out.println("Response Status code:" + response.getStatusCode());
		
		System.out.println("Response time:" + response.getTime());
		
		System.out.println("Response body:" + response.body().asPrettyString());
		
		System.out.println("Response status line:" + response.getStatusLine());
		
		System.out.println("Response header:" + response.getHeader("content-type"));
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void test2() {
		
		baseURI = "https://reqres.in/api/";
		given().get("users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)).log().all();
	}

}
