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

import com.main.hrms.model.Department;
import com.main.hrms.services.DepartmentService;


@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentservice;
	
	@GetMapping("/department")
	public List<Department> getDepartmentDetails() {
		return departmentservice.getData();
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable Integer id) {
		Optional<Department> department = departmentservice.getData(id);
	    if (department.isPresent()) {
	        return new ResponseEntity<>(department.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	}
	
	@GetMapping("/department/name/{name}")
    public ResponseEntity<List<Department>> getDepartmentsByName(@PathVariable String name) {
        List<Department> departments = departmentservice.getDepartmentsByName(name);
        if (!departments.isEmpty()) {
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department/location/{location}")
    public ResponseEntity<List<Department>> getDepartmentsByLocation(@PathVariable String location) {
        List<Department> departments = departmentservice.getDepartmentsByLocation(location);
        if (!departments.isEmpty()) {
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	@PostMapping("/insertDepartment")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String insert(@RequestBody Department department){
		Optional<Department>_department = departmentservice.insertData(department);
		if(_department.isPresent()) {
			return "The Department Data Saved Successfully";
		}else {
			return "Department Already Exist";
		}
	}
	
	@PutMapping("/updateDepartment")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String update(@RequestBody Department department){
		Optional<Department>_department = departmentservice.updateData(department);
		if(_department.isEmpty()) {
			return "The Department Data Does Not Exist";
		}else {
			return "The Department Data Updated Successfully";
		}
	}
	
	
	@DeleteMapping("/deleteDepartment/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String delete(@PathVariable int id){
		return departmentservice.deleteData(id);
	}
}



