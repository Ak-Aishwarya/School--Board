package com.school.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.sba.entity.User;
import com.school.sba.request.UserRequest;
import com.school.sba.response.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

public class UserController {
@Autowired
private UserService service;

@PostMapping("/user")
public ResponseEntity<ResponseStructure<UserResponse>> register(@RequestBody UserRequest request) {
	return service.register(request);
}
@PutMapping("/user/{userId}")
public  ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userId,@RequestBody User user){
	return service.update(user,userId);
}

@GetMapping("/user/{userId}")
public ResponseEntity<ResponseStructure<UserResponse>> fetchUserById(@PathVariable int userId) {
	return service.fetchUserById(userId);
}
@DeleteMapping("/user/{userId}")
public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
	return service.deleteUser(userId);
}
}
