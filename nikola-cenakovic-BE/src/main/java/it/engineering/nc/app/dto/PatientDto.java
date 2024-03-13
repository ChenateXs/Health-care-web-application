package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.enums.GenderType;
import it.engineering.nc.app.enums.PatientMaritalStatusType;
import it.engineering.nc.app.enums.PractitionerQualificationType;

public class PatientDto implements Serializable {
	private static final long serialVersionUID = -6322088769337722896L;


	@NotNull
	private Long idPatient;

	@Size(min = 5)
	private String identifier;

	@Size(min = 3)
	private String name;

	@Size(min = 3)
	private String surname;
	
	private GenderType gender;
	
	private Date birthDate;
	
	private String address;
	
	private String phone;

	@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;

	private boolean deceased;

	private PatientMaritalStatusType maritalStatus;
	
	private OrganizationDto organization;

	@JsonIgnoreProperties({ "organization", "examinations" })
	private PractitionerDto careProvider;

	public PatientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientDto(@NotNull Long idPatient, @Size(min = 5) String identifier, @Size(min = 3) String name,
			@Size(min = 3) String surname, GenderType gender, Date birthDate, String address, String phone,
			@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String email,
			boolean deceased, PatientMaritalStatusType maritalStatus, OrganizationDto organization,
			PractitionerDto careProvider) {
		super();
		this.idPatient = idPatient;
		this.identifier = identifier;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.deceased = deceased;
		this.maritalStatus = maritalStatus;
		this.organization = organization;
		this.careProvider = careProvider;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	public PatientMaritalStatusType getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(PatientMaritalStatusType maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public PractitionerDto getCareProvider() {
		return careProvider;
	}

	public void setCareProvider(PractitionerDto careProvider) {
		this.careProvider = careProvider;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, careProvider, deceased, email, gender, idPatient, identifier,
				maritalStatus, name, organization, phone, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientDto other = (PatientDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(careProvider, other.careProvider) && deceased == other.deceased
				&& Objects.equals(email, other.email) && gender == other.gender
				&& Objects.equals(idPatient, other.idPatient) && Objects.equals(identifier, other.identifier)
				&& maritalStatus == other.maritalStatus && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "PatientDto [idPatient=" + idPatient + ", identifier=" + identifier + ", name=" + name + ", surname="
				+ surname + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", deceased=" + deceased + ", maritalStatus=" + maritalStatus
				+ ", organization=" + organization + ", careProvider=" + careProvider + "]";
	}

}
