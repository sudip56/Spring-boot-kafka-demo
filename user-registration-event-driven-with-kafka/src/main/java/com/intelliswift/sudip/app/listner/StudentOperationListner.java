package com.intelliswift.sudip.app.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.CredentialDTO;
import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.service.CredentialGenaratorService;
import com.intelliswift.sudip.app.service.EmailService;
import com.intelliswift.sudip.app.service.EventLogManagementService;

@Component
public class StudentOperationListner {

	@Autowired
	private CredentialGenaratorService credentialGenaratorService;

	@Autowired
	private EmailService emailService;
	
	private static final Logger log = LoggerFactory.getLogger(StudentOperationListner.class);

	@KafkaListener(topics = {
			"student-operation3" }, groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consumer(StudentDTO studentDTO) {

		switch (studentDTO.getActivity()) {
		case "created":
			CredentialDTO credentialDTO = credentialGenaratorService.generateCredential(studentDTO);
			emailService.send(studentDTO.getEmail(), "Registration Successfully completed",
					"your user-name is:" + " " + credentialDTO.getUserName() + "\n" + "password is:" + " "
							+ credentialDTO.getPassword() + "\n" + "Registration request status:" + " " + "confirmed"
							+ "\n" + "pelease go to:" + " " + "localhost:8080/api/rest/login" + " " + "URL" + " "
							+ "for login");
			break;
		case "updated":
			emailService.send(studentDTO.getEmail(), "Student details updated",
					"your first name is:" + " " + studentDTO.getFirstName() + " " + "last name is:" + " "
							+ studentDTO.getLastName() + " " + "your email is:" + " " + studentDTO.getEmail());
			break;
		case "deleted":
			emailService.send(studentDTO.getEmail(), "Student deleted",
					"Student is deleted with id:" + " " + studentDTO.getStudentId());
		default:
			System.out.println("No Mail Send For this operation");

		}
	}
}
