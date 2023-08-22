package com.main.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer > {
	Optional<Employee> findByEmailid(String email);
	Optional<Employee> findByFirstname(String name);
}
