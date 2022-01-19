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

import com.dataeval.model.pojo.common.LookupModel;
import com.dataeval.model.response.ErrorResponse;
import com.dataeval.service.RoleService;

@RestController
@RequestMapping("/api/v1/role-config")
public class RoleController {

	@Autowired
	private RoleService roleService;

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private MessageSource messageSource;

	Locale currentLocale = LocaleContextHolder.getLocale();

	private String[] argumentsToReplace = new String[5];

	@GetMapping("/roles")
	public ResponseEntity<?> lookupSysconstants() {

		List<LookupModel> models = null;
		try {
			models = roleService.lookupRoles();
		} catch (Exception e) {
			String localizedErrorMessage = messageSource.getMessage("roles.nodata.found", null, currentLocale);
			ErrorResponse resp = new ErrorResponse(localizedErrorMessage);
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<LookupModel>>(models, HttpStatus.OK);
	}

}
