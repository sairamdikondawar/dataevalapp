package com.dataeval.model.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.dataeval.model.entity.FlowConfig;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.Question;
import com.dataeval.model.entity.QuestionType;
import com.dataeval.model.entity.Role;
import com.dataeval.model.entity.User;
import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.QuestionTypeModel;
import com.dataeval.model.pojo.RoleModel;
import com.dataeval.model.pojo.UserModel;

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

		entities.stream().forEach(entity -> {
			QuestionModel dto = EntityModelConverter.getQuestionModel(entity);
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

	public static List<FlowPageModel> getListFlowPageModelFromListEntities(List<FlowPage> entities) {
		List<FlowPageModel> dtoList = new ArrayList<FlowPageModel>();

		entities.stream().forEach(entity -> {
			FlowPageModel dto = EntityModelConverter.getFlowPageModel(entity);
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

}
