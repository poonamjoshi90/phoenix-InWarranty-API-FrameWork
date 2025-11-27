package com.api.testDriven;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.api.utils.ConfigManager;
import  static com.api.utils.SpecUtil.*;
import com.dataProvider.api.beans.UserBean;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPIDatDrivenTest {
	
	@Test(description="verify login with FD user",groups= {"api","regression","smoke"},
			dataProviderClass=com.dataProviders.DataProviderUtils.class,
			dataProvider="LoginAPIDataProvider"
			)
	
	public  void loginAPITest(UserBean userBean) throws IOException
	{
		
		   given()
		   .spec(requestSpec(userBean))  
	       .when()
	     .post("login")
	     .then()
	     .spec(responsespec_Ok())
	      .and()
	      .body("message",equalTo("Success"))
	      .body(matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema2.json"));
	      
	     
		
		
	}

}
