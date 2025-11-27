package com.api.utils;
import org.hamcrest.Matchers;
import com.api.request.model.UserCredentials;
import com.api.constant.Roles;
import com.api.utils.ConfigManager;
import com.dataProvider.api.beans.UserBean;
import com.api.utils.AuthTokenProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	
	
	
	public static RequestSpecification  requestSpec()
	{
		 RequestSpecification requestSpecification=new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getProperties("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return requestSpecification;
		
		
	
	}
	
	public static RequestSpecification  requestSpec(UserCredentials userCred)
	{
		 RequestSpecification request=new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getProperties("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(userCred)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
		
		
	
	}
	public static RequestSpecification  requestSpec(UserBean userBean)
	{
		 RequestSpecification request=new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getProperties("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(userBean)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
		
		
	
	}
	
	
	public static RequestSpecification RequestSpecWIthAuth(Roles role)
	{
		 RequestSpecification requestSpecification=new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getProperties("BASE_URI"))
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
				     .addHeader("Authorization", AuthTokenProvider.getToken(role))				
					.log(LogDetail.URI)
					.log(LogDetail.METHOD)
					.log(LogDetail.HEADERS)
					.log(LogDetail.BODY)
					.build();
					return requestSpecification;
					
	}
	public static RequestSpecification RequestSpecWIthAuth(Roles role,Object payload)
	{
		 RequestSpecification requestSpecification=new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getProperties("BASE_URI"))
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
					 .addHeader("Authorization", AuthTokenProvider.getToken(role))	
					.setBody(payload)
					.log(LogDetail.URI)
					.log(LogDetail.METHOD)
					.log(LogDetail.HEADERS)
					.log(LogDetail.BODY)
					.build();
					return requestSpecification;
					
	}
	
	public static ResponseSpecification responsespec_Json(int statusCode)
	{
		ResponseSpecification  responseSpecification =	new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responsespec_Text(int statusCode)
	{
		ResponseSpecification  responseSpecification =	new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responsespec_Ok()
	{
		ResponseSpecification  responseSpecification =	new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}

}
