package com.api.test;
import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.RequestSpecWIthAuth;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

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
