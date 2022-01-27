package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.converter.PageModelObjects;
import com.dataeval.model.entity.User;
import com.dataeval.model.pojo.UserModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.repository.RoleRepository;
import com.dataeval.repository.UserRepository;
import com.dataeval.util.Util;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> loadUserNameAndIdWithNoSec() {
		return userRepository.findAll();
	}

	public UserModel create(UserModel model) {
		if (model == null) {
			return model;
		}
		try {
			User entity = ModelToEntityConverter.getUserModel(model);
			
			if(model.getId() == null)
			{
				entity.setPassword("$2a$10$Iem0Lf7huYWkYtbI8F/NiO1rpGQRZzDKgdBDsxzdK9TkAKiDNPgSm");
			}
			
			entity.setRole(roleRepository.findById(model.getRole().getId()).get());
			entity = userRepository.save(entity);
			model = EntityModelConverter.getUserModel(entity);

		} catch (Exception e) {
			log.error("Error while create  User :: ", e);
			throw e;
		}

		return model;

	}

	public UserModel update(UserModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
		    User entity=userRepository.findById(model.getId()).get();
			    BeanUtils.copyProperties(model, entity, "password");
			
			entity.setRole(roleRepository.findById(model.getRole().getId()).get());
			entity = userRepository.save(entity);
			model = EntityModelConverter.getUserModel(entity);

		} catch (Exception e) {
			log.error("Error while update  User by id :: " + model.getId(), e);
		}
		return model;

	}

	public UserModel getById(Integer id) throws Exception {

		UserModel model = new UserModel();
		try {
			User entity = userRepository.findById(id)
					.orElseThrow(() -> (new Exception("User Not Exist with ID : " + id)));
			model = EntityModelConverter.getUserModel(entity);

		} catch (Exception e) {
			log.error("Error while get  User by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			userRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  User by id :: " + id, e);
		}

		return deleted;

	}

	public List<UserModel> findAll() {
		List<UserModel> modelsList = new ArrayList<UserModel>();
		try {
			List<User> entityList = userRepository.findAll();
			modelsList = ListModelObject.getListUserModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  Users ", e);
		}
		return modelsList;
	}

	public Page<UserModel> findAll(CommonCriteria commonCriteria) {
		try {
			Page<User> entityList = userRepository.findAll(Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPageUserModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  Users ", e);
			throw e;
		}
	}

}
