package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.entity.Schedule;
import com.school.sba.request.ScheduleRequest;
import com.school.sba.response.ScheduleResponse;
import com.school.sba.response.SchoolResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;

@RestController
public class ScheduleController {
@Autowired
private ScheduleService ser;

@PostMapping("/schools/{schoolId}/schedules")
public ResponseEntity<ResponseStructure<ScheduleResponse>> save(@RequestBody ScheduleRequest schedule){
	return ser.save(schedule);
}
@GetMapping("/schools/{schoolId}/schedules")
public ResponseEntity<ResponseStructure<ScheduleResponse>> fetchSchedule(@PathVariable int schoolId){
	return ser.fetchSchedule(schoolId);
}

@PutMapping("/schedules/{scheduleId}")
public ResponseEntity<ResponseStructure<ScheduleResponse>> update(@PathVariable int scheduleId,@RequestBody Schedule schedule){
	return ser.update(scheduleId,schedule);
}
}
