package com.school.sba.request;

import java.time.LocalTime;

import com.school.sba.enums.programType;

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
public class AcademicRequest {
	
	private programType programType;
	private String programName;
	 @Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
	private LocalTime beginsAt;
	 @Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$",message="Invalid time format. Please use HH:MM")
	private LocalTime endsAt;
}
