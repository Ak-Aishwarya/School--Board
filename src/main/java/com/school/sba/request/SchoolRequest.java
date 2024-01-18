package com.school.sba.request;

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
public class SchoolRequest {
	private String schoolName;
	private long contactNo;
	private String email;
	private String address;
}
