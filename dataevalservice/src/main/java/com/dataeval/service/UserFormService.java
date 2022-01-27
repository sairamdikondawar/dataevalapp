package com.dataeval.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.converter.PageModelObjects;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.entity.Question;
import com.dataeval.model.entity.UserForm;
import com.dataeval.model.entity.UserPage;
import com.dataeval.model.entity.UserQuestion;
import com.dataeval.model.entity.UserSection;
import com.dataeval.model.pojo.SubmitFormModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.SortInfo;
import com.dataeval.model.pojo.common.UserFormsQuery;
import com.dataeval.repository.QuestionRepository;
import com.dataeval.repository.UserFormRepository;
import com.dataeval.repository.UserPageRepository;
import com.dataeval.repository.UserQuestionRepository;
import com.dataeval.repository.UserRepository;
import com.dataeval.util.Util;

@Service
public class UserFormService {

	private static final Logger log = LoggerFactory.getLogger(UserFormService.class);

	@Autowired
	private UserFormRepository userFormRepository;

	@Autowired
	private UserPageRepository pageSectionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserQuestionRepository userQuestionRepository;

	public List<UserForm> loadUserFormNameAndIdWithNoSec() {
		return userFormRepository.findAll();
	}

	public SubmitFormModel create(SubmitFormModel model) {
		if (model == null) {
			return model;
		}
		try {
			UserForm entity = new UserForm();
			
			entity.setUser(userRepository.findById(Util.getLoggedInUserId()).get());
			entity.setCreatedBy(entity.getUser().getUserName());
			entity.setCreationDate(new Date(System.currentTimeMillis()));
			entity.setUpdatedDate(new Date(System.currentTimeMillis()));
			entity.setUpdatedBy(entity.getUser().getUserName());
			entity = userFormRepository.save(entity);

			List<Integer> fieldsIds = new ArrayList<Integer>();
			Map<Integer, String> filedAndValuesMap = new HashMap<Integer, String>();
			model.getUserQuestions().forEach(ques -> {
				Integer fieldId = Integer.parseInt(ques.getName().replaceAll("field", ""));
				fieldsIds.add(fieldId);
				filedAndValuesMap.put(fieldId, ques.getAnswer());
			});

			List<Question> questions = questionRepository.findAllById(fieldsIds);

			saveUserQuestions(questions, entity, filedAndValuesMap);

		} catch (Exception e) {
			log.error("Error while create  UserForm :: ", e);
			throw e;
		}

		return model;

	}

	public UserFormModel update(UserFormModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			UserForm entity = ModelToEntityConverter.getUserFormEntity(model);
			entity = userFormRepository.save(entity);
			model = EntityModelConverter.getUserFormModel(entity);

		} catch (Exception e) {
			log.error("Error while update  UserForm by id :: " + model.getId(), e);
		}
		return model;

	}

	public UserFormModel getById(Integer id) throws Exception {

		UserFormModel model = new UserFormModel();
		try {
			UserForm entity = userFormRepository.findById(id)
					.orElseThrow(() -> (new Exception("UserForm Not Exist with ID : " + id)));
			model = EntityModelConverter.getUserFormModel(entity);

		} catch (Exception e) {
			log.error("Error while get  UserForm by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			userFormRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  UserForm by id :: " + id, e);
		}

		return deleted;

	}

	public List<UserFormModel> findAll() {
		List<UserFormModel> modelsList = new ArrayList<UserFormModel>();
		try {
			List<UserForm> entityList = userFormRepository.findAll();
			modelsList = ListModelObject.getListUserFormModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  UserForms ", e);
		}
		return modelsList;
	}

	public Page<UserFormModel> findAll(UserFormsQuery commonCriteria) {
		try {
			SortInfo sort = new SortInfo();
			sort.setColumnName("creationDate");
			sort.setOrder(0);

			List<SortInfo> sortInfo = new ArrayList<SortInfo>();
			sortInfo.add(sort);
			commonCriteria.setSort(sortInfo);
			Page<UserForm> entityList = userFormRepository.findAllUserFomrs(commonCriteria.getRoleName(), commonCriteria.getUserName(), null,Util.getPageObjectFromCriteria(commonCriteria));
 			return PageModelObjects.getPageUserFormModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  UserForms ", e);
			throw e;
		}
	}

	public List<UserQuestion> saveUserQuestions(List<Question> questions, UserForm userForm,
			Map<Integer, String> filedAndValuesMap) {
		List<UserQuestion> userQuestions = new ArrayList<UserQuestion>();
		Map<Integer, UserPage> userPageMap = new HashMap<Integer, UserPage>();
		Map<Integer, UserSection> userSectionMap = new HashMap<Integer, UserSection>();

		try {

			questions.forEach(question -> {

				UserQuestion userQuestion = new UserQuestion();
				userQuestion.setAnswer(filedAndValuesMap.get(question.getId()));
				userQuestion.setQuestionId(question.getId());
				userQuestion.setName(question.getName());
				userQuestion.setRequired(question.getRequired());
				userQuestion.setType(question.getType());

				PageSection ps = question.getSection();
				FlowPage fp = ps.getPage();
				UserSection us = new UserSection();
				if (userSectionMap.get(ps.getId()) == null) {

					us.setName(ps.getName());
					us.setSectionId(ps.getId());
					us.setLayout(ps.getLayout());
					us.setSequence(ps.getSequence());

					userSectionMap.put(ps.getId(), us);
					UserPage up = new UserPage();
					if (userPageMap.get(fp.getId()) == null) {

						up.setName(fp.getName());
						up.setLayout(fp.getLayoutColumns());
						up.setPageId(fp.getId());
						up.setSequence(fp.getSequence());
						up.setUserForm(userForm);

						userPageMap.put(fp.getId(), up);
					} else {
						up = userPageMap.get(fp.getId());
					}
					us.setUserPage(up);
				} else {
					us = userSectionMap.get(ps.getId());
				}
				userQuestion.setUserSection(us);
				userQuestions.add(userQuestion);
			});

			userQuestionRepository.saveAll(userQuestions);

		} catch (Exception e) {
			log.error("Error while findAll  UserQuestions ", e);
		}
		return userQuestions;
	}

}
