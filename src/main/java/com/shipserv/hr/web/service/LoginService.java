/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.service;

import com.shipserv.hr.web.domain.UserToken;

/**
 *
 * @author mpcariaso
 */
public interface LoginService {

    public UserToken login(String userName, String password);
    
}
