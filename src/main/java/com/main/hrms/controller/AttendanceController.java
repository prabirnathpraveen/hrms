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

import com.main.hrms.model.Attendance;
import com.main.hrms.services.AttendanceService;


@RestController
@RequestMapping("/api/v1/")
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceservice;
	
	@GetMapping("/attendance")
	public List<Attendance> getData() {
		return attendanceservice.getData();
	}
	
	@GetMapping("/attendance/{id}")
	public ResponseEntity<Attendance> getData(@PathVariable Integer id) {
		Optional<Attendance>attendance = attendanceservice.getData(id);
	    if (attendance.isPresent()) {
	        return new ResponseEntity<>(attendance.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/checkin")
	public String insert(@RequestBody Attendance attendance){
		Optional<Attendance>_leave = attendanceservice.insertData(attendance);
		if(_leave.isPresent()) {
			return "The Data Saved Successfully";
		}else {
			return "You check in entry Already Exist";
		}
	}
	
	@PutMapping("/checkout")
	public String update(@RequestBody Attendance attendance){
		Optional<Attendance>_leave = attendanceservice.updateData(attendance);
		if(_leave.isEmpty()) {
			return "The Check Out Already Done or Check In Not Found";
		}else {
			return "Check Out Done Successfully";
		}
	}
	
	
	@DeleteMapping("/deleteattendance/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable int id){
		return attendanceservice.deleteData(id);
	}
}