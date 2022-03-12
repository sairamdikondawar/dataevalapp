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

import com.dataeval.model.pojo.PatientCallLogModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.PatinetLogSearchQuery;
import com.dataeval.model.response.EmptySuccessResponse;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.PatientCallLogService;
import com.dataeval.util.Util;

@RestController
@RequestMapping("/api/v1/manage-patientcalllog")
public class PatientCallLogController {

	private static final Logger log = LoggerFactory.getLogger(PatientCallLogController.class);

	@Autowired
	private PatientCallLogService patientCallLogService;

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/patientcalllogs")
	public Page<PatientCallLogModel> list(PatinetLogSearchQuery common) {
		try {
//			PatinetLogSearchQuery common = Util.getObjectMapper().readValue(searchCriteria.toString(), PatinetLogSearchQuery.class);
//
//			common.setPage(page);
//			common.setSize(size);

			return patientCallLogService.findAll(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@GetMapping("/patientcalllogshistory")
	public Page<PatientCallLogModel> historyList(PatinetLogSearchQuery common) {
		try {

			return patientCallLogService.findAllHistory(common);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Page.empty();
	}

	@PostMapping("/patientcalllog")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody PatientCallLogModel model, BindingResult bindingResult)
			throws Exception {
		log.info("Inside Create PatientCallLog API");
		try {
			model = patientCallLogService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("patientlog.insert.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}
		argumentsToReplace[0] = model.getUser().getUserName();
		String localizedErrorMessage = messageSource.getMessage("patientlog.insert.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedErrorMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping("/patientcalllog/{id}")
	public ResponseEntity<?> update(@RequestBody PatientCallLogModel model, @PathVariable Long id) {

		log.info("Inside update PatientCallLog API");
		try {
			model = patientCallLogService.create(model);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("patientlog.update.unsuccessful", null,
					currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.BAD_REQUEST);
		}

		argumentsToReplace[0] = model.getUser().getUserName();
		String localizedSuccessMessage = messageSource.getMessage("patientlog.update.successful", argumentsToReplace,
				currentLocale);
		EmptySuccessResponse resp = new EmptySuccessResponse(localizedSuccessMessage);
		resp.setResource(model);
		return new ResponseEntity<EmptySuccessResponse>(resp, HttpStatus.OK);

	}

	@GetMapping("/patientcalllog/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		PatientCallLogModel model = null;
		try {
			model = patientCallLogService.getById(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("patientlog.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PatientCallLogModel>(model, HttpStatus.OK);
	}

	@GetMapping("/patientcalllog/patient/{id}")
	public ResponseEntity<?> getByPatienId(@PathVariable Integer id) {
		PatientCallLogModel model = null;
		try {
			model = patientCallLogService.getByPatientId(id);
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("patientlog.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PatientCallLogModel>(model, HttpStatus.OK);
	}

}
