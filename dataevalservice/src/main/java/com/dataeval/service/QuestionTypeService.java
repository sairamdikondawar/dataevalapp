package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataeval.model.constants.CommonConstants;
import com.dataeval.model.entity.QuestionType;
import com.dataeval.model.pojo.common.LookupModel;
import com.dataeval.repository.QuestionTypeRepository;

@Service
public class QuestionTypeService {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionTypeService.class);

	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	
	 

	public List<LookupModel> lookupQuestionTypes() {
		List<LookupModel> modelsList = new ArrayList<LookupModel>();
		try {
			List<QuestionType> entityList = questionTypeRepository.findAllByStatus(CommonConstants.ACTIVE);

			entityList.stream().forEach(role -> {
				LookupModel model = new LookupModel(role.getQuestionType(), role.getId());
				modelsList.add(model);
			});
		} catch (Exception e) {
			log.error("Error while findAll  Lookup Question Types  ", e);
		}
		return modelsList;
	}

}
