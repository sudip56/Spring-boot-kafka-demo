package com.intelliswift.sudip.app.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.CredentialDTO;
import com.intelliswift.sudip.app.entity.CredentialDetailsEntity;

@Component
public class CredentialMapper {
	
	@Autowired
	private StudentMapper mapper;

	// CredentialDTO to CredentialDetailsEntity mapper..
	public CredentialDetailsEntity entityMapper(CredentialDTO credentialDTO) {
		CredentialDetailsEntity credentialDetailsEntity = new CredentialDetailsEntity();
		credentialDetailsEntity.setUserName(credentialDTO.getUserName());
		credentialDetailsEntity.setPassword(credentialDTO.getPassword());
		if(null != credentialDTO.getStudentDTO())
		credentialDetailsEntity.setStudentEntity(mapper.entityMapper(credentialDTO.getStudentDTO()));

		return credentialDetailsEntity;
	}

	// CredentialDetailsEntity to CredentialDTO mapper..
		public CredentialDTO dtoMapper(CredentialDetailsEntity credentialDetailsEntity) {
			CredentialDTO credentialDTO = new CredentialDTO();
			credentialDTO.setId(credentialDetailsEntity.getId());
			credentialDTO.setUserName(credentialDetailsEntity.getUserName());
			credentialDTO.setPassword(credentialDetailsEntity.getPassword());
			
			return credentialDTO;
		}

}
