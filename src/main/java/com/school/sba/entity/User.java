package com.school.sba.entity;

import java.util.List;

import com.school.sba.enums.UserRole;


import jakarta.persistence.Column;
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
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int userId;
@Column(unique=true)
private String userName;
private String password;
private String firstName;
private String lastName;
private long contactNo;
@Column(unique=true)
private String email;
private UserRole userRole;
private boolean isDeleted;
@ManyToOne
private School school;
@ManyToMany
private List<AcademicProgram> academic;
@ManyToOne
private Subject subject;

}
