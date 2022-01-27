package com.dataeval.model.converter;

import java.util.ArrayList;
import java.util.List;

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

public class ListModelObject {

	public static List<FlowConfigModel> getListFlowConfigModelFromListEntities(List<FlowConfig> entities) {
		List<FlowConfigModel> dtoList = new ArrayList<FlowConfigModel>();

		entities.stream().forEach(entity -> {
			FlowConfigModel dto = EntityModelConverter.getFlowConfigModel(entity);
			dtoList.add(dto);
		});
		return dtoList;

	}

	public static List<QuestionModel> getListQuestionModelFromListEntities(List<Question> entities) {
		List<QuestionModel> dtoList = new ArrayList<QuestionModel>();

		if (entities != null)
			entities.stream().forEach(entity -> {
				QuestionModel dto = EntityModelConverter.getQuestionModel(entity);
//				dto.setSection(EntityModelConverter.getPageSectionModel(entity.getPageSection()));
				dtoList.add(dto);
			});
		return dtoList;
	}

	public static List<QuestionTypeModel> getListQuestionTypeModelFromListEntities(List<QuestionType> entities) {
		List<QuestionTypeModel> dtoList = new ArrayList<QuestionTypeModel>();

		entities.stream().forEach(entity -> {
			QuestionTypeModel dto = EntityModelConverter.getQuestionTypeModel(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public static List<FlowPageModel> getListFlowPageModelFromListEntities(List<FlowPage> entities, boolean deepClone) {
		List<FlowPageModel> dtoList = new ArrayList<FlowPageModel>();

		entities.stream().forEach(entity -> {
			FlowPageModel dto = EntityModelConverter.getFlowPageModel(entity,deepClone);
			if(entity.getPageSections()!=null)
			dto.setSections(getListPageSectionModelFromListEntities(entity.getPageSections()));
			dtoList.add(dto);
		}

		);
		return dtoList;
	}

	public static List<UserModel> getListUserModelFromListEntities(List<User> entities) {
		List<UserModel> dtoList = new ArrayList<UserModel>();

		entities.stream().forEach(entity -> {
			UserModel dto = EntityModelConverter.getUserModel(entity);
			dtoList.add(dto);
		});
		return dtoList;

	}

	public static List<RoleModel> getListRoleModelFromListEntities(List<Role> entities) {
		List<RoleModel> dtoList = new ArrayList<RoleModel>();

		entities.stream().forEach(entity -> {
			RoleModel dto = EntityModelConverter.getRoleModel(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public static List<PageSectionModel> getListPageSectionModelFromListEntities(List<PageSection> entities,
			boolean deepClone) {
		List<PageSectionModel> dtoList = new ArrayList<PageSectionModel>();
      if(entities!=null)
      {
    	  entities.stream().forEach(entity -> {
  			PageSectionModel dto = EntityModelConverter.getPageSectionModel(entity);
  			if (deepClone)
  			{
  				dto.setQuestions(getListQuestionModelFromListEntities(entity.getQuestions()));
  			}
  			else {
  				dto.setQuestions(null);
  			}
  			dtoList.add(dto);
  		});
      }
		return dtoList;
	}

	public static List<PageSectionModel> getListPageSectionModelFromListEntities(List<PageSection> entities) {
		return getListPageSectionModelFromListEntities(entities, Boolean.TRUE);
	}
	
	
	public static List<UserFormModel> getListUserFormModelFromListEntities(List<UserForm> entities) {
		List<UserFormModel> dtoList = new ArrayList<UserFormModel>();

		entities.stream().forEach(entity -> {
			UserFormModel dto = EntityModelConverter.getUserFormModel(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	
	public static List<UserPageModel> getListUserPageModelFromListEntities(List<UserPage> entities) {
		List<UserPageModel> dtoList = new ArrayList<UserPageModel>();

		entities.stream().forEach(entity -> {
			UserPageModel dto = EntityModelConverter.getUserPageModel(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}
	public static List<UserSectionModel> getListUserSectionModelFromListEntities(List<UserSection> entities) {
		List<UserSectionModel> dtoList = new ArrayList<UserSectionModel>();

		entities.stream().forEach(entity -> {
			UserSectionModel dto = EntityModelConverter.getUserSectionModel(entity);
			dto.setUserPage(null);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	public static List<UserQuestionModel> getListUserQuestionModelFromListEntities(List<UserQuestion> entities) {
		List<UserQuestionModel> dtoList = new ArrayList<UserQuestionModel>();

		entities.stream().forEach(entity -> {
			UserQuestionModel dto = EntityModelConverter.getUserQuestionModel(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
