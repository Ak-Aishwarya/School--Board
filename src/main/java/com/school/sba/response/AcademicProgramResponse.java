package com.school.sba.response;

import java.time.LocalTime;
import java.util.List;

import com.school.sba.entity.Subject;
import com.school.sba.enums.programType;

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
public class AcademicProgramResponse {
	private int programId;
	private programType programType;
	private String programName;
	private LocalTime beginsAt;
	private LocalTime endsAt;
	private List<String> subject;
}
