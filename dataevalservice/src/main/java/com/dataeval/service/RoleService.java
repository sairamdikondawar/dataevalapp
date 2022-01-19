package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataeval.model.entity.Role;
import com.dataeval.model.pojo.common.LookupModel;
import com.dataeval.repository.RoleRepository;

@Service
public class RoleService {

	private static final Logger log = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	public List<LookupModel> lookupRoles() {
		List<LookupModel> modelsList = new ArrayList<LookupModel>();
		try {
			List<Role> entityList = roleRepository.findAll();

			entityList.stream().forEach(role -> {
				LookupModel model = new LookupModel(role.getRoleName(), role.getId());
				modelsList.add(model);
			});
		} catch (Exception e) {
			log.error("Error while findAll  Lookup Roles  ", e);
		}
		return modelsList;
	}

}
