package com.school.sba.response;

import com.school.sba.request.SchoolRequest;

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
public class SchoolResponse {
	private int schoolId;
	private String schoolName;
	private long contactNo;
	private String email;
	private String address;
}
