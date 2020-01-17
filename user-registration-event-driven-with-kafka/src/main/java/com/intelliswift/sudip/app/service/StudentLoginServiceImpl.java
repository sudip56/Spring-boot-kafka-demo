package com.intelliswift.sudip.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliswift.sudip.app.DAO.CredentialDAO;
import com.intelliswift.sudip.app.entity.CredentialDetailsEntity;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

	@Autowired
	private CredentialDAO credentialDAO;

	@Override
	public String getAuthenticated(String user, String pwd) {

		String resutl = "";
		CredentialDetailsEntity credentialDetailsEntity = credentialDAO.findByUserNameAndPassword(user, pwd);

		if (null != credentialDetailsEntity) {
			if (credentialDetailsEntity.getUserName().equalsIgnoreCase(user)
					&& credentialDetailsEntity.getPassword().equalsIgnoreCase(pwd)) {
				resutl = "Welcome to your Profile";
			} else {
				resutl = "Sorry invalid credential Try Again..!!!";
			}
		}
		return resutl;

	}

}
