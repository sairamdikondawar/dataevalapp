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
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.QuestionCriteria;
import com.dataeval.model.pojo.common.SortInfo;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.QuestionService;
import com.dataeval.util.Util;

@RestController
@RequestMapping("/api/v1/question-config")
public class QuestionController {

	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/questions")
	public Page<QuestionModel> list(QuestionCriteria common) {
		try {
            
			return questionService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return Page.empty();
	}

	@PostMapping("/question")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody QuestionModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create Question API");
		try {
			model = questionService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("question.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getName();
		String localizedErrorMessage = messageSource.getMessage("question.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/question/{id}")
	public ResponseEntity<?> update(@RequestBody QuestionModel model, @PathVariable Long id) {

		log.info("Inside update Question API");
		try {
			model = questionService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("question.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getName();
		String localizedSuccessMessage = messageSource.getMessage("question.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/question/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		QuestionModel model = null;
		try {
			model = questionService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("question.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<QuestionModel>(model, HttpStatus.OK);
	}

}
