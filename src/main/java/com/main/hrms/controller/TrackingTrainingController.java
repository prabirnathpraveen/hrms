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

import com.main.hrms.model.TrackingTraining;
import com.main.hrms.services.TrackingTrainingService;

@RestController
@RequestMapping("/api/v1/")
public class TrackingTrainingController {
	
	@Autowired
	private TrackingTrainingService trainingtrackingservice;
	
	@GetMapping("/tracking")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public List<TrackingTraining> getData() {
		return trainingtrackingservice.getData();
	}
	
	@GetMapping("/tracking/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public ResponseEntity<TrackingTraining> getData(@PathVariable Integer id) {
		Optional<TrackingTraining>trackingtraining = trainingtrackingservice.getData(id);
	    if (trackingtraining.isPresent()) {
	        return new ResponseEntity<>(trackingtraining.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/inserttracking")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String insert(@RequestBody TrackingTraining trackingtraining){
		Optional<TrackingTraining>_trackingtraining = trainingtrackingservice.insertData(trackingtraining);
		if(_trackingtraining.isPresent()) {
			return "The Tracking Data Saved Successfully";
		}else {
			return "Leave Already Exist";
		}
	}
	
	@PutMapping("/updatetracking")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String update(@RequestBody TrackingTraining trackingtraining){
		Optional<TrackingTraining>_trackingtraining = trainingtrackingservice.updateData(trackingtraining);
		if(_trackingtraining.isEmpty()) {
			return "The Data Does Not Exist";
		}else {
			return "The Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deletetracking/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable int id){
		return trainingtrackingservice.deleteData(id);
	}
}
