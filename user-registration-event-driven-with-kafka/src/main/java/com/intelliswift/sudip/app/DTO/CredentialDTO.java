package com.intelliswift.sudip.app.DTO;

import java.io.Serializable;

import com.intelliswift.sudip.app.entity.StudentEntity;

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
public class CredentialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6607565339813395063L;
	
	private int id;
	
	private String userName;
	
	private String password;

	private StudentDTO studentDTO;
}
