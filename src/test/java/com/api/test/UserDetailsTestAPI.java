package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;
import java.io.IOException;
import static com.api.constant.Roles.*;
import org.testng.annotations.Test;
import com.api.request.model.UserCredentials;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManagerOld;
import  static com.api.utils.SpecUtil.*;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import  static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;
import io.restassured.http.Header;

public class UserDetailsTestAPI {
	
	@Test(description="Verify Userresponse should show correctly",groups= {"api","smoke","regression"})
	public void userDetailsTestAPI() throws IOException
	{
		
	//	Header authheader14 = new Header("Authorization",AuthTokenProvider.getToken(Roles.FD));
		given()
		 .spec(RequestSpecWIthAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(responsespec_Ok())
		 .and()
	      .body("message",equalTo("Success"))
	      .body(matchesJsonSchemaInClasspath("responseSchema/UserDetailsSchema.json"));
	     
		
	}

}
