package com.intelliswift.sudip.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "CREDENTIAL_DETAILS")
public class CredentialDetailsEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -293386649578964718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CREDENTAIL_ID")
	private int id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "credentialDetailsEntity")
	private StudentEntity studentEntity;

}
