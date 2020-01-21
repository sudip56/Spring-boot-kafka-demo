package com.intelliswift.sudip.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "EVENT_LOGS")
public class EventLogEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5714752379033744146L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVENT_ID")
	private int id;

	@Column(name = "ACTIVITY_NAME")
	private String activityName;

	@Column(name = "ACTIVITY_TIMING", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date activityTime;
	
	@Column(name = "HOST_NAME")
	private String hostName;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="STUDENT_ID") private StudentEntity studentEntity;
	 */

}
