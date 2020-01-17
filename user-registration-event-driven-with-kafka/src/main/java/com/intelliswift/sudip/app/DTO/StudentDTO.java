package com.intelliswift.sudip.app.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
public class StudentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9024502278863163339L;
	
	private int studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String status;
	private CredentialDTO credentialDTO;
		
}
