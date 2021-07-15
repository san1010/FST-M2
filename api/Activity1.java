package activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 {
	final static String ROOT_URI="https://petstore.swagger.io/v2/pet";
	/*
  @Test(priority=1)
  public void addNewPet() {
	  String reqBody= "{\"id\":77333, \"name\": \"Lucy2\", \"status\":\"available\"}";
	  Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(ROOT_URI);
	  
	  //Assertion 
	  
	  response.then().body("id", equalTo(77333));
	  response.then().body("name", equalTo("Lucy2"));
	  response.then().body("status", equalTo("available"));
	  
  } */
  @Test(priority=2)
  public void getPetInfo() {
	  Response response = given().contentType(ContentType.JSON).when().pathParam("petId","87456").get(ROOT_URI+"/{petId}");
	  response.then().body("id", equalTo(87456));
	  response.then().body("name", equalTo("puppy"));
	  response.then().body("status", equalTo("available"));
	  
	  
  }/*
  @Test(priority=3)
  public void deletPet() {
	  Response response = given().contentType(ContentType.JSON).when().pathParam("petId","77222").delete(ROOT_URI+"/{petId}");
	  response.then().body("code", equalTo(200));
	  response.then().body("message", equalTo("77222"));
  }*/
  
}
