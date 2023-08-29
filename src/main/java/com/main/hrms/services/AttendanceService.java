package com.main.hrms.services;

import java.time.Duration;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.common.CurrentDateTime;
import com.main.hrms.model.Attendance;
import com.main.hrms.model.RequestMeta;
import com.main.hrms.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendancerepository;
	
	@Autowired
    private RequestMeta requestMeta;
	
	public List<Attendance> getData(){
		return attendancerepository.findAll();
	}
	
	public Optional<Attendance> getData(Integer id){
		return attendancerepository.findById(id);
	}
	
	public Optional<Attendance> insertData(Attendance attendance){
		List<Attendance> attendanceData=attendancerepository.findByEmployeeidAndDate(requestMeta.getUserid(),CurrentDateTime.getFormattedDate());
		if(!attendanceData.isEmpty()){
			return Optional.empty();
		}
		else {
			attendance.setEmployeeid(requestMeta.getUserid());
			attendance.setDate(CurrentDateTime.getFormattedDate());
			attendance.setCheckintime(CurrentDateTime.getFormattedTime());
			System.out.println("Time:          " + CurrentDateTime.getFormattedTime());
			return Optional.of(attendancerepository.save(attendance));
		}	
	}
	
	public Optional<Attendance> updateData(Attendance attendance){
		List<Attendance> attendanceData=attendancerepository.findByEmployeeidAndDate(requestMeta.getUserid(),CurrentDateTime.getFormattedDate());
		Attendance att = attendanceData.get(0);
		if(!attendanceData.isEmpty() && att.getCheckouttime()==null){
			attendance.setEmployeeid(att.getEmployeeid());;
			attendance.setAttendanceid(att.getAttendanceid());
			attendance.setCheckintime(att.getCheckintime());
			attendance.setCheckouttime(CurrentDateTime.getFormattedTime());
			Duration duration = Duration.between(att.getCheckintime(), CurrentDateTime.getFormattedTime());
			 long seconds = duration.getSeconds();
		     int hours = (int) (seconds / 3600);
		     if(hours>9) {
		    	 attendance.setIspresent(true);
		     }
		     int minutes = (int) ((seconds % 3600) / 60);
		     int remainingSeconds = (int) (seconds % 60);  
		     String workingHours = "%02d:%02d:%02d".formatted(hours, minutes, remainingSeconds);
		    attendance.setWorkinghours(workingHours);
			attendance.setDate(att.getDate());
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
