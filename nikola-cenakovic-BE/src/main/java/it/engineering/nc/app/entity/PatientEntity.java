package it.engineering.nc.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.engineering.nc.app.enums.GenderType;
import it.engineering.nc.app.enums.PatientMaritalStatusType;
import it.engineering.nc.app.enums.PractitionerQualificationType;

@Entity
@Table(name="patient")
@Where(clause = "active=true")
public class PatientEntity implements Serializable{
	private static final long serialVersionUID = -4790933736194256602L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_patient")
	private Long idPatient;
	
	@Column(length = 50, unique = true)
	private String identifier;

	@Column(nullable = false)
	private boolean active;

	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String surname;
	
	private GenderType gender;
	
	@Column(nullable = false, name = "birth_date")
	private Date birthDate;

	@Column(length = 50)
	private String address;
	
	@Column(length = 50)
	private String phone;

	@Column(length = 50)
	private String email;
	
	private boolean deceased;

	@Column(name = "marital_status")
	private PatientMaritalStatusType maritalStatus;
	
	@ManyToOne
	@JoinColumn(name="id_organization")
	private OrganizationEntity organization;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_practitioner")
	private PractitionerEntity careProvider;


	public PatientEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PatientEntity(Long idPatient, String identifier, boolean active, String name, String surname,
			GenderType gender, Date birthDate, String address, String phone, String email, boolean deceased,
			PatientMaritalStatusType maritalStatus, OrganizationEntity organization, PractitionerEntity careProvider) {
		super();
		this.idPatient = idPatient;
		this.identifier = identifier;
		this.active = active;
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


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
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


	public OrganizationEntity getOrganization() {
		return organization;
	}


	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}


	public PractitionerEntity getCareProvider() {
		return careProvider;
	}


	public void setCareProvider(PractitionerEntity careProvider) {
		this.careProvider = careProvider;
	}


	@Override
	public int hashCode() {
		return Objects.hash(active, address, birthDate, careProvider, deceased, email, gender, idPatient, identifier,
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
		PatientEntity other = (PatientEntity) obj;
		return active == other.active && Objects.equals(address, other.address)
				&& Objects.equals(birthDate, other.birthDate) && Objects.equals(careProvider, other.careProvider)
				&& deceased == other.deceased && Objects.equals(email, other.email) && gender == other.gender
				&& Objects.equals(idPatient, other.idPatient) && Objects.equals(identifier, other.identifier)
				&& maritalStatus == other.maritalStatus && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& Objects.equals(surname, other.surname);
	}


	@Override
	public String toString() {
		return "PatientEntity [idPatient=" + idPatient + ", identifier=" + identifier + ", active=" + active + ", name="
				+ name + ", surname=" + surname + ", gender=" + gender + ", birthDate=" + birthDate + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", deceased=" + deceased + ", maritalStatus="
				+ maritalStatus + ", organization=" + organization + ", careProvider=" + careProvider + "]";
	}


}
