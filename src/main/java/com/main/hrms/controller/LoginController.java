package com.main.hrms.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.hrms.model.Employee;
import com.main.hrms.model.Login;
import com.main.hrms.services.JwtService;
import com.main.hrms.services.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/")
public class LoginController {
	
	 @Autowired
	 private LoginService loginservice;
	 
	@Autowired
	private JwtService jwtservice;
	
	
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody Login login , HttpServletResponse response){
    	Optional<Employee> emp = loginservice.authenticate(login);
    	if (emp.isPresent()) {
            Employee employee = emp.get();
            String token =  jwtservice.generateToken(employee.getEmailid(),employee.getFirstname());
            Cookie cookie = new Cookie("authToken", token);
            cookie.setMaxAge(24 * 60 * 60); // Cookie will expire in 24 hours (adjust as needed)
            cookie.setPath("/"); 
            response.addCookie(cookie);
            return token;
        }
		return "invalid user request !";
    }
}

