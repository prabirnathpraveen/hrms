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

import com.main.hrms.model.Leaves;
import com.main.hrms.services.LeaveService;

@RestController
@RequestMapping("/api/v1/")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveservice;
	
	@GetMapping("/leave")
	public List<Leaves> getData() {
		return leaveservice.getData();
	}
	
	@GetMapping("/leave/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public ResponseEntity<Leaves> getData(@PathVariable Integer id) {
		Optional<Leaves>leaves = leaveservice.getData(id);
	    if (leaves.isPresent()) {
	        return new ResponseEntity<>(leaves.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/insertleave")
	public String insert(@RequestBody Leaves leaves){
		Optional<Leaves>_leave = leaveservice.insertData(leaves);
		if(_leave.isPresent()) {
			return "The Leave Data Saved Successfully";
		}else {
			return "Leave Already Exist";
		}
	}
	
	@PutMapping("/updateleave")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String update(@RequestBody Leaves leaves){
		Optional<Leaves>_leave = leaveservice.updateData(leaves);
		if(_leave.isEmpty()) {
			return "The Leave Data Does Not Exist";
		}else {
			return "The Leave Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deleteleave/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable int id){
		return leaveservice.deleteData(id);
	}
}
