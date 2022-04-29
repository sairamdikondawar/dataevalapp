package com.dataeval.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String password;
	private String status;
	private String userName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Role role;

	private String address;
	private String alternateContact;
	private String insuranceNumber;
	private String medicalRecordNumber;
	private String pharmacyFaxNumber;
	private String pharmacyName;
	private String pharmacyPhoneNumber;
	private String phoneNumber;
	private String refferal;
	private String mobileNumber;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// bi-directional one-to-one association to Role
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "DATE_OF_BIRTH")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "PHARMACY_FAX_NUMBER")
	public String getPharmacyFaxNumber() {
		return this.pharmacyFaxNumber;
	}

	public void setPharmacyFaxNumber(String pharmacyFaxNumber) {
		this.pharmacyFaxNumber = pharmacyFaxNumber;
	}

	@Column(name = "PHARMACY_NAME")
	public String getPharmacyName() {
		return this.pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	@Column(name = "PHARMACY_PHONE_NUMBER")
	public String getPharmacyPhoneNumber() {
		return this.pharmacyPhoneNumber;
	}

	public void setPharmacyPhoneNumber(String pharmacyPhoneNumber) {
		this.pharmacyPhoneNumber = pharmacyPhoneNumber;
	}

	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRefferal() {
		return this.refferal;
	}

	public void setRefferal(String refferal) {
		this.refferal = refferal;
	}

	@Column(name = "MEDICAL_RECORD_NUMBER")
	public String getMedicalRecordNumber() {
		return this.medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ALTERNATE_CONTACT")
	public String getAlternateContact() {
		return this.alternateContact;
	}

	public void setAlternateContact(String alternateContact) {
		this.alternateContact = alternateContact;
	}

	@Column(name = "INSURANCE_NUMBER")
	public String getInsuranceNumber() {
		return this.insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	@Column(name = "MOBILE_NUMBER")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	// bi-directional many-to-many association to Role
//	@ManyToMany
//	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//			@JoinColumn(name = "ROLE_ID") })
//	public List<Role> getRoles() {
//		return this.roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

}