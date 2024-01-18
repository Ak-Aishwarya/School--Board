package com.school.sba.entity;

import java.sql.Time;
import java.time.Duration;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder
@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerDay;
	private Duration classHourLengthInMin;
	private LocalTime breakTime;
	private Duration breakLengthInMin;
	private LocalTime lunchTime;
	private Duration lunchLengthInMin;
	@OneToOne(mappedBy="scheduleId")
	private School school;


}

