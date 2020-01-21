package com.intelliswift.sudip.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.mapper.StudentMapper;
import com.intelliswift.sudip.app.request.StudentRequest;
import com.intelliswift.sudip.app.service.StudentService;

@RestController
@RequestMapping
public class StudentOperationController {

	@Autowired
	private StudentService service;

	@Autowired
	StudentMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(StudentOperationController.class);

	@GetMapping("/welcome")
	public String greetings() {
		return "Hello Server is up..";
	}

	@PostMapping(value = "/students")
	public String registerStudent(@RequestBody StudentRequest studentRequest, @RequestHeader HttpHeaders httpHeaders) {
		StudentDTO studentDTO = service.registerStudent(mapper.DTOMapper(studentRequest), httpHeaders);
		return "Thanks for registering with us, your student id is:" + " " + studentDTO.getStudentId() + " "
				+ "with status:" + " " + studentDTO.getStatus() + " "
				+ "please check your email for use-name and password";
	}

	@GetMapping(value = "/students/{userName}")
	public List<StudentDTO> getAllStudent(@RequestHeader HttpHeaders httpHeaders, @PathVariable String userName) {
		return service.getAllStudent(httpHeaders, userName);
	}

	@GetMapping(value = "/students/{id}/{userName}")
	public StudentDTO getStudentById(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders,
			@PathVariable String userName) {
		return service.getStudentByID(id, httpHeaders, userName);
	}

	@PutMapping(value = "/students/{userName}")
	public StudentDTO updateStudent(@RequestBody StudentRequest studentRequest, @RequestHeader HttpHeaders httpHeaders,
			@PathVariable String userName) {
		return service.updateStudent(mapper.DTOMapper(studentRequest), httpHeaders, userName);
	}

	@DeleteMapping(value = "/students/{id}/{userName}")
	public String deleteStudentById(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders,
			@PathVariable String userName) {
		return service.deleteStudentById(id, httpHeaders, userName);
	}
}
