package com.intelliswift.sudip.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "STUDENT_DETAILS")
public class StudentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6381128168572227637L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private int studentId;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "STATUS", columnDefinition = "varchar(255) default 'in-progress'")
	private String status = "in-progress";

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credential_id")
	private CredentialDetailsEntity credentialDetailsEntity;

}
