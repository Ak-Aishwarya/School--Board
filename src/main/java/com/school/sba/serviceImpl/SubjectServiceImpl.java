package com.school.sba.serviceImpl;

import java.util.ArrayList;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.Subject;
import com.school.sba.repository.AcademicProgramRepo;
import com.school.sba.repository.SubjectRepo;
import com.school.sba.request.SubjectRequest;
import com.school.sba.response.AcademicProgramResponse;
import com.school.sba.response.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.util.ResponseStructure;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectRepo subjectRepo;
	
	@Autowired
	private ResponseStructure<AcademicProgramResponse> structure;
@Autowired
private AcademicProgramRepo academicrepo;

@Autowired
private ResponseStructure<List<SubjectResponse>> resStructure;

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> create(int programId, SubjectRequest subjectReq) {
		return academicrepo.findById(programId).map(program->{
			List<Subject> subjects=(program.getSubject()!=null)?program.getSubject():new ArrayList<Subject>();
			
			subjectReq.getSubjectName().forEach(name->{
				boolean isPresent=false;
				for(Subject sub:subjects) {
					isPresent= (name.equalsIgnoreCase(sub.getSubjectName())) ?true:false;
					if(!isPresent)break;
				}
				if(!isPresent)subjects.add(subjectRepo.findBySubjectName(name)
						.orElseGet(()->subjectRepo.save(Subject.builder().subjectName(name).build())));
		}
			);
		List<Subject> toBeRemoved=new ArrayList<Subject>();
		subjects.forEach(sub->{
			boolean isPresent=false;
			for(String name:subjectReq.getSubjectName()) {
				isPresent=(sub.getSubjectName().equalsIgnoreCase(name))?true:false;
				if(!isPresent)toBeRemoved.add(sub);
			}}
			);
		
				subjects.removeAll(toBeRemoved);
			program.setSubject(subjects);
			academicrepo.save(program);
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("updated the subject list to academic program");
			structure.setData(AcademicProgramServiceImpl.mapToResponse(program));
			return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure,HttpStatus.OK);
		}).orElseThrow();
	}

	@Override
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects() {
		List<Subject> findAll = subjectRepo.findAll();

		List<SubjectResponse> collect = findAll.stream()
				.map(u->mapToSubjectResponse(u))
				.collect(Collectors.toList());



		resStructure.setStatus(HttpStatus.FOUND.value());
		resStructure.setMessage(" sujects found successfully ");
		resStructure.setData(collect);

		return new ResponseEntity<ResponseStructure<List<SubjectResponse>>>(resStructure,HttpStatus.FOUND);
	}
	private SubjectResponse mapToSubjectResponse(Subject subject)
	{
		return SubjectResponse.builder()
				.subjectId(subject.getSubjectId())
				.subjectName(subject.getSubjectName())
				.build();

	}
}


