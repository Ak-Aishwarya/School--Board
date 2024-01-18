package com.school.sba.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.SchoolRepo;
import com.school.sba.repository.UserRepo;
import com.school.sba.request.SchoolRequest;
import com.school.sba.response.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;
import com.school.sba.exception.SchoolNotFoundException;
import com.school.sba.exception.UserNotFound;

@Service
public class SchoolSerImpl implements SchoolService{
@Autowired
private SchoolRepo repo;
@Autowired
private UserRepo userrepo;

@Autowired
private ResponseStructure<SchoolResponse> rs;


@Override
public ResponseEntity<ResponseStructure<SchoolResponse>> createSchool(int userId, SchoolRequest school) {
	return userrepo.findById(userId).map(u->{
		if(u.getUserRole().equals(UserRole.ADMIN)) {
			if(u.getSchool()==null) {
				School sc=maptoSchool(school);
				sc=repo.save(sc);
				u.setSchool(sc);
				userrepo.save(u);
				
				rs.setStatus(HttpStatus.CREATED.value());
				rs.setMessage("School object created successfully");
				rs.setData(maptoSchool(sc));
				
				return new ResponseEntity<ResponseStructure<SchoolResponse>>(rs,HttpStatus.CREATED);
			}
			else
				throw new SchoolNotFoundException("school not created");
				
			}else
				throw new RuntimeException();
	}).orElseThrow(()->new UserNotFound("user not found"));
}


private SchoolResponse maptoSchool(School sc) {
	
	return SchoolResponse.builder().schoolName(sc.getSchoolName())
			.schoolId(sc.getSchoolId())
			.address(sc.getAddress())
			.contactNo(sc.getContactNo())
			.email(sc.getEmail()).build();
}


private School maptoSchool(SchoolRequest school) {
	return School.builder().schoolName(school.getSchoolName())
			.address(school.getAddress())
			.contactNo(school.getContactNo())
			.email(school.getEmail())
			.build();
				
}
}


