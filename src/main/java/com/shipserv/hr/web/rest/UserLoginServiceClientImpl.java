package com.shipserv.hr.web.rest;

import static com.jayway.restassured.RestAssured.given;

import org.springframework.stereotype.Service;

import com.jayway.restassured.response.ValidatableResponse;
import com.shipserv.hr.web.domain.UserToken;

@Service
public class UserLoginServiceClientImpl implements UserLoginServiceClient {

	private String BASE_SERVICE_URL = "http://localhost";
	private String SERVICE_CONTEXT_URI = "/hr-service";
	private int BASE_SERVICE_PORT = 8080;
	private String OAUTH2_LOGIN_URL = "/oauth/token";

	private String CLIENT_ID = "test01-id";

	private String CLIENT_SECRET = "test01-secretkey";

	private String GRANT_TYPE = "password";

	private String SCOPE = "read";

	/*formParam("client_id", "test01-id").
	formParam("client_secret", "test01-secretkey").
	formParam("grant_type", "password").
	formParam("scope", "read").*/

	/*returns access token*/
	public UserToken login(String userName, String password) {

		ValidatableResponse response =
				given().
					formParam("username", userName).
					formParam("password", password).
					formParam("client_id", CLIENT_ID).
					formParam("client_secret", CLIENT_SECRET).
					formParam("grant_type", GRANT_TYPE).
					formParam("scope", SCOPE).
					baseUri(BASE_SERVICE_URL).
					port(BASE_SERVICE_PORT).
					log().all(). //logs the request

				when().
					post(SERVICE_CONTEXT_URI + OAUTH2_LOGIN_URL).
				then().
					log().all(); //logs the response


				String accessToken = response.extract().body().jsonPath().get("access_token");

				UserToken userToken = new UserToken();
				userToken.setUserName(userName);
				userToken.setAccessToken(accessToken);
				userToken.setFullName(userName);

				return userToken;

	}

}
