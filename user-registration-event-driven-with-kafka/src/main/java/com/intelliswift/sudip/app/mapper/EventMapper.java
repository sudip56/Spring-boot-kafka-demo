package com.intelliswift.sudip.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intelliswift.sudip.app.DTO.EventLogDTO;
import com.intelliswift.sudip.app.entity.EventLogEntity;

@Component
public class EventMapper {

	// EventLogDTO to EventLogEntity mapper..
	public EventLogEntity getEntityMapper(EventLogDTO eventLogDTO) {
		EventLogEntity eventLogEntity = new EventLogEntity();

		eventLogEntity.setActivityName(eventLogDTO.getActivityName());
		eventLogEntity.setHostName(eventLogDTO.getHostName());
		eventLogEntity.setUserName(eventLogDTO.getUserName());

		return eventLogEntity;
	}

	// EventLogEntity to EventLogDTO mapper..

	public List<EventLogDTO> getEventLogDTO(List<EventLogEntity> list) {
		List<EventLogDTO> eventDTOList = new ArrayList<>();
		EventLogDTO eventLogDTO = new EventLogDTO();

		for (EventLogEntity eventLogEntity : list) {
			
			eventLogDTO.setId(eventLogEntity.getId());
			eventLogDTO.setActivityName(eventLogEntity.getActivityName());
			eventLogDTO.setActivityTime(eventLogEntity.getActivityTime());
			eventLogDTO.setHostName(eventLogEntity.getHostName());
			eventLogDTO.setUserName(eventLogEntity.getUserName());
			eventDTOList.add(eventLogDTO);
		}
		return eventDTOList;
	}
}
