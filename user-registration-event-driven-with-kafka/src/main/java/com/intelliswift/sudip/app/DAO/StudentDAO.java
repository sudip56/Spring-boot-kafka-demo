package com.intelliswift.sudip.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intelliswift.sudip.app.entity.StudentEntity;

@Repository
public interface StudentDAO extends JpaRepository<StudentEntity, Integer> {

}
