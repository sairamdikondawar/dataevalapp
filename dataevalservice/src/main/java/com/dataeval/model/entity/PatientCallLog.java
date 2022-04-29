package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the PATIENT_CALL_RECORD database table.
 * 
 */
@Entity
@Table(name = "PATIENT_CALL_RECORD")
@NamedQuery(name = "PatientCallLog.findAll", query = "SELECT p FROM PatientCallLog p")
public class PatientCallLog extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String callRecordStatus;
	private User user;
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

	public PatientCallLog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CALL_RECORD_STATUS")
	public String getCallRecordStatus() {
		return this.callRecordStatus;
	}

	public void setCallRecordStatus(String callRecordStatus) {
		this.callRecordStatus = callRecordStatus;
	}

	@Column(name = "PATIENT_STATUS")
	public String getPatientStatus() {
		return this.patientStatus;
	}

	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}

	@Column(name = "VISIT_DATE")
	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CALL_TYPE")
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	@Column(name = "CARE_TEAM_MEMBER_FIRST_NAME")
	public String getCareTeamMemberFirstName() {
		return careTeamMemberFirstName;
	}

	public void setCareTeamMemberFirstName(String careTeamMemberFirstName) {
		this.careTeamMemberFirstName = careTeamMemberFirstName;
	}

	@Column(name = "CARE_TEAM_MEMBER_LAST_NAME")
	public String getCareTeamMemberLastName() {
		return careTeamMemberLastName;
	}

	public void setCareTeamMemberLastName(String careTeamMemberLastName) {
		this.careTeamMemberLastName = careTeamMemberLastName;
	}

	@Column(name = "SIGNATURE")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NEXT_MONTH_APPOINTMENT_DATE")
	public Date getNextMonthAppointmentDate() {
		return nextMonthAppointmentDate;
	}

	public void setNextMonthAppointmentDate(Date nextMonthAppointmentDate) {
		this.nextMonthAppointmentDate = nextMonthAppointmentDate;
	}

	@Column(name = "ADDITIONAL_NOTES")
	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	@Column(name = "MANAGING_SYMPTOMS")
	public String getManagingSymptoms() {
		return managingSymptoms;
	}

	public void setManagingSymptoms(String managingSymptoms) {
		this.managingSymptoms = managingSymptoms;
	}

	@Column(name = "MEASURABLE_TREATMENT_OUTCOME")
	public String getMeasurableTreatmentOutcome() {
		return measurableTreatmentOutcome;
	}

	public void setMeasurableTreatmentOutcome(String measurableTreatmentOutcome) {
		this.measurableTreatmentOutcome = measurableTreatmentOutcome;
	}

	@Column(name = "HEALTH_CONDITIONS_TO_DISCUSS")
	public String getHealthConditionsToDiscuss() {
		return healthConditionsToDiscuss;
	}

	public void setHealthConditionsToDiscuss(String healthConditionsToDiscuss) {
		this.healthConditionsToDiscuss = healthConditionsToDiscuss;
	}

	@Column(name = "REMAINING_TIME")
	public Integer getRemaingTime() {
		return remaingTime;
	}

	public void setRemaingTime(Integer remaingTime) {
		this.remaingTime = remaingTime;
	}

	@Column(name = "TIME_SPENT_IN_SESSION")
	public Integer getTimeSpentInSession() {
		return timeSpentInSession;
	}

	public void setTimeSpentInSession(Integer timeSpentInSession) {
		this.timeSpentInSession = timeSpentInSession;
	}

	@Column(name = "TOTAL_TIME_SPENT")
	public Integer getTotalTimeSpent() {
		return totalTimeSpent;
	}

	public void setTotalTimeSpent(Integer totalTimeSpent) {
		this.totalTimeSpent = totalTimeSpent;
	}

	@Column(name = "FINAL_RECORD")
	public String getFinalReocrd() {
		return finalReocrd;
	}

	public void setFinalReocrd(String finalReocrd) {
		this.finalReocrd = finalReocrd;
	}

	@Column(name = "MONTHLY_STATUS")
	public String getMonthlyStatus() {
		return monthlyStatus;
	}

	public void setMonthlyStatus(String monthlyStatus) {
		this.monthlyStatus = monthlyStatus;
	}
}