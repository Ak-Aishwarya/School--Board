package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.Subject;
import com.school.sba.request.SubjectRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.response.SubjectResponse;
import com.school.sba.util.ResponseStructure;

public interface SubjectService {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> create(int programId, SubjectRequest subject);

	ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects();

	//ResponseEntity<ResponseStructure<AcademicProgramResponse>> update(int programId, SubjectRequest subject);

}
