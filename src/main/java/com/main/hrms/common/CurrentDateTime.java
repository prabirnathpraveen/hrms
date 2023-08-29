package com.main.hrms.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CurrentDateTime {
	public static LocalDate getFormattedDate() {
		 LocalDate currentDate = LocalDate.now();
        return currentDate;
    }
	
	public static LocalTime getFormattedTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        LocalTime formattedTime = LocalTime.of(
            myDateObj.getHour(),
            myDateObj.getMinute(),
            myDateObj.getSecond()
        );
        return formattedTime;
    }
	
}

