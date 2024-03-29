package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.request.AcademicRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.util.ResponseStructure;
@RestController
public class AcademicProgramController {
	@Autowired
	private AcademicProgramService academicProgramService;
	@PostMapping("/schools/{schoolId}/academicPrograms")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addsAcademicProgram(@PathVariable int schoolId, @RequestBody AcademicRequest academicProgramRequest){
		return academicProgramService.addsAcademicProgram(schoolId, academicProgramRequest);
	}
	@GetMapping("/schools/{schoolId}/academicPrograms")
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> fetchAllAcademicProgram(@PathVariable int schoolId){
		return academicProgramService.fetchAllAcademicProgram(schoolId);
	}
	
	
}
