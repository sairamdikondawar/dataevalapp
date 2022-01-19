package com.dataeval.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.common.AuthenticationBean;

//@CrossOrigin(origins={"http://localhost:4200", "http://localhost:3200"})
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean helloWorldBean() {
		return new AuthenticationBean("You are authenticated");
	}	
}
