package com.intelliswift.sudip.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliswift.sudip.app.DTO.EventLogDTO;
import com.intelliswift.sudip.app.service.EventLogManagementService;

@RestController
@RequestMapping(value = "/activity")
public class ActivityCheckController {
	
	@Autowired
	private EventLogManagementService eventLogManagementServiceImpl;
	
	private static final Logger log = LoggerFactory.getLogger(EventLogManagementService.class);
	
	@GetMapping(value = "/{userName}")
	public List<EventLogDTO> getActivityDetailsByUserName(@PathVariable String userName)
	{
		List<EventLogDTO> eventLogDTOlist = eventLogManagementServiceImpl.getActivityDetailsByUserName(userName);
		return eventLogDTOlist;
	}
}
