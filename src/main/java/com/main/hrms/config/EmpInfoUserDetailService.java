package com.main.hrms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.main.hrms.model.Employee;
import com.main.hrms.repository.EmployeeRepository;

@Component
public class EmpInfoUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeerepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Employee> empInfo = employeerepository.findByEmailid(email);
		return empInfo.map(EmpInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("user not Found" + email));
	}
}
