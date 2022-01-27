package com.dataeval.controller;

import java.util.Arrays;
import java.util.List;
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

import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.PageService;
import com.dataeval.util.Util;

@RestController
@RequestMapping("/api/v1/page-config")
public class PageController {

	private static final Logger log = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private PageService pageService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/pages")
	public Page<FlowPageModel> list(CommonCriteria common) {
		try {

			return pageService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@PostMapping("/page")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody FlowPageModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create Site API");
		try {
			model = pageService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("page.insert.unsuccessful", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getName();
		String localizedErrorMessage = messageSource.getMessage("page.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/page/{id}")
	public ResponseEntity<?> update(@RequestBody FlowPageModel model, @PathVariable Long id) {

		log.info("Inside update Site API");
		try {
			model = pageService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("page.update.unsuccessful", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getName();
		String localizedSuccessMessage = messageSource.getMessage("page.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/page/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		FlowPageModel model = null;
		try {
			model = pageService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("page.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FlowPageModel>(model, HttpStatus.OK);
	}

	@GetMapping("/list")
	public List<FlowPageModel> activePages() {
		try {
			return pageService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Arrays.asList(null);
	}

}
