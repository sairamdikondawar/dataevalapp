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
import com.dataeval.model.pojo.FlowConfigModel;
import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.QuestionModel;
import com.dataeval.model.pojo.QuestionTypeModel;
import com.dataeval.model.pojo.RoleModel;
import com.dataeval.model.pojo.UserModel;

public class ModelToEntityConverter {

	private static final Logger log = LoggerFactory.getLogger(EntityModelConverter.class);

	public static FlowConfig getFlowConfigModel(FlowConfigModel model) {
		FlowConfig entity = new FlowConfig();
		try {
			BeanUtils.copyProperties(model, entity);
			Role rEntity=getRoleModel(model.getRole());
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
			BeanUtils.copyProperties(model, entity);
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

}
