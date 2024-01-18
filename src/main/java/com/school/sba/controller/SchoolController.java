package com.school.sba.controller;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.School;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;
import com.school.sba.request.SchoolRequest;
import com.school.sba.response.SchoolResponse;


@RestController
public class SchoolController {
	
	
		@Autowired
		private SchoolService ser;
		
		
		@PostMapping("users/{userId}/schools")
		public ResponseEntity<ResponseStructure<SchoolResponse>> createSchool(@PathVariable int userId,@RequestBody SchoolRequest school) {
			return ser.createSchool(userId,school);
		}

}
