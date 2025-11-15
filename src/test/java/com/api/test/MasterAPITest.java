package com.api.test;
import static  io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void MasterAPITest()
	{
		
		Header authheader = new Header("Authorization",AuthTokenProvider.getToken(Roles.FD));
		given()
		 .spec(SpecUtil.RequestSpecWIthAuth(Roles.FD))
		.when()
		.post("/master")
		.then()
		.spec(SpecUtil.responsespec_Ok())
		.body("message",Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("mst_model"))
		.body("$", Matchers.hasKey("message"))
		.body("$", Matchers.hasKey("data"))
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/MasterAPIResponseSchema.json"));
	}
	@Test
	public void InvalidTokenForMasterAPI()
	{
		given()
		.and()
		.baseUri(ConfigManager.getProperties("BASE_URI"))
		.and()
		.and()
		.contentType("")
		.when()
		.post("/master")
		.then()
		.log()
		.all()
		.statusCode(401);
		
	}

}
