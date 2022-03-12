package com.dataeval.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.ChangePasswordModel;
import com.dataeval.model.pojo.UserModel;
import com.dataeval.model.pojo.common.PatientSearchCriteria;
import com.dataeval.model.pojo.common.UserSearchQuery;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.UserService;

@RestController
@RequestMapping("/api/v1/user-config")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/users")
	public Page<UserModel> list(UserSearchQuery common) {
		try { 
			return userService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}
	
	@GetMapping("/patients")
	public Page<UserModel> patinetList(PatientSearchCriteria common) {
		try { 
			return userService.findAllPatients(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody UserModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create User API");
		try {
			model = userService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("user.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getUserName();
		String localizedErrorMessage = messageSource.getMessage("user.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@RequestBody UserModel model, @PathVariable Long id) {

		log.info("Inside update User API");
		try {
			model = userService.update(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("user.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getUserName();
		String localizedSuccessMessage = messageSource.getMessage("user.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}
	@PutMapping("/user/password")
	public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordModel model) {

		log.info("Inside update User API");
		try {
			model = userService.updatePassword(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("user.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = "";//model.getUserName();
		String localizedSuccessMessage = messageSource.getMessage("user.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		UserModel model = null;
		try {
			model = userService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("user.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable Integer id) {
		UserModel model = null;
		try {
			model = userService.getPatientById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("user.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

}
