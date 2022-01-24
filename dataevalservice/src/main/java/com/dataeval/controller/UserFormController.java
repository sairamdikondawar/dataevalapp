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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.SubmitFormModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.UserFormService;
import com.dataeval.util.Util;

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

	@GetMapping
	public Page<UserFormModel> list(@RequestParam(required = false) String searchCriteria, Integer page, Integer size) {
		try {
			CommonCriteria common = Util.getObjectMapper().readValue(searchCriteria.toString(), CommonCriteria.class);
			common.setPage(page);
			common.setSize(size);

			return userFormService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

//	@PostMapping("/question")
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<?> create(@RequestBody UserFormModel model, BindingResult bindingResult) throws Exception {
//		log.info("Inside Create Question API");
//		try {
//			model = userFormService.create(model);
//		} catch (Exception e) {
//			String localizedErrorMessage = messageSource.getMessage("question.insert.unsuccessful", null,
//					currentLocale);
//			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
//			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
//		}
//		argumentsToReplace[0] = model.getName();
//		String localizedErrorMessage = messageSource.getMessage("question.insert.successful", argumentsToReplace,
//				currentLocale);
//		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
//		resp.setResource(model);
//		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
//	}

//	@PutMapping("/question/{id}")
//	public ResponseEntity<?> update(@RequestBody UserFormModel model, @PathVariable Long id) {
//
//		log.info("Inside update Question API");
//		try {
//			model = userFormService.create(model);
//		} catch (Exception e) {
//			String localizedErrorMessage = messageSource.getMessage("question.update.unsuccessful", null,
//					currentLocale);
//			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
//			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
//		}
//
//		argumentsToReplace[0] = model.getUser().getUserName();
//		String localizedSuccessMessage = messageSource.getMessage("question.update.successful", argumentsToReplace,
//				currentLocale);
//		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
//		resp.setResource(model);
//		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);
//
//	}

	@GetMapping("/question/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		UserFormModel model = null;
		try {
			model = userFormService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("question.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserFormModel>(model, HttpStatus.OK);
	}

}
