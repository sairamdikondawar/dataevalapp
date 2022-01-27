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
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.pojo.PageSectionModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.LookupModel;
import com.dataeval.repository.PageRepository;
import com.dataeval.repository.PageSectionRepository;
import com.dataeval.util.Util;

@Service
public class PageSectionService {

	private static final Logger log = LoggerFactory.getLogger(PageSectionService.class);

	@Autowired
	private PageSectionRepository pageSectionRepository;
	
	@Autowired
	private PageRepository pageRepository;

	public List<PageSection> loadPageSectionNameAndIdWithNoSec() {
		return pageSectionRepository.findAll();
	}

	public PageSectionModel create(PageSectionModel model) {
		if (model == null) {
			return model;
		}
		try {
			PageSection entity = ModelToEntityConverter.getPageSectionEntity(model);
			entity.setPage(pageRepository.findById(model.getPage().getId()).get());
			entity = pageSectionRepository.save(entity);
			model = EntityModelConverter.getPageSectionModel(entity);

		} catch (Exception e) {
			log.error("Error while create  PageSection :: ", e);
			throw e;
		}

		return model;

	}

	public PageSectionModel update(PageSectionModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			PageSection entity = ModelToEntityConverter.getPageSectionEntity(model);
			entity.setPage(pageRepository.findById(model.getPage().getId()).get());
			entity = pageSectionRepository.save(entity);
			model = EntityModelConverter.getPageSectionModel(entity);

		} catch (Exception e) {
			log.error("Error while update  PageSection by id :: " + model.getId(), e);
		}
		return model;

	}

	public PageSectionModel getById(Integer id) throws Exception {

		PageSectionModel model = new PageSectionModel();
		try {
			PageSection entity = pageSectionRepository.findById(id)
					.orElseThrow(() -> (new Exception("PageSection Not Exist with ID : " + id)));
			model = EntityModelConverter.getPageSectionModel(entity);

		} catch (Exception e) {
			log.error("Error while get  PageSection by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			pageSectionRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  PageSection by id :: " + id, e);
		}

		return deleted;

	}

	public List<PageSectionModel> findAll() {
		List<PageSectionModel> modelsList = new ArrayList<PageSectionModel>();
		try {
			List<PageSection> entityList = pageSectionRepository.findAll();
			modelsList = ListModelObject.getListPageSectionModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  PageSections ", e);
		}
		return modelsList;
	}
	
	public List<LookupModel> lookupSections() {
		List<LookupModel> modelsList = new ArrayList<LookupModel>();
		try {
			List<PageSection> entityList = pageSectionRepository.findAll();
//			modelsList = ListModelObject.getListPageSectionModelFromListEntities(entityList, Boolean.FALSE);
			
			entityList.stream().forEach(role -> {
				LookupModel model = new LookupModel(role.getName(), role.getId());
				modelsList.add(model);
			});
		} catch (Exception e) {
			log.error("Error while findAll  PageSections ", e);
		}
		return modelsList;
	}

	public Page<PageSectionModel> findAll(CommonCriteria commonCriteria) {
		try {
			Page<PageSection> entityList = pageSectionRepository
					.findAll(Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPagePageSectionModelFromPageEntities(entityList, Boolean.FALSE);
		} catch (Exception e) {
			log.error("Error while findAll  PageSections ", e);
			throw e;
		}
	}

}
