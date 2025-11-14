package com.api.test;
import static  io.restassured.RestAssured.*;

import static   org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.UserCredentials;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManagerOld;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.testng.annotations.Test;

import io.restassured.http.Header;

public class UserDetailsTestAPI {
	
	@Test
	public void userDetailsTestAPI() throws IOException
	{
		
		Header authheader14 = new Header("Authorization",AuthTokenProvider.getToken(Roles.FD));
		given()
		.baseUri(ConfigManager.getProperties("BASE_URI"))
		.and()
		.header(authheader14)
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().body()
		.log().method()
		.log().headers()
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1000L))
		 .and()
	      .body("message",equalTo("Success"))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/UserDetailsSchema.json"));
	     
		
	}

}
