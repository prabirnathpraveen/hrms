package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Leaves;
import com.main.hrms.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	private LeaveRepository leaverepository;
	
	public List<Leaves> getData(){
		return leaverepository.findAll();
	}
	
	public Optional<Leaves> getData(Integer id){
		return leaverepository.findById(id);
	}
	
	public Optional<Leaves> insertData(Leaves leaves){
		if(leaverepository.existsById(leaves.getLeaveid())){
			return Optional.empty();
		}
		else {
			return Optional.of(leaverepository.save(leaves));
		}	
	}
	
	public Optional<Leaves> updateData(Leaves leaves){
		if(leaverepository.existsById(leaves.getLeaveid())){
			return Optional.of(leaverepository.save(leaves));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(leaverepository.existsById(id)) { 
			leaverepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}
