package com.dataeval.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.PatientCallLog;

@Repository
public interface PatientCallLogRepository extends JpaRepository<PatientCallLog, Integer> {

	@Query("select log from PatientCallLog log where   log.callRecordStatus ='ACTIVE' and (?1 is null or ?1 = ''  or log.user.firstName like %?1%  or log.user.firstName like %?1%) and (?2 is null or ?2 = '' or log.callType =?2)")
	public Page<PatientCallLog> findAllPatinetLog(String patientName, String callType, Pageable pageable);
	
	@Query("select log from PatientCallLog log where    (?1 is null or ?1 = ''  or log.user.firstName like %?1%  or log.user.firstName like %?1%) and (?2 is null or ?2 = '' or log.callType =?2)")
	public Page<PatientCallLog> findAllPatinetLogHistory(String patientName, String callType, Pageable pageable);

	@Query("select log from PatientCallLog log where log.user.id = ?1 and log.callRecordStatus ='ACTIVE'")
	public Optional<PatientCallLog> findAllByPatientIdAndActiveStatus(Integer patientId);

}
