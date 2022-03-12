package com.dataeval.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dataeval.model.constants.CommonConstants;
import com.dataeval.model.converter.EntityModelConverter;
import com.dataeval.model.converter.ListModelObject;
import com.dataeval.model.converter.ModelToEntityConverter;
import com.dataeval.model.converter.PageModelObjects;
import com.dataeval.model.entity.PatientCallLog;
import com.dataeval.model.pojo.PatientCallLogModel;
import com.dataeval.model.pojo.common.PatinetLogSearchQuery;
import com.dataeval.model.pojo.common.SortInfo;
import com.dataeval.repository.PatientCallLogRepository;
import com.dataeval.util.Util;

@Service
public class PatientCallLogService {

	private static final Logger log = LoggerFactory.getLogger(PageService.class);
	private static final Integer totalTime = 1200;

	@Autowired
	PatientCallLogRepository patientCallLogRepository;

	public List<PatientCallLog> loadPatientCallLogNameAndIdWithNoSec() {
		return patientCallLogRepository.findAll(Sort.by("sequence"));
	}

	public PatientCallLogModel create(PatientCallLogModel model) throws Exception {
		if (model == null) {
			return model;
		}
		try {
			PatientCallLog oldEntity = patientCallLogRepository
					.findAllByPatientIdAndActiveStatus(model.getUser().getId()).orElse(null);

			PatientCallLog entity = ModelToEntityConverter.getPatienCallLogModel(model);
			Integer reamingTime = totalTime - entity.getTotalTimeSpent();

			entity.setRemaingTime(reamingTime >= 0 ? reamingTime : 0);

			entity.setCallRecordStatus(CommonConstants.ACTIVE);
			entity.setPatientStatus(CommonConstants.ACTIVE);
			if (oldEntity != null && oldEntity.getId() != null) {
				entity.setTimeSpentInSession(entity.getTotalTimeSpent() - oldEntity.getTotalTimeSpent());
				entity.setVisitDate(oldEntity.getVisitDate());
			} else {
				entity.setTimeSpentInSession(entity.getTotalTimeSpent());
				entity.setVisitDate(new Date(System.currentTimeMillis()));
			}
			Util.updateHistory(entity,Boolean.TRUE);
			entity = patientCallLogRepository.save(entity);
			
			if(reamingTime<0)
			{
				PatientCallLog nextEntity = ModelToEntityConverter.getPatienCallLogModel(model);
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(entity.getVisitDate());
				cal.add(Calendar.MONTH, 1);
				nextEntity.setVisitDate(cal.getTime());
				nextEntity.setCallRecordStatus(CommonConstants.ACTIVE);
				nextEntity.setPatientStatus(CommonConstants.ACTIVE);
				nextEntity.setTimeSpentInSession(reamingTime*(-1));
				nextEntity.setTotalTimeSpent(reamingTime*(-1));
				nextEntity.setRemaingTime(totalTime+reamingTime); // Here remaining time would be in negative value
				Util.updateHistory(nextEntity, Boolean.TRUE);
				nextEntity = patientCallLogRepository.save(nextEntity);
				
				model = EntityModelConverter.getPatienCallLogModel(entity);
				
				model.setCallRecordStatus(CommonConstants.INACTIVE);
				update(model);
				
			}

			if (oldEntity != null && oldEntity.getId() != null) {
				oldEntity.setCallRecordStatus("INACTIVE");

				update(EntityModelConverter.getPatienCallLogModel(oldEntity));
			}
			model = EntityModelConverter.getPatienCallLogModel(entity);
			

		} catch (Exception e) {
			log.error("Error while create  PatientCallLog :: ", e);
			throw e;
		}

		return model;

	}

	public PatientCallLogModel update(PatientCallLogModel model) {

		if (model == null || model.getId() == null) {
			return model;
		}
		try {
			PatientCallLog entity = ModelToEntityConverter.getPatienCallLogModel(model);
			Util.updateHistory(entity, false);
			entity = patientCallLogRepository.save(entity);
			model = EntityModelConverter.getPatienCallLogModel(entity);

		} catch (Exception e) {
			log.error("Error while update  PatientCallLog by id :: " + model.getId(), e);
		}
		return model;

	}

	public PatientCallLogModel getById(Integer id) throws Exception {

		PatientCallLogModel model = new PatientCallLogModel();
		try {
			PatientCallLog entity = patientCallLogRepository.findById(id)
					.orElseThrow(() -> (new Exception("PatientCallLog Not Exist with ID : " + id)));
			model = EntityModelConverter.getPatienCallLogModel(entity);

		} catch (Exception e) {
			log.error("Error while get  PatientCallLog by id :: " + id, e);
			throw e;
		}

		return model;

	}

	public PatientCallLogModel getByPatientId(Integer id) throws Exception {

		PatientCallLogModel model = new PatientCallLogModel();
		try {
			PatientCallLog entity = patientCallLogRepository.findAllByPatientIdAndActiveStatus(id)
					.orElseThrow(() -> (new Exception("PatientCallLog Not Exist with ID : " + id)));
			model = EntityModelConverter.getPatienCallLogModel(entity);

		} catch (Exception e) {
			log.error("Error while get  PatientCallLog by Patient id :: " + id, e);
			throw e;
		}

		return model;

	}

	public boolean delete(Integer id) {
		boolean deleted = false;
		try {
			patientCallLogRepository.deleteById(id);
			deleted = true;
		} catch (Exception e) {
			log.error("Error while delete  PatientCallLog by id :: " + id, e);
		}

		return deleted;

	}

	public List<PatientCallLogModel> findAll() {
		return this.findAllByRole(null);
	}

	public List<PatientCallLogModel> findAllByRole(String role) {
		List<PatientCallLogModel> modelsList = new ArrayList<PatientCallLogModel>();
		try {
			List<PatientCallLog> entityList = patientCallLogRepository.findAll();

			modelsList = ListModelObject.getListPatientCallLogModelFromListEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  PatientCallLogs ", e);
		}
		return modelsList;
	}

	public Page<PatientCallLogModel> findAll(PatinetLogSearchQuery commonCriteria) {
		try {

			SortInfo sort = new SortInfo();
			sort.setColumnName("nextMonthAppointmentDate");
			sort.setOrder(1);

			List<SortInfo> sortInfo = new ArrayList<SortInfo>();
			sortInfo.add(sort);
			commonCriteria.setSort(sortInfo);
			Page<PatientCallLog> entityList = patientCallLogRepository.findAllPatinetLog(
					commonCriteria.getPatientName(), commonCriteria.getCallType(),
					Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPagePatientCallLogModelModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  PatientCallLogs ", e);
			throw e;
		}
	}
	
	public Page<PatientCallLogModel> findAllHistory(PatinetLogSearchQuery commonCriteria) {
		try {

			SortInfo sort = new SortInfo();
			sort.setColumnName("creationDate");
			sort.setOrder(0);

			List<SortInfo> sortInfo = new ArrayList<SortInfo>();
			sortInfo.add(sort);
			commonCriteria.setSort(sortInfo);
			Page<PatientCallLog> entityList = patientCallLogRepository.findAllPatinetLogHistory(
					commonCriteria.getPatientName(), commonCriteria.getCallType(),
					Util.getPageObjectFromCriteria(commonCriteria));
			return PageModelObjects.getPagePatientCallLogModelModelFromPageEntities(entityList);
		} catch (Exception e) {
			log.error("Error while findAll  PatientCallLogs ", e);
			throw e;
		}
	}

}
