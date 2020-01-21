package com.intelliswift.sudip.app.service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliswift.sudip.app.DAO.CredentialRepository;
import com.intelliswift.sudip.app.DAO.StudentRepository;
import com.intelliswift.sudip.app.DTO.CredentialDTO;
import com.intelliswift.sudip.app.DTO.StudentDTO;
import com.intelliswift.sudip.app.entity.CredentialDetailsEntity;
import com.intelliswift.sudip.app.entity.StudentEntity;
import com.intelliswift.sudip.app.mapper.CredentialMapper;
import com.intelliswift.sudip.app.mapper.StudentMapper;

@Service
public class CredentialGenaratorServiceImpl implements CredentialGenaratorService {

	@Autowired
	private CredentialRepository credentialDAO;

	@Autowired
	private StudentRepository StudentDAO;

	@Autowired
	private CredentialMapper credentialMapper;

	@Autowired
	private StudentMapper studentMapper;
	
	private static final Logger log = LoggerFactory.getLogger(CredentialGenaratorServiceImpl.class);

	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	@Override
	public CredentialDTO generateCredential(StudentDTO studentDTO) {

		// Define desired password length
		int passwordLength = 10;
		// Generate Secure Password
		String password = generatePassword(passwordLength);
		// Print out password value
		System.out.println("Secure password: " + password);

		CredentialDTO credentialDTO = new CredentialDTO();
		credentialDTO.setUserName(studentDTO.getFirstName());
		credentialDTO.setPassword(password);
		// credentialDTO.setStudentDTO(studentDTO);

		CredentialDetailsEntity credentialDetailsEntity = credentialDAO
				.save(credentialMapper.entityMapper(credentialDTO));

		System.out.println("credentialEntity saved::" + credentialDetailsEntity.toString());

		Optional<StudentEntity> updatableStudent = StudentDAO.findById(studentDTO.getStudentId());

		if (updatableStudent.isPresent() && !updatableStudent.isEmpty()) {

			StudentEntity studentEntity = updatableStudent.get();
			studentEntity.setStatus("confirmed");
			studentEntity.setCredentialDetailsEntity(credentialDetailsEntity);
			try {
				StudentDAO.save(studentEntity);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return credentialDTO;

	}

	public static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
}
