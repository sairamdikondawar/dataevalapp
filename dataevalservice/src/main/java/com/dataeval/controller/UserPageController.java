package com.dataeval.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.UserPageModel;
import com.dataeval.service.UserPageService;

@RestController
@RequestMapping("/api/v1/userpage-config")
public class UserPageController {

	@Autowired
	private UserPageService userPageService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping
	public List<UserPageModel> list() {
		try {
			return userPageService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.asList(null);
	}

	@GetMapping("/userform/{id}")
	public List<UserPageModel> listByFormId(@PathVariable Integer id) {
		try {
			return userPageService.findAll(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.asList(null);
	}

}
