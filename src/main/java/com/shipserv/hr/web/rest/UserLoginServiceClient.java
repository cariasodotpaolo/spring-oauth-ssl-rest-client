package com.shipserv.hr.web.rest;

import com.shipserv.hr.web.domain.UserToken;

public interface UserLoginServiceClient {

	public UserToken login(String userName, String password);

}