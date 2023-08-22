package com.main.hrms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Employee;
import com.main.hrms.model.Login;
import com.main.hrms.repository.EmployeeRepository;

@Service
public class LoginService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    public boolean check(String storedHashedPassword,String rawPassword) {
    	
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(rawPassword, storedHashedPassword);
        if (matches) {
           return true;
        } else {
            return false;
        }
    }

    public Optional<Employee> authenticate(Login login) {
		Optional<Employee> employees = employeeRepository.findByEmailid(login.getEmailid());
		if (!employees.isEmpty()) {
            Employee employee = employees.get();
            if(check(employee.getPasswordhash(),login.getPassword())) {
            	return employees;
            }
        }
		return employees.empty();
	}
}

