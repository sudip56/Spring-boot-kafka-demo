package com.intelliswift.sudip.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.intelliswift.sudip.app.DAO.StudentDAO;
import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.entity.StudentEntity;
import com.intelliswift.sudip.app.mapper.StudentMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private StudentMapper mapper;
	
	@Autowired
	private KafkaTemplate<String, StudentDTO> kafkaTemplate;
	
	private static final String topic = "create-student-topic16";

	@Override
	public StudentDTO registerStudent(StudentDTO studentDTO) {
		StudentEntity entity = studentDAO.save(mapper.entityMapper(studentDTO));
		// using kafka producer push studentDTO to topic..
		kafkaTemplate.send(topic, mapper.DTOMapper(entity));
		return mapper.DTOMapper(entity);
	}

	@Override
	public List<StudentDTO> getAllStudent() {

		List<StudentDTO> listDTO = new ArrayList<>();
		List<StudentEntity> list = studentDAO.findAll();
		for (StudentEntity studentEntity : list) {
			listDTO.add(mapper.DTOMapper(studentEntity));
		}
		return listDTO;
	}

	@Override
	public StudentDTO getStudentByID(Integer id) {

		StudentDTO studentDTO = null;
		if (studentDAO.findById(id).isPresent()) {
			studentDTO = mapper.DTOMapper(studentDAO.findById(id).get());
		}
		return studentDTO;
	}

	@Override
	public StudentDTO updateStudent(StudentDTO studentDTO) {

		StudentEntity entity = null;
		Optional<StudentEntity> optional = studentDAO.findById(studentDTO.getStudentId());

		if (optional.isPresent()) {
			entity = optional.get();
		}
		entity.setFirstName(studentDTO.getFirstName());
		entity.setLastName(studentDTO.getLastName());
		entity.setEmail(studentDTO.getEmail());

		StudentEntity updatedentity = studentDAO.save(entity);
		return mapper.DTOMapper(updatedentity);
	}

	@Override
	public String deleteStudentById(Integer id) {

		studentDAO.deleteById(id);
		return "Student deleted with the id:" + " " + id;
	}
}
