package com.dataeval.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.common.LookupModel;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.PageSectionService;
import com.dataeval.service.PageService;
import com.dataeval.service.QuestionTypeService;
import com.dataeval.service.RoleService;

@RestController
@RequestMapping("/api/v1/lookup")
public class CommonController {
	
	@Autowired
	private QuestionTypeService questionTypeService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private PageSectionService sectionService;

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/questionTypes")
	public ResponseEntity<?> lookupSysconstants() {

		List<LookupModel> models = null;
		try {
			models = questionTypeService.lookupQuestionTypes();
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("questionTypes.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<LookupModel>>(models, HttpStatus.OK);
	}
	
	
	@GetMapping("/pages")
	public ResponseEntity<?> lookupPages() {

		List<FlowPageModel> models = null;
		try {
			models = pageService.findAll();
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("pages.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<FlowPageModel>>(models, HttpStatus.OK);
	}
	
	@GetMapping("/sections")
	public ResponseEntity<?> lookupSections() {

		List<LookupModel> models = null;
		try {
			models = sectionService.lookupSections();
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("pages.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<LookupModel>>(models, HttpStatus.OK);
	}

}
