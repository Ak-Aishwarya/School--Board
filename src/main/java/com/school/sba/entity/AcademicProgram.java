package com.school.sba.entity;

import java.time.LocalTime;
import java.util.List;

import com.school.sba.enums.programType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AcademicProgram {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int programId;
private programType programType;
private String programName;
private LocalTime beginsAt;
private LocalTime endsAt;
@ManyToOne
private School school;
@ManyToMany
private List<Subject> subject;
@ManyToMany
private List<User> user;
}
