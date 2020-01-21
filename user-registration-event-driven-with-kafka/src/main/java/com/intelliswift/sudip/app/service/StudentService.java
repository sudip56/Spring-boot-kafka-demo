package com.intelliswift.sudip.app.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.intelliswift.sudip.app.DTO.StudentDTO;

public interface StudentService {

	public StudentDTO registerStudent(StudentDTO studentDTO, HttpHeaders httpHeaders);

	public List<StudentDTO> getAllStudent(HttpHeaders httpHeaders, String userName);

	public StudentDTO getStudentByID(Integer id, HttpHeaders httpHeaders, String userName);

	public StudentDTO updateStudent(StudentDTO studentDTO, HttpHeaders httpHeaders, String userName);

	public String deleteStudentById(Integer id, HttpHeaders httpHeaders, String userName);
	
}
