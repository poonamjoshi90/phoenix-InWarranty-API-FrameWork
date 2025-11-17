package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	
	private  UserCredentials userCredentials;
	
	
	
	private LoginAPITest()
	{
		
	}
	
	@BeforeMethod(description="Create payload for Login API")
	public void setup()
	{
	 userCredentials = new UserCredentials("iamfd","password");
			
		
	}
	
	@Test(description="verify login with FD user",groups= {"api","regression","smoke"})
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
	      .body(matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema2.json"));
	      
	     
		
		
	}

}
