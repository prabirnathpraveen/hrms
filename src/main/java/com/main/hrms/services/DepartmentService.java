package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Department;
import com.main.hrms.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getData(){
		return departmentRepository.findAll();
	}
	
	public Optional<Department> getData(Integer id){
		return departmentRepository.findById(id);
	}
	
	public List<Department> getDepartmentsByName(String name) {
        return departmentRepository.findByDepartmentname(name);
    }

    public List<Department> getDepartmentsByLocation(String location) {
        return departmentRepository.findByLocation(location);
    }
	
	public Optional<Department> insertData(Department department){
		if(departmentRepository.existsById(department.getDepartmentid())){
			return Optional.empty();
		}
		else {
			return Optional.of(departmentRepository.save(department));
		}	
	}
	
	public Optional<Department> updateData(Department department){
		if(departmentRepository.existsById(department.getDepartmentid())){
			return Optional.of(departmentRepository.save(department));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(departmentRepository.existsById(id)) { 
			departmentRepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}
