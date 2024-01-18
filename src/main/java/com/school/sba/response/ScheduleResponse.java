package com.school.sba.response;

import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private int classHourLengthInMin;
	private LocalTime breakTime;
	private int breakLengthInMin;
	private LocalTime lunchTime;
	private int lunchLengthInMin;
}
