package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.sba.request.AcademicRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.util.ResponseStructure;


public interface AcademicProgramService {

	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addsAcademicProgram(int schoolId,
			AcademicRequest academicProgramRequest);

	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> fetchAllAcademicProgram(int schoolId);
}
