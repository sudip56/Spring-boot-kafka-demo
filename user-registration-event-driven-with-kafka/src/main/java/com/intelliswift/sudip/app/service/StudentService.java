package com.intelliswift.sudip.app.service;

import java.util.List;

import com.intelliswift.sudip.app.DTO.StudentDTO;

public interface StudentService {

	public StudentDTO registerStudent(StudentDTO studentDTO);

	public List<StudentDTO> getAllStudent();

	public StudentDTO getStudentByID(Integer id);

	public StudentDTO updateStudent(StudentDTO studentDTO);

	public String deleteStudentById(Integer id);
	
	
}
