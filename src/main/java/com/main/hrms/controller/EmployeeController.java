package com.main.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.hrms.model.Employee;
import com.main.hrms.model.ResponseApi;
import com.main.hrms.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController{

	@Autowired
	private EmployeeService employeeservice;

	@GetMapping("/employee")
	public List<Employee> getData(){
		return employeeservice.getData();
	}
	
	@GetMapping("/employee/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public ResponseEntity<Object> getData(@PathVariable("id") Integer id) {
		Optional<Employee> employee = employeeservice.getData(id);
		ResponseApi responseapi = new ResponseApi();
	    if (employee.isPresent()) {
	        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	    } else {
	    	responseapi.setStatus(404);
	    	responseapi.setMessage("Resource Not Found or Employee Doesn't Exist");
	    	responseapi.setError("Not Found");
	        return new ResponseEntity<>(responseapi,HttpStatus.NOT_FOUND);
	    }     
	}
	
	@DeleteMapping("/employee/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable("id") int id){
		return employeeservice.deleteData(id);
	}
	
	@PostMapping("/insertemployee")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String insert(@RequestBody Employee employee){
			Optional<Employee>_employee = employeeservice.insertData(employee);
			if(_employee.isPresent()) {
				return "The Employee Data Saved Successfully";
			}else {
				return "Employee Already Exist";
			}
		}
	
	@PutMapping("/updateemployee")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String update(@RequestBody Employee employee){
		Optional<Employee>_employee = employeeservice.updateData(employee);
		if(_employee.isEmpty()) {
			return "The Employee Data Does Not Exist";
		}else {
			return "The Employee Data Updated Successfully";
		}
	}
}
