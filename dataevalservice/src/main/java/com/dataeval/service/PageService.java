package com.dataeval.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dataeval.model.constants.CommonConstants;
import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.converter.PageModelObjects;
import com.dataeval.model.entity.FlowPage;
import com.dataeval.model.entity.PageSection;
import com.dataeval.model.entity.Question;
import com.dataeval.model.pojo.FlowPageModel;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.repository.PageRepository;
import com.dataeval.util.Util;

@Service
public class PageService {

	private static final Logger log = LoggerFactory.getLogger(PageService.class);

	@Autowired
	private PageRepository pageRepository;

	public List<FlowPage> loadFlowPageNameAndIdWithNoSec() {
		return pageRepository.findAll(Sort.by("sequence"));
	}

	public FlowPageModel create(FlowPageModel model) {
		if (model == null) {
			return model;
		}
		try {
			FlowPage entity = ModelToEntityConverter.getFlowPageModel(model);
			entity = pageRepository.save(entity);
			model = EntityModelConverter.getFlowPageModel(entity);

		} catch (Exception e) {
			log.error("Error while create  FlowPage :: ", e);
			throw e;
		}

		return model;

	}

	public FlowPageModel update(FlowPageModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			FlowPage entity = ModelToEntityConverter.getFlowPageModel(model);
			entity = pageRepository.save(entity);
			model = EntityModelConverter.getFlowPageModel(entity);

		} catch (Exception e) {
			log.error("Error while update  FlowPage by id :: " + model.getId(), e);
		}
		return model;

	}

	public FlowPageModel getById(Integer id) throws Exception {

		FlowPageModel model = new FlowPageModel();
		try {
			FlowPage entity = pageRepository.findById(id)
					.orElseThrow(() -> (new Exception("FlowPage Not Exist with ID : " + id)));
			model = EntityModelConverter.getFlowPageModel(entity);

		} catch (Exception e) {
			log.error("Error while get  FlowPage by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			pageRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  FlowPage by id :: " + id, e);
		}

		return deleted;

	}
	public List<FlowPageModel> findAll() {
		return this.findAllByRole(null);
	}

	public List<FlowPageModel> findAllByRole(String role) {
		List<FlowPageModel> modelsList = new ArrayList<FlowPageModel>();
		try {
			List<FlowPage> entityList = pageRepository.findAllByStatusOrderBySequenceAsc(CommonConstants.ACTIVE);
			
			FlowPage reviewPage=new FlowPage();
			reviewPage.setName("Review & Submit");
			reviewPage.setSequence(9999);
			List<PageSection> allSections=new ArrayList<PageSection>();
			
			for(FlowPage page:entityList)
			{
				for(PageSection sec:page.getPageSections())
				{
					List<Question> questions=new ArrayList<Question>();
					PageSection copySec=new PageSection();
					BeanUtils.copyProperties(sec, copySec);
					copySec.setLayout(2);
					
					
					for(Question ques:sec.getQuestions())
					{
						if(role != null&& !role.isEmpty()) {
							ques.setReadonly(true);
						}
						
						Question clonQue=new Question();
						
						BeanUtils.copyProperties(ques, clonQue);
						clonQue.setReadonly(Boolean.TRUE);
						questions.add(clonQue);
					}
					copySec.setQuestions(questions);
					
					allSections.add(copySec);
					
					
					
					
				}
			}
			
			reviewPage.setPageSections(allSections);
			entityList.add(reviewPage);
			
			modelsList = ListModelObject.getListFlowPageModelFromListEntities(entityList, true, role);
		} catch (Exception e) {
			log.error("Error while findAll  FlowPages ", e);
		}
		return modelsList;
	}

	public Page<FlowPageModel> findAll(CommonCriteria commonCriteria) {
		try {
			Page<FlowPage> entityList = pageRepository.findAll(Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPageFlowPageModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  FlowPages ", e);
			throw e;
		}
	}
	
	public List<FlowPageModel> lookupPages() {
		try {
			List<FlowPage> entityList = pageRepository.lookupPages();
			return ListModelObject.getListFlowPageModelFromListEntities(entityList,false);
		} catch (Exception e) {
			log.error("Error while findAll  FlowPages ", e);
			throw e;
		}
	}

}
