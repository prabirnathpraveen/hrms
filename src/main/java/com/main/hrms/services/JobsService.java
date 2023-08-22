package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.main.hrms.model.Jobs;
import com.main.hrms.repository.DepartmentRepository;
import com.main.hrms.repository.JobsRepository;

@Service
public class JobsService {
	
	@Autowired
	private JobsRepository jobsrepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Jobs> getData(){
		return jobsrepository.findAll();
	}
	
	public Optional<Jobs> getData(Integer id){
		return jobsrepository.findById(id);
	}
	
	public String deleteData(int id){
		if(jobsrepository.existsById(id)) { 
			jobsrepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
	
	public Optional<Jobs> insertData(Jobs jobs){
		if(jobsrepository.existsById(jobs.getJobid())){
			return Optional.empty();
		}
		else {
			return Optional.of(jobsrepository.save(jobs));
		}	
	}
	
	public boolean checkDepId(Jobs jobs) {
		if(departmentRepository.existsById(jobs.getDepartmentid())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public Optional<Jobs> updateData(Jobs jobs){
		if(jobsrepository.existsById(jobs.getJobid())){
			return Optional.of(jobsrepository.save(jobs));
		}
		else{
			return Optional.empty();
		}	
	}
}
