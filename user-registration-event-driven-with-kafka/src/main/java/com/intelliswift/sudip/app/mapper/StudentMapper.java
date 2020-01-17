package com.intelliswift.sudip.app.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.entity.StudentEntity;
import com.intelliswift.sudip.app.request.StudentRequest;

@Component
public class StudentMapper {
	
	@Autowired
	private CredentialMapper credentialMapper;

	// StudentRequest to StudentDTO mapper..
	public StudentDTO DTOMapper(StudentRequest request) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(request.getStudentId());
		studentDTO.setFirstName(request.getFirstName());
		studentDTO.setLastName(request.getLastName());
		studentDTO.setEmail(request.getEmail());

		return studentDTO;
	}

	// student DTO to Entity mapper..
	public StudentEntity entityMapper(StudentDTO studentDTO) {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setFirstName(studentDTO.getFirstName());
		studentEntity.setLastName(studentDTO.getLastName());
		studentEntity.setEmail(studentDTO.getEmail());
		if(null != studentDTO.getCredentialDTO())
		studentEntity.setCredentialDetailsEntity(credentialMapper.entityMapper(studentDTO.getCredentialDTO()));

		return studentEntity;
	}

	// StudentEntity to StudentDTO mapper..
	public StudentDTO DTOMapper(StudentEntity studentEntity) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(studentEntity.getStudentId());
		studentDTO.setFirstName(studentEntity.getFirstName());
		studentDTO.setLastName(studentEntity.getLastName());
		studentDTO.setEmail(studentEntity.getEmail());
		studentDTO.setStatus(studentEntity.getStatus());

		return studentDTO;
	}
}
