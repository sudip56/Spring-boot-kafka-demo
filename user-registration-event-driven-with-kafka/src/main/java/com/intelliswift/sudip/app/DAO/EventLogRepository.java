package com.intelliswift.sudip.app.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intelliswift.sudip.app.entity.EventLogEntity;

public interface EventLogRepository extends JpaRepository<EventLogEntity, Integer>{

	public List<EventLogEntity> findLogsByUserName(String userName);
}
