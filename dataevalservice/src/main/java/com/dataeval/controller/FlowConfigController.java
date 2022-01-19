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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.FlowConfigService;
import com.dataeval.util.Util;

//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:3200"})
@RestController
@RequestMapping("/api/v1/flow-config")
public class FlowConfigController {

	private static final Logger log = LoggerFactory.getLogger(FlowConfigController.class);

	@Autowired
	private FlowConfigService flowConfigService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/flowconfigs")
	public Page<FlowConfigModel> list(@RequestParam(required = false) String searchCriteria, Integer page,
			Integer size) {
		try {
			CommonCriteria common = Util.getObjectMapper().readValue(searchCriteria.toString(), CommonCriteria.class);

			common.setPage(page);
			common.setSize(size);

			return flowConfigService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@PostMapping("/flowconfig")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody FlowConfigModel model, BindingResult bindingResult) throws Exception {
		log.info("Inside Create Site API");
		try {
			model = flowConfigService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("flowconfig.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getFlowName();
		String localizedErrorMessage = messageSource.getMessage("flowconfig.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/flowconfig/{id}")
	public ResponseEntity<?> update(@RequestBody FlowConfigModel model, @PathVariable Long id) {

		log.info("Inside update Site API");
		try {
			model = flowConfigService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("flowconfig.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getFlowName();
		String localizedSuccessMessage = messageSource.getMessage("flowconfig.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/flowconfig/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		FlowConfigModel model = null;
		try {
			model = flowConfigService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("flowconfig.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FlowConfigModel>(model, HttpStatus.OK);
	}

}
