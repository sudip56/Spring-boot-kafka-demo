package com.intelliswift.sudip.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.intelliswift.sudip.app.DAO.StudentRepository;
import com.intelliswift.sudip.app.DTO.EventLogDTO;
import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.controller.StudentOperationController;
import com.intelliswift.sudip.app.entity.StudentEntity;
import com.intelliswift.sudip.app.mapper.StudentMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentDAO;

	@Autowired
	private StudentMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final String basic_card_opearion_topic = "student-operation3";
	private static final String event_log_management_topic = "event-log-management4";

	@Override
	public StudentDTO registerStudent(StudentDTO studentDTO, HttpHeaders httpHeaders) {
		StudentEntity entity = studentDAO.save(mapper.entityMapper(studentDTO));
		
		StudentDTO operationDTO = mapper.DTOMapper(entity);
		operationDTO.setActivity("created");
		// using kafka producer push studentDTO to topic..
		kafkaTemplate.send(basic_card_opearion_topic, operationDTO);

		EventLogDTO eventLogDTO = new EventLogDTO();
		eventLogDTO.setActivityName("registering student");
		eventLogDTO.setHostName(httpHeaders.getHost().toString());
		eventLogDTO.setUserName(mapper.DTOMapper(entity).getFirstName());

		kafkaTemplate.send(event_log_management_topic, eventLogDTO);

		return mapper.DTOMapper(entity);
	}

	@Override
	public List<StudentDTO> getAllStudent(HttpHeaders httpHeaders, String userName) {

		List<StudentDTO> listDTO = new ArrayList<>();
		List<StudentEntity> list = studentDAO.findAll();
		for (StudentEntity studentEntity : list) {
			listDTO.add(mapper.DTOMapper(studentEntity));
		}

		EventLogDTO eventLogDTO = new EventLogDTO();
		eventLogDTO.setActivityName("get all student");
		eventLogDTO.setHostName(httpHeaders.getHost().toString());
		eventLogDTO.setUserName(userName);

		kafkaTemplate.send(event_log_management_topic, eventLogDTO);

		return listDTO;
	}

	@Override
	public StudentDTO getStudentByID(Integer id, HttpHeaders httpHeaders, String userName) {

		StudentDTO studentDTO = null;
		if (studentDAO.findById(id).isPresent()) {
			studentDTO = mapper.DTOMapper(studentDAO.findById(id).get());
		}

		EventLogDTO eventLogDTO = new EventLogDTO();
		eventLogDTO.setActivityName("get student by specific Id");
		eventLogDTO.setHostName(httpHeaders.getHost().toString());
		eventLogDTO.setUserName(userName);

		kafkaTemplate.send(event_log_management_topic, eventLogDTO);

		return studentDTO;
	}

	@Override
	public StudentDTO updateStudent(StudentDTO studentDTO, HttpHeaders httpHeaders, String userName) {

		StudentEntity entity = new StudentEntity();
		Optional<StudentEntity> optional = studentDAO.findById(studentDTO.getStudentId());

		if (optional.isPresent() && !optional.isEmpty()) {
			entity = optional.get();
			entity.setFirstName(studentDTO.getFirstName());
			entity.setLastName(studentDTO.getLastName());
			entity.setEmail(studentDTO.getEmail());
		}
		StudentEntity updatedentity = studentDAO.save(entity);
		
		StudentDTO operationDTO = mapper.DTOMapper(updatedentity);
		operationDTO.setActivity("updated");
		
		kafkaTemplate.send(basic_card_opearion_topic, operationDTO);

		EventLogDTO eventLogDTO = new EventLogDTO();
		eventLogDTO.setActivityName("update student by student id");
		eventLogDTO.setHostName(httpHeaders.getHost().toString());
		eventLogDTO.setUserName(userName);

		kafkaTemplate.send(event_log_management_topic, eventLogDTO);

		return mapper.DTOMapper(updatedentity);
	}

	@Override
	public String deleteStudentById(Integer id, HttpHeaders httpHeaders, String userName) {
		
		StudentEntity studentEntity = new StudentEntity();
		
		Optional<StudentEntity> optionalStudentEntity = studentDAO.findById(id);
		if(!optionalStudentEntity.isEmpty() && optionalStudentEntity.isPresent())
		{
			studentEntity = optionalStudentEntity.get();
		}
		if(studentEntity != null)
		{
			studentDAO.delete(studentEntity);
		}
		StudentDTO operationDTO = mapper.DTOMapper(studentEntity);
		operationDTO.setActivity("deleted");
		
		kafkaTemplate.send(basic_card_opearion_topic, operationDTO);
		
		EventLogDTO eventLogDTO = new EventLogDTO();
		eventLogDTO.setActivityName("delete student by id");
		eventLogDTO.setHostName(httpHeaders.getHost().toString());
		eventLogDTO.setUserName(userName);

		kafkaTemplate.send(event_log_management_topic, eventLogDTO);

		return "Student deleted with the id:" + " " + id;
	}
}
