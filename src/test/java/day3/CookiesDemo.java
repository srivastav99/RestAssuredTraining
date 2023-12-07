package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;
import java.util.Map;


public class CookiesDemo {

	@Test(priority = 1)
	void testCookies() {
			
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC", "Ackid1SAHczN5CUC7pg8uvM769_pOEfYs3dCOfptmqfmWfCh_56d5ATquQ")//here the value 'Ackid1S...' of cookie is dynamic, it will change every time and thus the assert will fail
			.log().all();
	}

	
	@Test(priority = 2)
	void getCookiesInfo() {
		
		Response res = given() //here res will store all the response coming from http request(but for this to happen we cannot have then())
				
		.when()
			.get("https://www.google.com/");
		/*
		//getting single cookie info
		String cookie_value = res.getCookie("AEC");//Here we are getting the value of AEC cookie
		System.out.println("Value of cookie is====>"+cookie_value);
		*/
		//getting all cookies info
		Map<String, String> cookies_values =  res.getCookies();
		
		//System.out.println(cookies_values.keySet());
		
		for (String k : cookies_values.keySet()) {
			
			String cookie_value = res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
			
		}
	}
	
	
}
