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

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.Subject;
import com.school.sba.request.SubjectRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.response.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.util.ResponseStructure;

import lombok.Getter;

@RestController
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	@PostMapping("/academic-programs/{programId}/subjects")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> create(@PathVariable int programId,@RequestBody SubjectRequest subject){
		return subjectService.create(programId,subject);
	}

	@GetMapping("/subjects")
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects(){
		return subjectService.findAllSubjects();
	}
}
