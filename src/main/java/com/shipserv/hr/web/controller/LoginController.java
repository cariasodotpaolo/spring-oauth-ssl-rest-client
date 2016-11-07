/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipserv.hr.web.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shipserv.hr.web.domain.UserToken;
import com.shipserv.hr.web.service.LoginService;

/**
 *
 * @author paolo
 */
@Controller
public class LoginController {
    
    @Resource
    private LoginService loginService;
        
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String indexPage() {
       
        return "redirect:/static/login.html";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) throws SQLException {
                
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String keepSignedIn = request.getParameter("keepSignedIn");
        
        
        
        UserToken userToken = loginService.login(userName, password);
        
        if(userToken.getAccessToken() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("keepSignedIn", keepSignedIn);
            session.setAttribute("userName", userName);
            session.setAttribute("accessToken", userToken.getAccessToken());
            
            return "redirect:/employee/list?status=ACTIVE";
        } else {
            return "redirect:/static/login.html";
        }        
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        
        session.invalidate();
        
        return "redirect:/static/login.html";
    }
    
}
