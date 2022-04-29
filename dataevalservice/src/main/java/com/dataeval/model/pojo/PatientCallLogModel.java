package com.dataeval.model.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class PatientCallLogModel extends AuditModel {

	private Integer id;
	private String callRecordStatus;
	private UserModel user;
	private String patientStatus;
	private Date visitDate;
	private String callType;
	private String careTeamMemberFirstName;
	private String careTeamMemberLastName;
	private String signature;
	private Date nextMonthAppointmentDate;
	private String additionalNotes;
	private String managingSymptoms;
	private String measurableTreatmentOutcome;
	private String healthConditionsToDiscuss;
	private Integer remaingTime;
	private Integer timeSpentInSession;
	private Integer totalTimeSpent;
	private String finalReocrd;
	private String monthlyStatus;
}
