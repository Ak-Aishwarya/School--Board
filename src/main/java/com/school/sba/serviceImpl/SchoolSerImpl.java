package com.school.sba.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.repository.SchoolRepo;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;
import com.school.sba.exception.SchoolNotFoundException;

@Service
public class SchoolSerImpl implements SchoolService{
@Autowired
private SchoolRepo repo;

@Override
public ResponseEntity<ResponseStructure<School>> addSchool(School school) {
School s=repo.save(school);
	
	ResponseStructure<School> rs=new ResponseStructure<>();
	rs.setStatus(HttpStatus.CREATED.value());
	rs.setMessage("Student object created successfully");
	rs.setData(s);
	
	return new ResponseEntity<ResponseStructure<School>>(rs,HttpStatus.CREATED);
}

@Override
public ResponseEntity<ResponseStructure<School>> findSchool(int schoolId) {
	Optional<School> op=repo.findById(schoolId);
	if(op.isPresent()) {
		School s1=op.get();
		ResponseStructure<School> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.FOUND.value());
		rs.setMessage("School data found");
		rs.setData(s1);
		return new ResponseEntity<ResponseStructure<School>>(rs,HttpStatus.FOUND);
	}
	else {
		 throw new SchoolNotFoundException("School not found");
	}
}

@Override
public ResponseEntity<ResponseStructure<School>> updateStu(int schoolId, School upStu) {
	Optional<School>op=repo.findById(schoolId);
	if(op.isPresent()) {
		School exStu=op.get();
		upStu.setSchoolId(exStu.getSchoolId());
		School student=repo.save(upStu);
		
		ResponseStructure<School> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student data updated");
		rs.setData(student);
		
		return new ResponseEntity<ResponseStructure<School>>(rs,HttpStatus.OK);
	}
	else {
		throw new SchoolNotFoundException("Student not found");
}
}

@Override
public ResponseEntity<ResponseStructure<School>> deleteStu(int schoolId) {
	Optional<School>op=repo.findById(schoolId);
	if(op.isPresent()) {
		School stu=op.get();
		repo.delete(stu);
		
		ResponseStructure<School> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student data deleted");
		rs.setData(stu);
		return new ResponseEntity<ResponseStructure<School>>(rs,HttpStatus.OK);
	}
	else {
		throw new SchoolNotFoundException("Student not found");
}
}

}
