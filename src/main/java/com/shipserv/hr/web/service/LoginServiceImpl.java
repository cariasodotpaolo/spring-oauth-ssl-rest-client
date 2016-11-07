/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipserv.hr.web.domain.UserToken;
import com.shipserv.hr.web.rest.UserLoginServiceClient;

/**
 *
 * @author mpcariaso
 */
@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private UserLoginServiceClient loginClient;
    
    /*returns access token*/
    public UserToken login(String userName, String password)  {
        
        return loginClient.login(userName, password);
    }
    
}
