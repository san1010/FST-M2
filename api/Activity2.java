package activities;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Activity2 {
	final static String ROOT_URI="https://petstore.swagger.io/v2/user";
	
  @Test(priority=1)
  public void addNewUserIdFromFile() throws IOException, InterruptedException {
	  FileInputStream inputJSON=new FileInputStream("src\\test\\resources\\input.json");
	  String reqBody=new String(inputJSON.readAllBytes());
	  
	  Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(ROOT_URI);
	 inputJSON.close();
	 
	 response.then().body("code", equalTo(200));
	 
	  Thread.sleep(1000);
  }
  
  @Test(priority=2)
  public void getUserInfo() {
	  File outputJSON=new File("src\\test\\resources\\userGETResponse.json");
	  Response response= given().contentType(ContentType.JSON).pathParam("username", "san1010").when().get(ROOT_URI + "/{username}");
	  
	  String resBody=response.getBody().asPrettyString();
	  
	  	try {
		  outputJSON.createNewFile();
		  FileWriter writer = new FileWriter(outputJSON.getPath());
		  writer.write(resBody);
		  writer.close();
	  		} catch (IOException excp) {
		  excp.printStackTrace(); 
	  		}
	 
	  response.then().body("id", equalTo(7602));
	  response.then().body("username", equalTo("san1010"));
	  response.then().body("firstName", equalTo("Justin"));
	  response.then().body("lastName", equalTo("Case"));
	  response.then().body("email", equalTo("san1010@mail.com"));
	  response.then().body("password", equalTo("password123"));
	  response.then().body("phone", equalTo("9812763450"));
	  
	  /*
	   * {
  "id": 7602,
  "username": "san1010",
  "firstName": "Justin",
  "lastName": "Case",
  "email": "san1010@mail.com",
  "password": "password123",
  "phone": "9812763450"
}
	   * */
  		}
  @Test(priority=3)
  public void deleteUser() {
	  Response response=given().contentType(ContentType.JSON).pathParam("username", "san1010").delete(ROOT_URI+ "/{username}");
	  response.then().body("code", equalTo(200));
	  
  }
  
}
