package com.intelliswift.sudip.app.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.EventLogDTO;
import com.intelliswift.sudip.app.service.EventLogManagementService;

@Component
public class StudentActivityListner {

	@Autowired
	private EventLogManagementService eventLogManagementServiceImpl;
	
	private static final Logger log = LoggerFactory.getLogger(EventLogManagementService.class);

	@KafkaListener(topics = {
			"event-log-management4" }, groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void consumer(EventLogDTO eventLogDTO) {
		
		
		eventLogManagementServiceImpl.logStudentActivityEvent(eventLogDTO);

	}
}
