package it.engineering.nc.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.engineering.nc.app.enums.GenderType;
import it.engineering.nc.app.enums.PractitionerQualificationType;

@Entity
@Table(name="practitioner")
@Where(clause = "active=true")
public class PractitionerEntity implements Serializable {
	private static final long serialVersionUID = -4799655627911956041L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_practitioner")
	private Long idPractitioner;
	
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

	@Column(nullable = false)
	private PractitionerQualificationType qualification;
	
	@ManyToOne
	@JoinColumn(name="id_organization")
	private OrganizationEntity organization;

	public PractitionerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PractitionerEntity(Long idPractitioner, String identifier, boolean active, String name, String surname,
			GenderType gender, Date birthDate, String address, String phone, String email,
			PractitionerQualificationType qualification, OrganizationEntity organization) {
		super();
		this.idPractitioner = idPractitioner;
		this.identifier = identifier;
		this.active = active;
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

	public PractitionerQualificationType getQualification() {
		return qualification;
	}

	public void setQualification(PractitionerQualificationType qualification) {
		this.qualification = qualification;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, address, birthDate, email, gender, idPractitioner, identifier, name, organization,
				phone, qualification, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PractitionerEntity other = (PractitionerEntity) obj;
		return active == other.active && Objects.equals(address, other.address)
				&& Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& gender == other.gender && Objects.equals(idPractitioner, other.idPractitioner)
				&& Objects.equals(identifier, other.identifier) && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& qualification == other.qualification && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "PractitionerEntity [idPractitioner=" + idPractitioner + ", identifier=" + identifier + ", active="
				+ active + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDate=" + birthDate
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + ", qualification=" + qualification
				+ ", organization=" + organization + "]";
	}
	

}
