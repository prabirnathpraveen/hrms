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
import com.main.hrms.model.Jobs;
import com.main.hrms.services.JobsService;

@RestController
@RequestMapping("/api/v1/")
public class JobsController{
	
	@Autowired
	private JobsService jobservice;
	
	@GetMapping("/jobs")
	public List<Jobs> getJobs() {
		return jobservice.getData();
	}
	
	@GetMapping("/jobs/{id}")
	public ResponseEntity<Jobs> getJobs(@PathVariable Integer id) {
		Optional<Jobs> jobs = jobservice.getData(id);
	    if (jobs.isPresent()) {
	        return new ResponseEntity<>(jobs.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	@DeleteMapping("/deletejobs/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String delete(@PathVariable int id){
		return jobservice.deleteData(id);
	}
	
	
	@PostMapping("/insertjobs")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String insert(@RequestBody Jobs jobs){
		if(jobservice.checkDepId(jobs)){
			Optional<Jobs>_jobs = jobservice.insertData(jobs);
			if(_jobs.isPresent()) {
				return "The Job Data Saved Successfully";
			}else {
				return "Job Already Exist";
			}
		}else {
			return "Department id does not exist";
		}
	}
	
	@PutMapping("/updatejobs")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String update(@RequestBody Jobs jobs){
		Optional<Jobs>_jobs = jobservice.updateData(jobs);
		if(_jobs.isEmpty()) {
			return "The Job Data Does Not Exist";
		}else {
			return "The Job Data Updated Successfully";
		}
	}
	
}
