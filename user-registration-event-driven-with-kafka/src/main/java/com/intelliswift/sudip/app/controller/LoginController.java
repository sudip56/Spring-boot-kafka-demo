package com.intelliswift.sudip.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliswift.sudip.app.service.StudentLoginService;
import com.intelliswift.sudip.app.service.StudentServiceImpl;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private StudentLoginService studentLoginService;
	
	private static final Logger log = LoggerFactory.getLogger(StudentLoginService.class);
	
	@PostMapping(value = "/{userName}/{password}")
	public String studentLogin(@PathVariable String userName, @PathVariable String password)
	{
		String msg = studentLoginService.getAuthenticated(userName,password);
		return msg;
	}
	
}
