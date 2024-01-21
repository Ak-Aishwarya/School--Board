package com.school.sba.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.exception.UserNotFound;
import com.school.sba.controller.SchoolController;
import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.School;
import com.school.sba.entity.Subject;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.AcademicProgramRepo;
import com.school.sba.repository.SubjectRepo;
import com.school.sba.repository.UserRepo;
import com.school.sba.request.UserRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.response.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userrepo;

	@Autowired
	private ResponseStructure responseStructure;
	
	@Autowired
private AcademicProgramRepo academicRepo;
	
	@Autowired
	private SubjectRepo subRepo;
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> register(UserRequest request) {
		User user = userrepo.save(mapToUser(request));
		//ResponseStructure responseStructure = new ResponseStructure();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("user saved sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);


	}

	private User mapToUser(UserRequest request) {
		return User.builder().userName(request.getUsername())
				.userRole(request.getUserRole())
				.email(request.getEmail())
				.password(request.getPassword())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.contactNo(request.getContactNo())
				.build();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.contactNo(user.getContactNo())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.userRole(user.getUserRole())
				.build();
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(int userId) {
		User user=userrepo.findById(userId).orElseThrow(()-> new UserNotFound(" Id Not found"));

		//ResponseStructure  responseStructure=new ResponseStructure();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		User user = userrepo.findById(userId).orElseThrow(()-> new UserNotFound("User id doesn't exist") );
		user.setDeleted(true);
		userrepo.save(user);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user updated with true");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(int programId, int userId) {
		User user = userrepo.findById(userId)
				.orElseThrow(()-> new UserNotFound("user not found"));

		AcademicProgram academicProgram = academicRepo.findById(programId)
				.orElseThrow(()->new RuntimeException("AcademicProgam not found"));

		if(user.getUserRole().equals(UserRole.ADMIN))
		{
			throw new RuntimeException("admin cannot assign");
		}
		else
		{
			user.getAcademic().add(academicProgram);
			userrepo.save(user);
			academicProgram.getUser().add(user);
			academicRepo.save(academicProgram );

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("user associated with academic program successfully");
			responseStructure.setData(mapToUserResponse(user));


			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addSubjectToTeacher(int subjectId, int userId) {
		Subject subject =subRepo .findById(subjectId)
				.orElseThrow(()-> new RuntimeException("subject not found"));

		User user = userrepo.findById(userId)
				.orElseThrow(()-> new UserNotFound("user not found"));

		if(user.getUserRole().equals(UserRole.TEACHER))
		{

			user.setSubject(subject);
			userrepo.save(user);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("subject added to the teacher successfully");
			responseStructure.setData(mapToUserResponse(user));

			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);

		}
		else
		{
			throw new RuntimeException("user is not a teacher");
		}
	}


	}

	

	
	
	


	
	






