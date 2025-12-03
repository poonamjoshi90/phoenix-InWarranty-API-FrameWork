package com.api.test;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtil.RequestSpecWIthAuth;
import static com.api.utils.SpecUtil.responsespec_Ok;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakeData {

	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating create Job API payload with FD user", groups = { "api", "regression",
			"smoke" })
	public void setup() {

		
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

	}

	@Test(description = "verify create job sucessfully with FD user", groups = { "api", "regression", "smoke" })

	public void createJobAPITest() {
		given().spec(RequestSpecWIthAuth(FD, createJobPayload)).when().post("/job/create").then()
				.spec(responsespec_Ok()).and().body("message", equalTo("Job created successfully. "))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"));

	}

}
