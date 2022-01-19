package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.converter.PageModelObjects;
import com.dataeval.model.entity.FlowConfig;
import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.repository.FlowConfigRepository;
import com.dataeval.util.Util;

@Service
public class FlowConfigService {

	private static final Logger log = LoggerFactory.getLogger(FlowConfigService.class);

	@Autowired
	private FlowConfigRepository flowConfigRepository;

	public List<FlowConfig> loadFlowConfigNameAndIdWithNoSec() {
		return flowConfigRepository.findAll();
	}

	public FlowConfigModel create(FlowConfigModel model) {
		if (model == null) {
			return model;
		}
		try {
			FlowConfig entity = ModelToEntityConverter.getFlowConfigModel(model);
			entity = flowConfigRepository.save(entity);
			model = EntityModelConverter.getFlowConfigModel(entity);

		} catch (Exception e) {
			log.error("Error while create  FlowConfig :: ", e);
			throw e;
		}

		return model;

	}

	public FlowConfigModel update(FlowConfigModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			FlowConfig entity = ModelToEntityConverter.getFlowConfigModel(model);
			entity = flowConfigRepository.save(entity);
			model = EntityModelConverter.getFlowConfigModel(entity);

		} catch (Exception e) {
			log.error("Error while update  FlowConfig by id :: " + model.getId(), e);
		}
		return model;

	}

	public FlowConfigModel getById(Integer id) throws Exception {

		FlowConfigModel model = new FlowConfigModel();
		try {
			FlowConfig entity = flowConfigRepository.findById(id)
					.orElseThrow(() -> (new Exception("FlowConfig Not Exist with ID : " + id)));
			model = EntityModelConverter.getFlowConfigModel(entity);

		} catch (Exception e) {
			log.error("Error while get  FlowConfig by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			flowConfigRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  FlowConfig by id :: " + id, e);
		}

		return deleted;

	}

	public List<FlowConfigModel> findAll() {
		List<FlowConfigModel> modelsList = new ArrayList<FlowConfigModel>();
		try {
			List<FlowConfig> entityList = flowConfigRepository.findAll();
			modelsList = ListModelObject.getListFlowConfigModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  FlowConfigs ", e);
		}
		return modelsList;
	}

	public Page<FlowConfigModel> findAll(CommonCriteria commonCriteria) {
		try {
			Page<FlowConfig> entityList = flowConfigRepository.findAll(Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPageFlowConfigModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  FlowConfigs ", e);
			throw e;
		}
	}

}
