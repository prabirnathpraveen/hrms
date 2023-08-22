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

import com.main.hrms.model.Training;
import com.main.hrms.services.TrainingService;

@RestController
@RequestMapping("/api/v1/")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingservice;
	
	@GetMapping("/training")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public List<Training> getData() {
		return trainingservice.getData();
	}
	
	@GetMapping("/training/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public ResponseEntity<Training> getData(@PathVariable("id") Integer id) {
		Optional<Training>trainings = trainingservice.getData(id);
	    if (trainings.isPresent()) {
	        return new ResponseEntity<>(trainings.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	
	@PostMapping("/inserttrainee")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String insert(@RequestBody Training trainings){
		Optional<Training>_leave = trainingservice.insertData(trainings);
		if(_leave.isPresent()) {
			return "The Leave Data Saved Successfully";
		}else {
			return "Leave Already Exist";
		}
	}
	
	@PutMapping("/updatetrainee")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String update(@RequestBody Training trainings){
		Optional<Training>_leave = trainingservice.updateData(trainings);
		if(_leave.isEmpty()) {
			return "The Leave Data Does Not Exist";
		}else {
			return "The Leave Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deletetrainee/{id}")
	@PreAuthorize("hasAuthority('ROLE_HR')")
	public String delete(@PathVariable("id") int id){
		return trainingservice.deleteData(id);
	}
}

