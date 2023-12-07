package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.POJO_PostRequest;

//This program is for for learning concept purpose, 
//serialization -- converting POJO to JSON object
//deserialization - converting JSON to POJO object
//The above two is taken care of automatically by Rest assured internally with the help of ObjectMapper class of jackson package .

public class SerializationDeserialization {
	
	//pojo to JSON  (serialization)
	@Test
	void convertPOJO2Json() throws JsonProcessingException {
		
		//Creating java object using pojo class
		POJO_PostRequest_StudentAPI stupojo = new POJO_PostRequest_StudentAPI();
		
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		
		String []coursesArr = {"c","c++"};
		stupojo.setCourses(coursesArr);
		
		//conveting java object to json object(serialization)
		ObjectMapper objMapper = new ObjectMapper();//imported from com.fasterxml.jackson.databind.ObjectMapper
		
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		
		System.out.println(jsondata);
		
		
	}
	
	
	//JSON to pojo  (de-serialization)
	@Test
	void convertJson2POJO() throws JsonProcessingException {
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"c\", \"c++\" ]\r\n"
				+ "}";
		
		
		//conveting json data to java object(de-serialization)
		ObjectMapper objMapper = new ObjectMapper(); //imported from com.fasterxml.jackson.databind.ObjectMapper
		
		POJO_PostRequest_StudentAPI stupojo = objMapper.readValue(jsondata, POJO_PostRequest_StudentAPI.class);
		
		System.out.println("Name:"+stupojo.getName());
		System.out.println("Location:"+stupojo.getLocation());
		System.out.println("Phone no:"+stupojo.getPhone());
		System.out.println("Course 1:"+stupojo.getCourses()[0]);
		System.out.println("Course 2:"+stupojo.getCourses()[1]);
			
		
	}
	
	
	
}
