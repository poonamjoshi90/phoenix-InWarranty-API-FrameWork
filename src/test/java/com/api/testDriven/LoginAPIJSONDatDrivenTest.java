package com.api.testDriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;


public class LoginAPIJSONDatDrivenTest {

	@Test(description="verify login with FD user",groups= {"api","regression","smoke"},
			dataProviderClass=com.dataProviders.DataProviderUtils.class,
			dataProvider="LoginAPIJSONDataProvider"
			)

	public  void loginAPITest( UserCredentials userCredentials) throws IOException
	{

		   given()
		   .spec(requestSpec(userCredentials))
	       .when()
	     .post("login")
	     .then()
	     .spec(responsespec_Ok())
	      .and()
	      .body("message",equalTo("Success"))
	      .body(matchesJsonSchemaInClasspath("responseSchema/LoginResponseSchema2.json"));




	}

}
