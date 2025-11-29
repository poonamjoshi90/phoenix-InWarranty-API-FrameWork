package com.api.testDriven;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.RequestSpecWIthAuth;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayload;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIJsonDataDrivenTest {

	CreateJobPayload createJobPayload;


	@Test(description="Verify if create api is able to create job",
			dataProviderClass=com.dataProviders.DataProviderUtils.class,
					groups= {"api","regression","smoke"},
			dataProvider="CreateJobAPIJsonDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload)
	{
		given()
		.spec(RequestSpecWIthAuth(FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responsespec_Ok())
		.and()
	      .body("message",equalTo("Job created successfully. "))
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"));


	}

}
