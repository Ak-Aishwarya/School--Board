package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.User;
import com.school.sba.request.UserRequest;
import com.school.sba.response.UserResponse;
import com.school.sba.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> register(UserRequest request);

	ResponseEntity<ResponseStructure<UserResponse>> update(User user, int userId);

	ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

}
