package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static   org.hamcrest.Matchers.*;
import static com.api.constant.Roles.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static  com.api.utils.SpecUtil.*;

import  static io.restassured.module.jsv.JsonSchemaValidator.*;



import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static  io.restassured.RestAssured.*;

import javax.xml.crypto.Data;

public class CountAPITest {
	
	@Test(description="Verify Count of AllResponse in API should show correctly",groups= {"api","smoke","regression"})
	public void VerifyCountAPIResponse()
	{
		given()
		 .spec(RequestSpecWIthAuth(FD))
		 .when()
		 .get("/dashboard/count")
		 .then()
		  .spec(responsespec_Ok())
		 .body("message", Matchers.equalTo("Success"))
		 .time(Matchers.lessThan(1000L))
		 .body("data", Matchers.notNullValue())
		 .body("data.size()",equalTo(3))
		 .body("data.count", everyItem(Matchers.greaterThanOrEqualTo(0)))
		 .body(matchesJsonSchemaInClasspath("responseSchema/CountAPIResponseSchemaFD.json"))
		 .body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		
	}
	
	@Test(description="Verify Status code is 401 for invalid Token",groups= {"api","smoke","regression"})
	public void countAPITest_MissingAutoToken()
	{
		given()
		 .spec(requestSpec())
		.when()
		.post("/master")
		.then()
		  .spec(responsespec_Text(401));
		
		
		
	}

}
