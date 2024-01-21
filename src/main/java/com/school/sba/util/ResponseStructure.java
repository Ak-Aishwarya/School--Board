package com.school.sba.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.school.sba.response.SubjectResponse;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T> {
 private int status;
 private String message;
 private T data;
}
