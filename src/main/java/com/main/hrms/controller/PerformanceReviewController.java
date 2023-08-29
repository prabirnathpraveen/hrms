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

import com.main.hrms.model.PerformanceReview;
import com.main.hrms.services.PerformanceReviewServices;

@RestController
@RequestMapping("/api/v1/")
public class PerformanceReviewController {
	
	@Autowired
	private PerformanceReviewServices reviewservice;
	
	@GetMapping("/review")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public List<PerformanceReview> getData() {
		return reviewservice.getData();
	}
	
	@GetMapping("/review/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public ResponseEntity<PerformanceReview> getData(@PathVariable Integer id) {
		Optional<PerformanceReview>review = reviewservice.getData(id);
	    if (review.isPresent()) {
	        return new ResponseEntity<>(review.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/insertreview")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String insert(@RequestBody PerformanceReview review){
		Optional<PerformanceReview>_review = reviewservice.insertData(review);
		if(_review.isPresent()) {
			return "The Leave Data Saved Successfully";
		}else {
			return "Leave Already Exist";
		}
	}
	
	@PutMapping("/updatereview")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String update(@RequestBody PerformanceReview review){
		Optional<PerformanceReview>_review = reviewservice.updateData(review);
		if(_review.isEmpty()) {
			return "The Data Does Not Exist";
		}else {
			return "The Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deletereview/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable int id){
		return reviewservice.deleteData(id);
	}
}
