package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Attendance;
import com.main.hrms.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	public List<Attendance> getData(){
		return attendancerepository.findAll();
	}
	
	public Optional<Attendance> getData(Integer id){
		return attendancerepository.findById(id);
	}
	
	public Optional<Attendance> insertData(Attendance attendance){
		if(attendancerepository.existsById(attendance.getAttendanceid())){
			return Optional.empty();
		}
		else {
			return Optional.of(attendancerepository.save(attendance));
		}	
	}
	
	public Optional<Attendance> updateData(Attendance attendance){
		if(attendancerepository.existsById(attendance.getAttendanceid())){
			return Optional.of(attendancerepository.save(attendance));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(attendancerepository.existsById(id)) { 
			attendancerepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}
