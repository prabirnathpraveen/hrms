package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.LeaveType;
import com.main.hrms.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	@Autowired
	private LeaveTypeRepository leavetyperepository;
	
	public List<LeaveType> getData(){
		return leavetyperepository.findAll();
	}
	
	public Optional<LeaveType> getData(Integer id){
		return leavetyperepository.findById(id);
	}
	
	public String deleteData(int id){
		if(leavetyperepository.existsById(id)) { 
			leavetyperepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
	
	public Optional<LeaveType> insertData(LeaveType leavetype){
		if(leavetyperepository.existsById(leavetype.getLeavetypeid())){
			return Optional.empty();
		}
		else {
			return Optional.of(leavetyperepository.save(leavetype));
		}	
	}
	
	public Optional<LeaveType> updateData(LeaveType leavetype){
		if(leavetyperepository.existsById(leavetype.getLeavetypeid())){
			return Optional.of(leavetyperepository.save(leavetype));
		}
		else{
			return Optional.empty();
		}	
	}
}
