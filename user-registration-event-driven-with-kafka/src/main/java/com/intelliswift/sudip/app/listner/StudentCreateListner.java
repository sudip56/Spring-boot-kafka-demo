package com.intelliswift.sudip.app.listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.CredentialDTO;
import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.service.CredentialGenaratorService;
import com.intelliswift.sudip.app.service.EmailService;

@Component
public class StudentCreateListner {

	@Autowired
	private CredentialGenaratorService credentialGenaratorService;

	@Autowired
	private EmailService emailService;

	@KafkaListener(topics = {
			"create-student-topic16" }, groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consumer(StudentDTO studentDTO) {
		CredentialDTO credentialDTO = credentialGenaratorService.generateCredential(studentDTO);
		emailService.send(studentDTO.getEmail(), "Registration Successfully completed",
				"your user-name is:" + " " + credentialDTO.getUserName() + "\n" + "password is:" + " "
						+ credentialDTO.getPassword() + "\n" + "Registration request status:" + " "
						+ "confirmed" + "\n" + "pelease go to:" + " "
						+ "localhost:8080/api/rest/login" + " " + "URL" + " " + "for login");

		System.out.println("inside listner::" + studentDTO.toString());
	}
}
