package com.school.sba.entity;

import java.time.LocalTime;

import com.school.sba.enums.classStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
public class ClassHour {
@Id
private int classHourid;
private LocalTime beginsAt;
private LocalTime endsAt;
private int roomNo;
private classStatus cs;
}
