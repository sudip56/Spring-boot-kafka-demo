package com.intelliswift.sudip.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliswift.sudip.app.entity.CredentialDetailsEntity;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialDetailsEntity, Integer> {
	
	public CredentialDetailsEntity findByUserNameAndPassword(String userName,String password);
}
