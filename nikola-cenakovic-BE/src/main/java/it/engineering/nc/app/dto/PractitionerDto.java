package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.engineering.nc.app.enums.GenderType;
import it.engineering.nc.app.enums.PractitionerQualificationType;

public class PractitionerDto implements Serializable {
	private static final long serialVersionUID = 3139005255665031103L;

	@NotNull
	private Long idPractitioner;

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
	
	private PractitionerQualificationType qualification;
	
	private OrganizationDto organization;

	public PractitionerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PractitionerDto(@NotNull Long idPractitioner, @Size(min = 5) String identifier, @Size(min = 3) String name,
			@Size(min = 3) String surname, GenderType gender, Date birthDate, String address, String phone,
			@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String email,
			PractitionerQualificationType qualification, OrganizationDto organization) {
		super();
		this.idPractitioner = idPractitioner;
		this.identifier = identifier;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
		this.organization = organization;
	}

	public Long getIdPractitioner() {
		return idPractitioner;
	}

	public void setIdPractitioner(Long idPractitioner) {
		this.idPractitioner = idPractitioner;
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

	public PractitionerQualificationType getQualification() {
		return qualification;
	}

	public void setQualification(PractitionerQualificationType qualification) {
		this.qualification = qualification;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, email, gender, idPractitioner, identifier, name, organization, phone,
				qualification, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PractitionerDto other = (PractitionerDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(email, other.email) && gender == other.gender
				&& Objects.equals(idPractitioner, other.idPractitioner) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(name, other.name) && Objects.equals(organization, other.organization)
				&& Objects.equals(phone, other.phone) && qualification == other.qualification
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "PractitionerDto [idPractitioner=" + idPractitioner + ", identifier=" + identifier + ", name=" + name
				+ ", surname=" + surname + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", qualification=" + qualification + ", organization="
				+ organization + "]";
	}

}
