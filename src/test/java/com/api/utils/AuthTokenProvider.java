package com.api.utils;
import static  io.restassured.RestAssured.*;

import com.api.constant.Roles;
import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
public class AuthTokenProvider {

	public static String  getToken(Roles role) {
		
		UserCredentials userCredentials=null;
		if(role==Roles.FD)
		{
			userCredentials=	new UserCredentials("iamfd","password");
		}
		else if(role==Roles.SUP)
		{
			userCredentials=	new UserCredentials("iamsup","password");
		}
		else if(role==Roles.SUP)
		{
			userCredentials=	new UserCredentials("iameng","password");
		}
		
		else if(role==Roles.QC)
		{
			userCredentials=	new UserCredentials("iamqc","password");
		}
	
	String token=	given()
		 .baseUri(ConfigManager.getProperties("BASE_URI"))
		 .and()
		 .contentType(ContentType.JSON)
		 .body(userCredentials)
		 .when()
		 .post("login")
		 .then()
		 .log().all()
		 .extract()
		 .body()
		 .jsonPath()
		 .getString("data.token");
	
	System.out.println("------------------------------------");
	System.out.println(token);
	return token;
		 

	}

}
