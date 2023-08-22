package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Attendance;

public interface AttendanceRepository  extends JpaRepository<Attendance, Integer > {

}

