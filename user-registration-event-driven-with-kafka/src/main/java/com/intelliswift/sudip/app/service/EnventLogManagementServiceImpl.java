package com.intelliswift.sudip.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliswift.sudip.app.DAO.EventLogRepository;
import com.intelliswift.sudip.app.DTO.EventLogDTO;
import com.intelliswift.sudip.app.mapper.EventMapper;

@Service
public class EnventLogManagementServiceImpl implements EventLogManagementService {

	@Autowired
	private EventLogRepository eventLogRepository;
	
	private static final Logger log = LoggerFactory.getLogger(EnventLogManagementServiceImpl.class);

	@Autowired
	private EventMapper eventMapper;

	@Override
	public void logStudentActivityEvent(EventLogDTO eventLogDTO) {
		eventLogRepository.save(eventMapper.getEntityMapper(eventLogDTO));
	}

	@Override
	public List<EventLogDTO> getActivityDetailsByUserName(String userName) {
		return eventMapper.getEventLogDTO(eventLogRepository.findLogsByUserName(userName));

	}
}
