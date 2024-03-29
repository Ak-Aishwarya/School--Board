package com.school.sba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.Subject;
@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer>{

	Optional<Subject> findBySubjectName(String name);

}
