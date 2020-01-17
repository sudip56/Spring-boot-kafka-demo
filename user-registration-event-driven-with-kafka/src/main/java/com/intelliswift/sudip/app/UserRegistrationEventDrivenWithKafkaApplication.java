package com.intelliswift.sudip.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.service.StudentService;

@SpringBootApplication
public class UserRegistrationEventDrivenWithKafkaApplication implements CommandLineRunner {
	
	@Autowired
	private StudentService service;
	
	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationEventDrivenWithKafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("Sudip");
		studentDTO.setLastName("Banerjee");
		studentDTO.setEmail("sudipbanerjeecse@gmail.com");
		//String msg = service.registerStudent(studentDTO);
		
		//System.out.println(msg);
		
	}

}
