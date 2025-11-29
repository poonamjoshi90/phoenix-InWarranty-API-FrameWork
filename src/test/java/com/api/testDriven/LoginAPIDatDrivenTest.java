package com.api.testDriven;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.dataProvider.api.beans.UserBean;

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
