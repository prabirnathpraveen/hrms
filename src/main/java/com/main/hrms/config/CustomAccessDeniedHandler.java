package com.main.hrms.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
			
			Map<String, Object> jsonResponse = new HashMap<>();
	        jsonResponse.put("status", HttpServletResponse.SC_FORBIDDEN);
	        jsonResponse.put("message", "Access Denied: You do not have the necessary permissionss.");
			
	        Gson gson = new Gson();
	        String jsonString = gson.toJson(jsonResponse);
	        
	        response.getWriter().write(jsonString);
	}
}
