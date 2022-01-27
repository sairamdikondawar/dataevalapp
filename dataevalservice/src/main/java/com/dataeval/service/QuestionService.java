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
import com.dataeval.model.entity.Question;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.QuestionCriteria;
import com.dataeval.repository.PageSectionRepository;
import com.dataeval.repository.QuestionRepository;
import com.dataeval.util.Util;

@Service
public class QuestionService {

	private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private PageSectionRepository pageSectionRepository;

	public List<Question> loadQuestionNameAndIdWithNoSec() {
		return questionRepository.findAll();
	}

	public QuestionModel create(QuestionModel model) {
		if (model == null) {
			return model;
		}
		try {
			Question entity = ModelToEntityConverter.getQuestionModel(model);
			entity.setRequired(Boolean.FALSE);
			entity.setSection(pageSectionRepository.findById(model.getSection().getId()).get());
			entity = questionRepository.save(entity);
			model = EntityModelConverter.getQuestionModel(entity);

		} catch (Exception e) {
			log.error("Error while create  Question :: ", e);
			throw e;
		}

		return model;

	}

	public QuestionModel update(QuestionModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			Question entity = ModelToEntityConverter.getQuestionModel(model);
			entity = questionRepository.save(entity);
			model = EntityModelConverter.getQuestionModel(entity);

		} catch (Exception e) {
			log.error("Error while update  Question by id :: " + model.getId(), e);
		}
		return model;

	}

	public QuestionModel getById(Integer id) throws Exception {

		QuestionModel model = new QuestionModel();
		try {
			Question entity = questionRepository.findById(id)
					.orElseThrow(() -> (new Exception("Question Not Exist with ID : " + id)));
			model = EntityModelConverter.getQuestionModel(entity);

		} catch (Exception e) {
			log.error("Error while get  Question by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			questionRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  Question by id :: " + id, e);
		}

		return deleted;

	}

	public List<QuestionModel> findAll() {
		List<QuestionModel> modelsList = new ArrayList<QuestionModel>();
		try {
			List<Question> entityList = questionRepository.findAll();
			modelsList = ListModelObject.getListQuestionModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  Questions ", e);
		}
		return modelsList;
	}

	public Page<QuestionModel> findAll(QuestionCriteria commonCriteria) {
		try {
			Page<Question> entityList = questionRepository.findAllQuestions(commonCriteria.getSectionId(), commonCriteria.getQName() ,Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPageQuestionModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  Questions ", e);
			throw e;
		}
	}

}
