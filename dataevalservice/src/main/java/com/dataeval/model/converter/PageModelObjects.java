package com.dataeval.model.converter;

import java.util.function.Function;

import org.springframework.data.domain.Page;

import com.dataeval.model.entity.FlowConfig;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.entity.PatientCallLog;
import com.dataeval.model.entity.Question;
import com.dataeval.model.entity.QuestionType;
import com.dataeval.model.entity.Role;
import com.dataeval.model.entity.User;
import com.dataeval.model.entity.UserForm;
import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.PatientCallLogModel;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.QuestionTypeModel;
import com.dataeval.model.pojo.RoleModel;
import com.dataeval.model.pojo.UserFormModel;
import com.dataeval.model.pojo.UserModel;
import com.dataeval.util.Util;

public class PageModelObjects {

	public static Page<FlowConfigModel> getPageFlowConfigModelFromPageEntities(Page<FlowConfig> entities) {
		Page<FlowConfigModel> dtoPage = entities.map(new Function<FlowConfig, FlowConfigModel>() {
			@Override
			public FlowConfigModel apply(FlowConfig entity) {
				FlowConfigModel dto = EntityModelConverter.getFlowConfigModel(entity);
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<QuestionModel> getPageQuestionModelFromPageEntities(Page<Question> entities) {
		Page<QuestionModel> dtoPage = entities.map(new Function<Question, QuestionModel>() {
			@Override
			public QuestionModel apply(Question entity) {
				QuestionModel dto = EntityModelConverter.getQuestionModel(entity);
				if (entity.getSection() != null) {
					PageSectionModel sec = EntityModelConverter.getPageSectionModel(entity.getSection());
					sec.setQuestions(null);
					dto.setSection(sec);
				}
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<QuestionTypeModel> getPageQuestionTypeModelFromPageEntities(Page<QuestionType> entities) {
		Page<QuestionTypeModel> dtoPage = entities.map(new Function<QuestionType, QuestionTypeModel>() {
			@Override
			public QuestionTypeModel apply(QuestionType entity) {
				QuestionTypeModel dto = EntityModelConverter.getQuestionTypeModel(entity);

				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<FlowPageModel> getPageFlowPageModelFromPageEntities(Page<FlowPage> entities) {
		Page<FlowPageModel> dtoPage = entities.map(new Function<FlowPage, FlowPageModel>() {
			@Override
			public FlowPageModel apply(FlowPage entity) {
				FlowPageModel dto = EntityModelConverter.getFlowPageModel(entity);
				dto.setSections(ListModelObject.getListPageSectionModelFromListEntities(entity.getPageSections()));
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<UserModel> getPageUserModelFromPageEntities(Page<User> entities) {
		Page<UserModel> dtoPage = entities.map(new Function<User, UserModel>() {
			@Override
			public UserModel apply(User entity) {
				UserModel dto = EntityModelConverter.getUserModel(entity);
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<RoleModel> getPageRoleModelFromPageEntities(Page<Role> entities) {
		Page<RoleModel> dtoPage = entities.map(new Function<Role, RoleModel>() {
			@Override
			public RoleModel apply(Role entity) {
				RoleModel dto = EntityModelConverter.getRoleModel(entity);
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<PageSectionModel> getPagePageSectionModelFromPageEntities(Page<PageSection> entities) {
		return getPagePageSectionModelFromPageEntities(entities, false);
	}

	public static Page<PageSectionModel> getPagePageSectionModelFromPageEntities(Page<PageSection> entities,
			boolean deepClone) {
		Page<PageSectionModel> dtoPage = entities.map(new Function<PageSection, PageSectionModel>() {
			@Override
			public PageSectionModel apply(PageSection entity) {
				PageSectionModel dto = EntityModelConverter.getPageSectionModel(entity);
				dto.setQuestions(null);
				if (deepClone) {
					dto.setQuestions(ListModelObject.getListQuestionModelFromListEntities(entity.getQuestions(),
							Util.getLoggedInUserType()));
				}
				dto.setPage(EntityModelConverter.getFlowPageModel(entity.getPage()));
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<UserFormModel> getPageUserFormModelFromPageEntities(Page<UserForm> entities) {
		Page<UserFormModel> dtoPage = entities.map(new Function<UserForm, UserFormModel>() {
			@Override
			public UserFormModel apply(UserForm entity) {
				UserFormModel dto = EntityModelConverter.getUserFormModel(entity);
				return dto;
			}
		});
		return dtoPage;
	}

	public static Page<PatientCallLogModel> getPagePatientCallLogModelModelFromPageEntities(
			Page<PatientCallLog> entities) {
		Page<PatientCallLogModel> dtoPage = entities.map(new Function<PatientCallLog, PatientCallLogModel>() {
			@Override
			public PatientCallLogModel apply(PatientCallLog entity) {
				PatientCallLogModel dto = EntityModelConverter.getPatienCallLogModel(entity);
				return dto;
			}
		});
		return dtoPage;
	}

}
