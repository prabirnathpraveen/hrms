package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Leaves;

public interface LeaveRepository extends JpaRepository<Leaves, Integer > {

}
