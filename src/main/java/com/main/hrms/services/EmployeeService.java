package com.main.hrms.services;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Employee;
import com.main.hrms.model.RequestMeta;
import com.main.hrms.repository.EmployeeRepository;

import ch.qos.logback.core.boolex.Matcher;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeerepository;
	
	@Autowired
    private RequestMeta requestMeta;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Employee> getData(){
		return employeerepository.findAll();
	}
	
	public Optional<Employee> getData(Integer id){
		return employeerepository.findById(id);
	}
	
	public String deleteData(int id){
		if(employeerepository.existsById(id)) { 
			employeerepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
	
	public Optional<Employee> insertData(Employee employee){
		if(employeerepository.existsById(employee.getEmployeeid())){
			return Optional.empty();
		}
		else {
			LocalTime formattedTime = Employee.getFormattedTime();
			employee.setCreatedtime(formattedTime);
			employee.setPasswordhash(passwordEncoder.encode(employee.getPasswordhash()));
			employee.setCreatedby(String.valueOf(requestMeta.getUserid()));
			return Optional.of(employeerepository.save(employee));
		}	
	}
	
	
	public Optional<Employee> updateData(Employee employee){
		if(employeerepository.existsById(employee.getEmployeeid())){
			LocalTime formattedTime = Employee.getFormattedTime();
			employee.setUpdatedtime(formattedTime);
			if(!isBcryptHash(employee.getPasswordhash())) {
				employee.setPasswordhash(passwordEncoder.encode(employee.getPasswordhash()));	
			}
			employee.setUpdatedby(String.valueOf(requestMeta.getUserid()));
			return Optional.of(employeerepository.save(employee));
		}
		else{
			return Optional.empty();
		}	
	}

	 public static boolean isBcryptHash(String inputStr) {
	        if (inputStr.length() != 60) {
	            return false;
	        }
	        if (!inputStr.startsWith("$2a$") &&
	            !inputStr.startsWith("$2b$") &&
	            !inputStr.startsWith("$2y$") &&
	            !inputStr.startsWith("$2x$")) {
	            return false;
	        }
	        String bcryptPattern = "^\\$[0-9a-zA-Z./]{2}\\$[0-9]{2}\\$[0-9a-zA-Z./]{53}$";
	        Pattern pattern = Pattern.compile(bcryptPattern);
	        java.util.regex.Matcher matcher = pattern.matcher(inputStr);
	        if (!matcher.matches()) {
	            return false;
	        }

	        return true;
	    }

}
