package com.api.test;
import static  io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static com.api.constant.Roles.*;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import static  com.api.utils.SpecUtil.*;
import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	
	@Test (description="Verify Master Data should show correctly",groups= {"api","smoke","regression"})

	public void MasterAPITest()
	{	given()
		 .spec(RequestSpecWIthAuth(FD))
		.when()
		.post("/master")
		.then()
		.spec(responsespec_Ok())
		.body("message",Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data", Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("mst_model"))
		.body("$", Matchers.hasKey("message"))
		.body("$", Matchers.hasKey("data"))
		 .body(matchesJsonSchemaInClasspath("responseSchema/MasterAPIResponseSchema.json"));
	}
	
	
	@Test (description="Verify Invalid Token in  Master API",groups= {"api","smoke","regression"})
	public void InvalidTokenForMasterAPI()
	{
		given()
		 .spec(requestSpec())
		.when()
		.post("/master")
		.then()
		  .spec(responsespec_Text(401));
		
	}

}
