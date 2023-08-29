package com.main.hrms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Attendance;

public interface AttendanceRepository  extends JpaRepository<Attendance, Integer > {
	List<Attendance> findByEmployeeidAndDate(int userId, LocalDate date);
}
