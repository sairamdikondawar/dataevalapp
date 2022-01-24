package com.dataeval.model.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.dataeval.model.entity.FlowConfig;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.entity.Question;
import com.dataeval.model.entity.QuestionType;
import com.dataeval.model.entity.Role;
import com.dataeval.model.entity.User;
import com.dataeval.model.entity.UserForm;
import com.dataeval.model.entity.UserPage;
import com.dataeval.model.entity.UserQuestion;
import com.dataeval.model.entity.UserSection;
import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.QuestionTypeModel;
import com.dataeval.model.pojo.RoleModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.pojo.UserModel;
import com.dataeval.model.pojo.UserPageModel;
import com.dataeval.model.pojo.UserQuestionModel;
import com.dataeval.model.pojo.UserSectionModel;

public class EntityModelConverter {

	private static final Logger log = LoggerFactory.getLogger(EntityModelConverter.class);

	public static FlowConfigModel getFlowConfigModel(FlowConfig entity) {
		FlowConfigModel model = new FlowConfigModel();
		try {
			BeanUtils.copyProperties(entity, model);
			model.setRole(getRoleModel(entity.getRole()));

		} catch (Exception e) {
			log.error("Unable to prepare FlowConfigModel Object", e);
		}
		return model;
	}

	public static FlowPageModel getFlowPageModel(FlowPage entity) {
		FlowPageModel model = new FlowPageModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Unable to prepare FlowPageModel Object", e);
		}
		return model;
	}

	public static UserModel getUserModel(User entity) {
		UserModel model = new UserModel();
		try {
			BeanUtils.copyProperties(entity, model);
			
			model.setRole(getRoleModel(entity.getRole()));
		} catch (Exception e) {
			log.error("Unable to prepare UserModel Object", e);
		}
		return model;
	}

	public static RoleModel getRoleModel(Role entity) {
		RoleModel model = new RoleModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Unable to prepare RoleModel Object", e);
		}
		return model;
	}

	public static QuestionModel getQuestionModel(Question entity) {
		QuestionModel model = new QuestionModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Unable to prepare QuestionModel Object", e);
		}
		return model;
	}

	public static QuestionTypeModel getQuestionTypeModel(QuestionType entity) {
		QuestionTypeModel model = new QuestionTypeModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Unable to prepare QuestionTypeModel Object", e);
		}
		return model;
	}

	public static PageSectionModel getPageSectionModel(PageSection entity) {
		PageSectionModel model = new PageSectionModel();
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (Exception e) {
			log.error("Unable to prepare PageSectionModel Object", e);
		}
		return model;
	}

	public static UserFormModel getUserFormModel(UserForm entity) {
		UserFormModel model = new UserFormModel();
		try {
			BeanUtils.copyProperties(entity, model);
			model.setUser(getUserModel(entity.getUser()));

		} catch (Exception e) {
			log.error("Unable to prepare UserFormModel Object", e);
		}
		return model;
	}

	public static UserPageModel getUserPageModel(UserPage entity) {
		UserPageModel model = new UserPageModel();
		try {
			BeanUtils.copyProperties(entity, model);
			
			model.setUserSections(ListModelObject.getListUserSectionModelFromListEntities(entity.getUserSections()));
		} catch (Exception e) {
			log.error("Unable to prepare UserPageModel Object", e);
		}
		return model;
	}

	public static UserSectionModel getUserSectionModel(UserSection entity) {
		UserSectionModel model = new UserSectionModel();
		try {
			BeanUtils.copyProperties(entity, model);
			model.setUserQuestions(ListModelObject.getListUserQuestionModelFromListEntities(entity.getUserQuestions()));
		} catch (Exception e) {
			log.error("Unable to prepare UserSectionModel Object", e);
		}
		return model;
	}

	public static UserQuestionModel getUserQuestionModel(UserQuestion entity) {
		UserQuestionModel model = new UserQuestionModel();
		try {
			BeanUtils.copyProperties(entity, model);
			model.setUserSection(null);
		} catch (Exception e) {
			log.error("Unable to prepare UserQuestionModel Object", e);
		}
		return model;
	}

}
