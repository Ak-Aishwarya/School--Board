package com.school.sba.service;
import java.util.List;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.util.ResponseStructure;
import com.school.sba.request.SchoolRequest;
import com.school.sba.response.SchoolResponse;
public interface SchoolService {
	
	public ResponseEntity<ResponseStructure<SchoolResponse>> createSchool(int userId, SchoolRequest school);

}
