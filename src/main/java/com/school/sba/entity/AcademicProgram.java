package com.school.sba.entity;

import java.time.LocalTime;

import com.school.sba.enums.programType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AcademicProgram {
@Id
private int programId;
private programType pt;
private String programName;
private LocalTime beginsAt;
private LocalTime endsAt;
}
