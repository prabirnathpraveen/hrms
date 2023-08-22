package com.main.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	List<Department> findByDepartmentname(String name);
    List<Department> findByLocation(String location);
}
