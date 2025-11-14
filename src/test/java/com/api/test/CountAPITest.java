package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static   org.hamcrest.Matchers.*;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import  static io.restassured.module.jsv.JsonSchemaValidator.*;



import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static  io.restassured.RestAssured.*;

import javax.xml.crypto.Data;

public class CountAPITest {
	
	@Test
	public void VerifyCountAPIResponse()
	{
		Header authheader = new Header("Authorization",AuthTokenProvider.getToken(Roles.FD));
		given()
		.baseUri(ConfigManager.getProperties("BASE_URI"))
		.and()
		 .header(authheader)
		 .and()
		 .when()
		 .get("/dashboard/count")
		 .then()
		 .log().all()
		 .statusCode(200)
		 .body("message", Matchers.equalTo("Success"))
		 .time(Matchers.lessThan(1000L))
		 .body("data", Matchers.notNullValue())
		 .body("data.size()",equalTo(3))
		 .body("data.count", everyItem(Matchers.greaterThanOrEqualTo(0)))
		 .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchemaFD.json"))
		 .body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		
	}
	
	@Test
	public void countAPITest_MissingAutoToken()
	{
		given()
		.baseUri(ConfigManager.getProperties("BASE_URI"))
		.and()
		.when()
		 .get("/dashboard/count")
		 .then()
		 .log().all()
		 .statusCode(401);
		
	}

}
