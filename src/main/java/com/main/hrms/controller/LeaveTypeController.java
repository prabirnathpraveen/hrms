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

import com.main.hrms.model.LeaveType;
import com.main.hrms.services.LeaveTypeService;

@RestController
@RequestMapping("/api/v1/")
public class LeaveTypeController {
	
	@Autowired
	private LeaveTypeService leavetypeservice;
	
	@GetMapping("/leavetype")
	public List<LeaveType> getDepartmentDetails() {
		return leavetypeservice.getData();
	}
	
	@GetMapping("/leavetype/{id}")
	public ResponseEntity<LeaveType> getleavetype(@PathVariable("id") Integer id) {
		Optional<LeaveType> leavetype = leavetypeservice.getData(id);
	    if (leavetype.isPresent()) {
	        return new ResponseEntity<>(leavetype.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	@DeleteMapping("/deleteleavetype/{id}")
	public String delete(@PathVariable("id") int id){
		return leavetypeservice.deleteData(id);
	}
	
	
	@PostMapping("/insertleavetype")
	public String insert(@RequestBody LeaveType leavetype){
			Optional<LeaveType>_leavetype = leavetypeservice.insertData(leavetype);
			if(_leavetype.isPresent()) {
				return "The Job Data Saved Successfully";
			}else {
				return "Job Already Exist";
			}
	}
	
	@PutMapping("/updateleavetype")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_HR')")
	public String update(@RequestBody LeaveType leavetype){
		Optional<LeaveType>_leavetype = leavetypeservice.updateData(leavetype);
		if(_leavetype.isEmpty()) {
			return "The Job Data Does Not Exist";
		}else {
			return "The Job Data Updated Successfully";
		}
	}
	
	
	
}
