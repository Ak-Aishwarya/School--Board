package com.school.sba.serviceImpl;

import java.sql.Time;
import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.exception.UserNotFound;
import com.school.sba.repository.ScheduleRepo;
import com.school.sba.repository.SchoolRepo;
import com.school.sba.request.ScheduleRequest;
import com.school.sba.response.ScheduleResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleRepo schedulerepo;

	@Autowired
	private ResponseStructure structure;
	
	@Autowired
	private SchoolRepo schoolrepo;

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> save(ScheduleRequest scheduleReq) {
		Schedule schedule=schedulerepo.save(mapToSchedule(scheduleReq));
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("schedule created");
		structure.setData(mapToScheduleResponse(schedule));
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure,HttpStatus.CREATED);
	}

	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
		return ScheduleResponse.builder().scheduleId(schedule.getScheduleId())
				.opensAt(schedule.getOpensAt())
				.lunchTime(schedule.getLunchTime())
				.lunchLengthInMin((int)(schedule.getLunchLengthInMin().toMinutesPart()))
				.closesAt(schedule.getClosesAt())
				.classHoursPerDay(schedule.getClassHoursPerDay())
				.classHourLengthInMin((int)(schedule.getClassHourLengthInMin().toMinutesPart()))
				.breakLengthInMin((int)(schedule.getBreakLengthInMin().toMinutesPart()))
				.breakTime(schedule.getBreakTime())
				.build();
	}

	private Schedule mapToSchedule(ScheduleRequest scheduleReq) {
		return Schedule.builder().opensAt(scheduleReq.getOpensAt())
				.lunchTime(scheduleReq.getLunchTime())
				.lunchLengthInMin(Duration.ofMinutes(scheduleReq.getLunchLengthInMin()))
				.closesAt(scheduleReq.getClosesAt())
				.classHoursPerDay(scheduleReq.getClassHoursPerDay())
				.classHourLengthInMin(Duration.ofMinutes(scheduleReq.getClassHourLengthInMin()))
				.breakLengthInMin(Duration.ofMinutes(scheduleReq.getBreakLengthInMin()))
				.breakTime(scheduleReq.getBreakTime())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> update(int scheduleId, Schedule schedule) {
		Schedule sc= schedulerepo.findById(scheduleId).map(u -> {
			schedule.setScheduleId(scheduleId);
			return schedulerepo.save(schedule);
		}).orElseThrow(() -> new RuntimeException("user id not found"));
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("user updated sucessfully");
		structure.setData(mapToScheduleResponse(sc));
		
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure, HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> fetchSchedule(int schoolId) {
	return schoolrepo.findById(schoolId).map(school->{
		ResponseStructure<ScheduleResponse> structure=new ResponseStructure<ScheduleResponse>();
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("schedule fetched successfully");
		structure.setData(mapToResponse(school.getScheduleId()));
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(structure,HttpStatus.FOUND);
	}).orElseThrow(()->new RuntimeException());	
	}

	private ScheduleResponse mapToResponse(Schedule scheduleId) {
		return ScheduleResponse.builder().scheduleId(scheduleId.getScheduleId())
				.opensAt(scheduleId.getOpensAt())
				.lunchTime(scheduleId.getLunchTime())
				.lunchLengthInMin((int)(scheduleId.getLunchLengthInMin().toMinutesPart()))
				.closesAt(scheduleId.getClosesAt())
				.classHoursPerDay(scheduleId.getClassHoursPerDay())
				.classHourLengthInMin((int)(scheduleId.getClassHourLengthInMin().toMinutesPart()))
				.breakLengthInMin((int)(scheduleId.getBreakLengthInMin().toMinutesPart()))
				.breakTime(scheduleId.getBreakTime())
				.build();
				
	}

	
}




