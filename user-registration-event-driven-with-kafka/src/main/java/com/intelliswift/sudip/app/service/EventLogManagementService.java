package com.intelliswift.sudip.app.service;

import java.util.List;

import com.intelliswift.sudip.app.DTO.EventLogDTO;

public interface EventLogManagementService {

	public void logStudentActivityEvent(EventLogDTO eventLogDTO);

	public List<EventLogDTO> getActivityDetailsByUserName(String userName);
	
	
}
