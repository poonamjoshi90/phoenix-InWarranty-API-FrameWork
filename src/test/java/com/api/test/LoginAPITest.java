package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	static UserCredentials userCredentials = new UserCredentials("iamfd","password");
	
	@Test
	public  void loginAPITest() throws IOException
	{
		
		   given()
		   .spec(SpecUtil.requestSpec(userCredentials))  
	       .when()
	     .post("login")
	     .then()
	     .spec(SpecUtil.responsespec_Ok())
	      .and()
	      .body("message",equalTo("Success"))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema2.json"));
	      
	     
		
		
	}

}
