package com.school.sba.service;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.util.ResponseStructure;

public interface SchoolService {
	public ResponseEntity<ResponseStructure<School>> addSchool(School student);
	public ResponseEntity<ResponseStructure<School>> findSchool(int schoolId);
	public ResponseEntity<ResponseStructure<School>> updateStu(int schoolId,School upStu);
	public ResponseEntity<ResponseStructure<School>> deleteStu(int schoolId);
}
