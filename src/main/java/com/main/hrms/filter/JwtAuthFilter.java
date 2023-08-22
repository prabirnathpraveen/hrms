package com.main.hrms.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.main.hrms.config.EmpInfoUserDetailService;
import com.main.hrms.services.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	  @Autowired
	   private JwtService jwtService;
	  
	  @Autowired
	    private EmpInfoUserDetailService userDetailsService;
	  
	  
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException ,ExpiredJwtException {
		
		 String authHeader = request.getHeader("Authorization");
		 
		 
		 //just printing the cookie.
		 Cookie[] cookies = request.getCookies(); 
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("authToken".equals(cookie.getName())) { 
		                String token = cookie.getValue();
		                System.out.println("AuthToken from Cookie: " + token);
		                break;
		            }
		        }
		    }
		    
		    //
	        String token = null;
	        String email = null;
	        try {
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            email = jwtService.extractUsername(token);
	        }

	        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	            if (jwtService.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);	
	            }
	        }
	        } catch (ExpiredJwtException e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Token has expired");
	            return;
	        } catch(MalformedJwtException exp) {
	        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Invalid Token");
	            return;
	        }
	        filterChain.doFilter(request, response);
	    }
	}

