package com.school.sba.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.exception.UserNotFound;
import com.school.sba.entity.User;
import com.school.sba.repository.UserRepo;
import com.school.sba.request.UserRequest;
import com.school.sba.response.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userrepo;

	@Autowired
	private ResponseStructure responseStructure;

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
		return User.builder().userName(request.getUsername()).email(request.getEmail())
				.password(request.getPassword()).build();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userName(user.getUserName()).email(user.getEmail())
				.build();
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(int userId) {
		User user=userrepo.findById(userId).orElseThrow(()-> new UserNotFound(" Id Not found") );

		//ResponseStructure  responseStructure=new ResponseStructure();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		User user = userrepo.findById(userId).orElseThrow(()-> new UserNotFound("User id doesn't exist") );
		userrepo.delete(user);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user Deleted sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> update(User updateduser, int userId) {
		User user = userrepo.findById(userId).map(u -> {
			updateduser.setUserId(userId);
			return userrepo.save(updateduser);
		}).orElseThrow(() -> new UserNotFound("user id not found"));
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user updated sucessfully");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}





}
