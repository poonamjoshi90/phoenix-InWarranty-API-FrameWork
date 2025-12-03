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
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest12 {

	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating create Job API payload with FD user", groups = { "api", "regression",
			"smoke" })
	public void setup() {

		Faker faker = new Faker();

		String firstName = faker.name().firstName();
		String LastName = faker.name().lastName();
		String mobilenumber = faker.numerify("909#######");
		String altmobilenumber = faker.numerify("909#######");
		String customeremailId = faker.internet().emailAddress();
		String altcustomeremailId = faker.internet().emailAddress();
		System.out.print(firstName);
		Customer customer = new Customer(firstName, LastName, mobilenumber, altmobilenumber, customeremailId,
				altcustomeremailId);

		String flatNumber = faker.address().buildingNumber();
		String apartmentName = faker.address().streetName();
		String streetname = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String country = faker.address().country();
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetname, landmark, area,
				pincode, country, state);

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imei1serialnumber = faker.numerify("202###########");
		String popurl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imei1serialnumber, imei1serialnumber,
				imei1serialnumber, popurl, 1, 1);

		System.out.println(customerProduct);
		String fakarremark = faker.lorem().sentence(6);
		Random randam = new Random();
		int problemId = randam.nextInt(28) + 1;

		Problems problems = new Problems(problemId, fakarremark);

		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);

		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);

	}

	@Test(description = "verify create job sucessfully with FD user", groups = { "api", "regression", "smoke" })

	public void createJobAPITest() {
		given().spec(RequestSpecWIthAuth(FD, createJobPayload)).when().post("/job/create").then()
				.spec(responsespec_Ok()).and().body("message", equalTo("Job created successfully. "))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("responseSchema/CreateJobAPIResponseSchema.json"));

	}

}
