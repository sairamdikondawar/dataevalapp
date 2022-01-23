package com.dataeval.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.SubmitFormModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.UserFormService;

@RestController
@RequestMapping("/api/v1/userform-config")
public class UserFormController {

	private static final Logger log = LoggerFactory.getLogger(UserFormController.class);

	@Autowired
	private UserFormService userFormService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody SubmitFormModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create Question API");
		try {
			model = userFormService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("question.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = "Success";
		String localizedErrorMessage = messageSource.getMessage("question.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

}
