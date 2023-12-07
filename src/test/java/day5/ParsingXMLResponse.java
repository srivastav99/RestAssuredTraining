package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParsingXMLResponse {
	
	@Test
	void testXMLResponse() {
		/*
		//Approach 1
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler/?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))//validating page number is 1 or not
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));//Validating traveller info
			//Here in the above line the value might change after a few days as they are dynamic
		 */
		//Approach 2
		Response res = given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler/?page=1");
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1"); ////validating page number is 1 or not
		
		String travellerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellerName, "Developer");//Validating traveller info
		//Here in the above line the value might change after a few days as they are dynamic
			
	}
	
	
	@Test
	void testXMLResponseBody() {
		
		Response res = given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler/?page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());//.asString() converts entire response type to string format whereas .toString() convert data to string
		//verify total no.of travellers 
		List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");	
		Assert.assertEquals(travellers.size(), 10);
		
		//verify traveller name is present in response
		List<String> traveller_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");	
		
		boolean status = false;
		for (String travellername : traveller_names) {
			//System.out.println(travellername);
			
			if(travellername.equals("Developer")) {
				status = true;
				break;
			}
		}
		//Here in the above line the value might change after a few days as they are dynamic
		Assert.assertEquals(status, true,"Traveller name not found");
		
	}
}
