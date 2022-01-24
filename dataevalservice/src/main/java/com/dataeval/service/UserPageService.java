package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.entity.UserPage;
import com.dataeval.model.pojo.UserPageModel;
import com.dataeval.repository.UserPageRepository;


@Service
public class UserPageService {

	private static final Logger log = LoggerFactory.getLogger(UserPageService.class);

	@Autowired
	private UserPageRepository userPageRepository;
	
	 

	public List<UserPage> loadUserPageNameAndIdWithNoSec() {
		return userPageRepository.findAll();
	}

	public UserPageModel create(UserPageModel model) {
		if (model == null) {
			return model;
		}
		try {
			UserPage entity = ModelToEntityConverter.getUserPageEntity(model);
			entity = userPageRepository.save(entity);
			model = EntityModelConverter.getUserPageModel(entity);

		} catch (Exception e) {
			log.error("Error while create  UserPage :: ", e);
			throw e;
		}

		return model;

	}

	public UserPageModel update(UserPageModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			UserPage entity = ModelToEntityConverter.getUserPageEntity(model);
			entity = userPageRepository.save(entity);
			model = EntityModelConverter.getUserPageModel(entity);

		} catch (Exception e) {
			log.error("Error while update  UserPage by id :: " + model.getId(), e);
		}
		return model;

	}

	public UserPageModel getById(Integer id) throws Exception {

		UserPageModel model = new UserPageModel();
		try {
			UserPage entity = userPageRepository.findById(id)
					.orElseThrow(() -> (new Exception("UserPage Not Exist with ID : " + id)));
			model = EntityModelConverter.getUserPageModel(entity);

		} catch (Exception e) {
			log.error("Error while get  UserPage by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			userPageRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  UserPage by id :: " + id, e);
		}

		return deleted;

	}

	public List<UserPageModel> findAll() {
		List<UserPageModel> modelsList = new ArrayList<UserPageModel>();
		try {
			List<UserPage> entityList = userPageRepository.findAll();
			modelsList = ListModelObject.getListUserPageModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  UserPages ", e);
		}
		return modelsList;
	}
	
	public List<UserPageModel> findAll(Integer id) {
		List<UserPageModel> modelsList = new ArrayList<UserPageModel>();
		try {
			List<UserPage> entityList = userPageRepository.findAllByFormId(id);
			modelsList = ListModelObject.getListUserPageModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  UserPages ", e);
		}
		return modelsList;
	}

//	public Page<UserPageModel> findAll(CommonCriteria commonCriteria) {
//		try {
//			Page<UserPage> entityList = userPageRepository.findAll(Util.getPageObjectFromCriteria(commonCriteria));
//			return PageModelObjects.getPageUserPageModelFromPageEntities(entityList);
//		} catch (Exception e) {
//			log.error("Error while findAll  UserPages ", e);
//			throw e;
//		}
//	}

}
