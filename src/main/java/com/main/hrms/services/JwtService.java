package com.main.hrms.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.main.hrms.common.AccessDeniedException;
import com.main.hrms.common.RequestMetaContextHolder;
import com.main.hrms.model.Employee;
import com.main.hrms.model.RequestMeta;
import com.main.hrms.repository.EmployeeRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	 @Autowired
	  private RequestMeta requestMeta;
	
	 @Autowired
	 private EmployeeRepository employeerepository;
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	
	public String generateToken(String email,String username) {
		Optional<Employee> emp = employeerepository.findByEmailid(email);
		Map<String,Object> claims = new HashMap<>();
		if(emp.isPresent()) {
			Employee employee= emp.get();
			claims.put("role", employee.getRoles());
			claims.put("id",employee.getEmployeeid());
			claims.put("email", employee.getEmailid());
			claims.put("name", employee.getFirstname()+ " "+ employee.getLastname());
		}
		return createToken(claims,email);
		
	}

	private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	try {
			Claims claims = extractAllClaims(token);
			 if (claims != null) {
	                Integer id = claims.get("id", Integer.class);
	                String name = claims.get("name", String.class);
	                String email = claims.get("email", String.class);
	                String role = claims.get("role", String.class);
	                requestMeta.setUserid(id);
	                requestMeta.setRole(role);
	                requestMeta.setUsername(name);
	                requestMeta.setMailid(email);
	                RequestMetaContextHolder.setRequestMeta(requestMeta);
	                System.out.println(requestMeta.getUserid());
	            } else {
	                System.out.println("Invalid or null claims.");
	            }
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String email){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,email);
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}