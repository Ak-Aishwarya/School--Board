package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.Schedule;
import com.school.sba.request.ScheduleRequest;
import com.school.sba.response.ScheduleResponse;
import com.school.sba.util.ResponseStructure;

public interface ScheduleService {

	public ResponseEntity<ResponseStructure<ScheduleResponse>> save(ScheduleRequest schedule);

	public ResponseEntity<ResponseStructure<ScheduleResponse>> update(int scheduleId, Schedule schedule);

	public ResponseEntity<ResponseStructure<ScheduleResponse>> fetchSchedule(int schoolId);

		
}
