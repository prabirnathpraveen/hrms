package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Salary;

public interface SalaryRepository  extends JpaRepository<Salary, Integer > {

}
