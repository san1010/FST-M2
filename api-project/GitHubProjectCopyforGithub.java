package projects;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GitHubProjectCopyforGithub {
 RequestSpecification requestSpec;
 String sshKey;
 int sshKeyId;
 
 @BeforeClass
 public void setUp() {
	 requestSpec= new RequestSpecBuilder()
			 .setContentType(ContentType.JSON)
			 .addHeader("Authorization", "token ")
			 .setBaseUri("https://api.github.com")
			 .build();
	 
	 sshKey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCIyVSXqguSNTOINU133p/cl32BLis16wRBuRRoRbfiC3mPuSnBQEuondSwg7iyEsmEfAkpmE+ZYL8QqUBwyNKasSzxZywPOxhLSs9BmGwpGmLz6hUjvF6u11iElR1TuxWl6ayU0HqL1sfhcWOMpqzyaOjH/ToD+YjFO/gFGCxzD24oDeoD4/YLDqnSjWApayu6cKBkHiNUYAbg6Vt3UXjg8x56D1AruPHrMhErlh4EWxTbHudMDLQLYg8p55MYwKezNY7z2F/nbn/m7TeyT1WH4CiOYOWSeMg5iKYjV6cu47cc3Z8TvTT5cZIsC9iEYRP6YhF+il3IgFwlhou";
 }
 @Test(priority=1)
 public void generateKey() {
	 String reqBody="{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCIyVSXqguSNTOINU133p/cl32BLis16wRBuRRoRbfiC3mPuSnBQEuondSwg7iyEsmEfAkpmE+ZYL8QqUBwyNKasSzxZywPOxhLSs9BmGwpGmLz6hUjvF6u11iElR1TuxWl6ayU0HqL1sfhcWOMpqzyaOjH/ToD+YjFO/gFGCxzD24oDeoD4/YLDqnSjWApayu6cKBkHiNUYAbg6Vt3UXjg8x56D1AruPHrMhErlh4EWxTbHudMDLQLYg8p55MYwKezNY7z2F/nbn/m7TeyT1WH4CiOYOWSeMg5iKYjV6cu47cc3Z8TvTT5cZIsC9iEYRP6YhF+il3IgFwlhou\"}";
	 Response response=given().spec(requestSpec).body(reqBody).when().post("/user/keys");
	 
	 //System.out.println(response.asPrettyString());
	 
	 sshKeyId=response.then().extract().path("id");
	 
	 //Assertions 
	 response.then().statusCode(201);
	 response.then().body("id",notNullValue());
 }
 
 @Test(priority=2)
 public void getKey() {
	 Response getResponse=given().spec(requestSpec).when().get("/user/keys");
	 System.out.println(getResponse.body().asPrettyString());
	 
	 //Assertions 
	 getResponse.then().statusCode(200);
 }
 
 @Test(priority=3)
 public void deleteKey() {
	
	 Response delResponse=given().spec(requestSpec).when().pathParam("keyId", sshKeyId).delete("user/keys/{keyId}");
	 
	 //System.out.println(delResponse.body().asPrettyString());
	 //Assertions
	 delResponse.then().statusCode(204);
	 
 }
 
}
