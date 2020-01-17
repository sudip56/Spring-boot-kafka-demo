package com.intelliswift.sudip.app.service;

import com.intelliswift.sudip.app.DTO.CredentialDTO;
import com.intelliswift.sudip.app.DTO.StudentDTO;

public interface CredentialGenaratorService {

	public CredentialDTO generateCredential(StudentDTO studentDTO);
	
}
