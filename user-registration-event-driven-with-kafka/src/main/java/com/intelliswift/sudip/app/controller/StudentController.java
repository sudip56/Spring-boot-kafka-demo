package com.intelliswift.sudip.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.mapper.StudentMapper;
import com.intelliswift.sudip.app.request.StudentRequest;
import com.intelliswift.sudip.app.service.StudentService;

@RestController
@RequestMapping
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	StudentMapper mapper;

	@GetMapping("/welcome")
	public String greetings() {
		return "Hello Server is up..";
	}

	@PostMapping(value = "/students")
	public String registerStudent(@RequestBody StudentRequest studentRequest) {
		StudentDTO studentDTO = service.registerStudent(mapper.DTOMapper(studentRequest));
		return "Thanks for registering with us, your student id is:" + " " + studentDTO.getStudentId() + " "
				+ "with status:" + " " + studentDTO.getStatus() + " "
				+ "please check your email for use-name and password";
	}

	@GetMapping(value = "/students")
	public List<StudentDTO> getAllStudent() {
		return service.getAllStudent();
	}

	@GetMapping(value = "/students/{id}")
	public StudentDTO getStudentById(@PathVariable Integer id) {
		return service.getStudentByID(id);
	}

	@PutMapping(value = "/students")
	public StudentDTO updateStudent(@RequestBody StudentRequest studentRequest) {
		return service.updateStudent(mapper.DTOMapper(studentRequest));
	}

	@DeleteMapping(value = "/students/{id}")
	public String deleteStudentById(@PathVariable Integer id) {
		return service.deleteStudentById(id);
	}
}
