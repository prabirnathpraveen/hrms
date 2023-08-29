package com.main.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.hrms.model.Salary;
import com.main.hrms.services.SalaryService;

@RestController
@RequestMapping("/api/v1/")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryservice;
	
	@GetMapping("/salary")
	public List<Salary> getData() {
		return salaryservice.getData();
	}
	
	@GetMapping("/salary/{id}")
	public ResponseEntity<Salary> getData(@PathVariable Integer id) {
		Optional<Salary>salary = salaryservice.getData(id);
	    if (salary.isPresent()) {
	        return new ResponseEntity<>(salary.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/insertsalary")
	public String insert(@RequestBody Salary salary){
		Optional<Salary>_salary = salaryservice.insertData(salary);
		if(_salary.isPresent()) {
			return "The Salary Data Saved Successfully";
		}else {
			return "Salary Already Exist";
		}
	}
	
	@PutMapping("/updatesalary")
	public String update(@RequestBody Salary salary){
		Optional<Salary>_salary = salaryservice.updateData(salary);
		if(_salary.isEmpty()) {
			return "The Salary Data Does Not Exist";
		}else {
			return "The Salary Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deletesalary/{id}")
	public String delete(@PathVariable int id){
		return salaryservice.deleteData(id);
	}
}
