package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

//In headers content-type, content-Encoding and server are the things which are very often validated
public class HeadersDemo {
	
	@Test(priority = 1)
	void testHeader() {
			
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")//If we have multiple validations we can also seperate them with .and() like .header("","") .and() .header("","") 
			.header("Server", "gws");
			//.log().all();
	}
	
	@Test(priority = 2)
	void gettHeader() {
			
		Response res = given() //here res will store all the response coming from http request
			
		.when()
			.get("https://www.google.com/");
			
		/*
		//getting single header info
		String headervalue = res.getHeader("Content-Type");
		System.out.println("The value of Content-Type header is:"+headervalue);
		*/
		
		//getting all headers info
		Headers myheaders = res.getHeaders();
		//Here Headers class represents multiple headers(a variable of that class can store multiple headers). There is also a Header class whose variable can store one Header at a time.
		for (Header hd : myheaders) {
			System.out.println(hd.getName()+"      "+ hd.getValue());
		}
		
	}

}
