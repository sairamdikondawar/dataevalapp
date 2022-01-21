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

import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.PageSectionService;
import com.dataeval.util.Util;

@RestController
@RequestMapping("/api/v1/pagesec-config")
public class PageSectionController {

	private static final Logger log = LoggerFactory.getLogger(PageSectionController.class);

	@Autowired
	private PageSectionService pageSectionService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/sections")
	public Page<PageSectionModel> list(@RequestParam(required = false) String searchCriteria, Integer page, Integer size) {
		try {
			CommonCriteria common = Util.getObjectMapper().readValue(searchCriteria.toString(), CommonCriteria.class);

			common.setPage(page);
			common.setSize(size);

			return pageSectionService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@PostMapping("/section")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody PageSectionModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create PageSection API");
		try {
			model = pageSectionService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("pageSection.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getName();
		String localizedErrorMessage = messageSource.getMessage("pageSection.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/section/{id}")
	public ResponseEntity<?> update(@RequestBody PageSectionModel model, @PathVariable Long id) {

		log.info("Inside update PageSection API");
		try {
			model = pageSectionService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("pageSection.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getName();
		String localizedSuccessMessage = messageSource.getMessage("pageSection.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/section/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		PageSectionModel model = null;
		try {
			model = pageSectionService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("pageSection.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PageSectionModel>(model, HttpStatus.OK);
	}

}
