package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer > {

}
