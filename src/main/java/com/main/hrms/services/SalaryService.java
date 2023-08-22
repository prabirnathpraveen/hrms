package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Salary;
import com.main.hrms.repository.SalaryRepository;

@Service
public class SalaryService {
	
	@Autowired
	private SalaryRepository salaryrepository;
	
	public List<Salary> getData(){
		return salaryrepository.findAll();
	}
	
	public Optional<Salary> getData(Integer id){
		return salaryrepository.findById(id);
	}
	
	public Optional<Salary> insertData(Salary salary){
		if(salaryrepository.existsById(salary.getSalaryid())){
			return Optional.empty();
		}
		else {
			return Optional.of(salaryrepository.save(salary));
		}	
	}
	
	public Optional<Salary> updateData(Salary salary){
		if(salaryrepository.existsById(salary.getSalaryid())){
			return Optional.of(salaryrepository.save(salary));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(salaryrepository.existsById(id)) { 
			salaryrepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}