package com.school.sba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject {
@Id
private int subjectId;
private String subjectName;
}
