package com.api.test;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.RequestSpecWIthAuth;
import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static com.api.utils.SpecUtil.responsespec_Text;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

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
