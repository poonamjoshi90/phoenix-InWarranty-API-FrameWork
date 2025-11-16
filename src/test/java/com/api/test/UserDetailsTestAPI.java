package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManagerOld;
import com.api.utils.SpecUtil;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.testng.annotations.Test;
import  static com.api.constant.Roles.*;
import io.restassured.http.Header;

public class UserDetailsTestAPI {
	
	@Test
	public void userDetailsTestAPI() throws IOException
	{
		
	//	Header authheader14 = new Header("Authorization",AuthTokenProvider.getToken(Roles.FD));
		given()
		 .spec(SpecUtil.RequestSpecWIthAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responsespec_Ok())
		 .and()
	      .body("message",equalTo("Success"))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/UserDetailsSchema.json"));
	     
		
	}

}
