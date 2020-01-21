package com.intelliswift.sudip.app.DTO;

import java.io.Serializable;
import java.util.Date;

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
public class EventLogDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4177702911653311822L;

	private int id;

	private String activityName;

	private Date activityTime;
	
	private String hostName;

	private String userName;

}
