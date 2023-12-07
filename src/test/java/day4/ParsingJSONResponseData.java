package day4;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {
	
	@Test(priority = 1)
	void testJsonResponse() {
		
		/*
		//Approach 1(Here, we are doing assertions through then())
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title", equalTo("The Lord of the Rings"));
		 */
		
		//Approach 2(here we will do assertions on top of res variable)
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200); //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
	}
	
	@Test(priority = 2)
	void testJsonResponseBodyData() {
		
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
	
		//JSONObject class(for this class to be available json related dependencies should be added like json)
		//We can parse json response data by using JSONObject class 
		JSONObject jo = new JSONObject(res.asString()); //.asString() converts entire response type to string format whereas .toString() convert data to string
		//Here in the above line the, response is starting with json object({}), so we are creating JSONobject class object and passing our response(res) in it, but if the response starts with json array([]), then we need to create an object of JSONArray class and pass our response(res) to it.
		//printing all the titles of book
		/*
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
		
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			System.out.println(bookTitle);
		}
		*/
		
		//search for title of the book in json
		boolean status= false;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			if(bookTitle.equals("The Lord of the Rings")) {
				
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true,"Book title not found");
		
		//validate total price of books
		double totalprice=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			
			totalprice = totalprice +Double.parseDouble(price);
			
		}
		System.out.println(totalprice);
		Assert.assertEquals(totalprice, 526,"Actual Total price is not as per expected total price");
	}
	

}
