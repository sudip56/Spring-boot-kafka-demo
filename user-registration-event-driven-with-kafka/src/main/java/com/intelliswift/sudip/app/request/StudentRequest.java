package com.intelliswift.sudip.app.request;

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
public class StudentRequest {

	private int studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String status;
}
