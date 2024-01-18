package com.school.sba.request;

import java.time.LocalTime;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
	 @Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
		private LocalTime opensAt;
	 @Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
		private LocalTime closesAt;
		private int classHoursPerDay;
		private int classHourLengthInMin;
		@Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
		private LocalTime breakTime;
		private int breakLengthInMin;
		@Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
		private LocalTime lunchTime;
		private int lunchLengthInMin;
		
}
