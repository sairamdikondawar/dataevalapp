package com.dataeval.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.dataeval.model.entity.FlowConfig;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.entity.PatientCallLog;
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
import com.dataeval.model.pojo.PatientCallLogModel;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.QuestionTypeModel;
import com.dataeval.model.pojo.RoleModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.pojo.UserModel;
import com.dataeval.model.pojo.UserPageModel;
import com.dataeval.model.pojo.UserQuestionModel;
import com.dataeval.model.pojo.UserSectionModel;

public class ModelToEntityConverter {

	private static final Logger log = LoggerFactory.getLogger(EntityModelConverter.class);

	public static FlowConfig getFlowConfigModel(FlowConfigModel model) {
		FlowConfig entity = new FlowConfig();
		try {
			BeanUtils.copyProperties(model, entity);
			Role rEntity = getRoleModel(model.getRole());
			entity.setRole(rEntity);
		} catch (Exception e) {
			log.error("Unable to prepare FlowConfig Object", e);
		}
		return entity;
	}

	public static FlowPage getFlowPageModel(FlowPageModel model) {
		FlowPage entity = new FlowPage();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Unable to prepare FlowPage Object", e);
		}
		return entity;
	}

	public static User getUserModel(UserModel model) {
		User entity = new User();
		try {
			BeanUtils.copyProperties(model, entity, "password");
		} catch (Exception e) {
			log.error("Unable to prepare User Object", e);
		}
		return entity;
	}

	public static Role getRoleModel(RoleModel model) {
		Role entity = new Role();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Unable to prepare Role Object", e);
		}
		return entity;
	}

	public static Question getQuestionModel(QuestionModel model) {
		Question entity = new Question();
		try {
			BeanUtils.copyProperties(model, entity);
			entity.setUserTypes(String.join(",", model.getUserTypesList()));
		} catch (Exception e) {
			log.error("Unable to prepare Question Object", e);
		}
		return entity;
	}

	public static QuestionType getQuestionTypeModel(QuestionTypeModel model) {
		QuestionType entity = new QuestionType();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Unable to prepare QuestionType Object", e);
		}
		return entity;
	}

	public static PageSection getPageSectionEntity(PageSectionModel model) {
		PageSection entity = new PageSection();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Unable to prepare PageSection Object", e);
		}
		return entity;
	}

	public static UserForm getUserFormEntity(UserFormModel model) {
		UserForm entity = new UserForm();
		try {
			BeanUtils.copyProperties(model, entity);

			List<UserPage> pages = new ArrayList<UserPage>();
			model.getUserPages().forEach(page -> {

				pages.add(ModelToEntityConverter.getUserPageEntity(page));
			});

//			entity.setUserPages(pages);

		} catch (Exception e) {
			log.error("Unable to prepare UserForm Object", e);
		}
		return entity;
	}

	public static UserPage getUserPageEntity(UserPageModel model) {
		UserPage entity = new UserPage();
		try {
			BeanUtils.copyProperties(model, entity);
			List<UserSection> sections = new ArrayList<UserSection>();

			model.getUserSections().forEach(section -> {

				sections.add(ModelToEntityConverter.getUserSectionEntity(section));

			});

			entity.setUserSections(sections);

		} catch (Exception e) {
			log.error("Unable to prepare UserPage Object", e);
		}
		return entity;
	}

	public static UserSection getUserSectionEntity(UserSectionModel model) {
		UserSection entity = new UserSection();
		try {
			BeanUtils.copyProperties(model, entity);
			List<UserQuestion> fields = new ArrayList<UserQuestion>();

			model.getUserQuestions().forEach(question -> {

				fields.add(ModelToEntityConverter.getUserQuestionEntity(question));

			});

			entity.setUserQuestions(fields);

		} catch (Exception e) {
			log.error("Unable to prepare UserSection Object", e);
		}
		return entity;
	}

	public static UserQuestion getUserQuestionEntity(UserQuestionModel model) {
		UserQuestion entity = new UserQuestion();
		try {
			BeanUtils.copyProperties(model, entity);
		} catch (Exception e) {
			log.error("Unable to prepare UserQuestion Object", e);
		}
		return entity;
	}

	public static PatientCallLog getPatienCallLogModel(PatientCallLogModel model) {
		PatientCallLog entity = new PatientCallLog();
		try {
			BeanUtils.copyProperties(model, entity);
			User user = new User();
			user.setId(model.getUser().getId());
			entity.setUser(user);

		} catch (Exception e) {
			log.error("Unable to prepare PatientCallRecord Object", e);
		}
		return entity;
	}
}
