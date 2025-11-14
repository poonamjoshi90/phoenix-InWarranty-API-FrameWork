package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	static UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	@Test
	public  void loginAPITest() throws IOException
	{
		
	     given()
	     .baseUri(ConfigManager.getProperties("BASE_URI"))
	     .and()
	     .contentType(ContentType.JSON)
	      .accept(ContentType.JSON)
	      .and()
	     .body(userCredentials)
	     .log().uri()
	     .log().body()
	     .log().headers()
	     .when()
	     .post("login")
	     .then()
	     .log().all()
	      .statusCode(200)
	      .time(lessThan(1000L))
	      .and()
	      .body("message",equalTo("Success"))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema2.json"));
	      
	     
		
		
	}

}
