 package it.engineering.nc.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name="organization")
@Where(clause = "active=true")
public class OrganizationEntity implements Serializable {
	private static final long serialVersionUID = 3859583533242867373L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_organization")
	private Long idOrganization;
	
	@Column(length = 50, unique = true)
	private String identifier;

	@Column(nullable = false)
	private boolean active;

	@ManyToOne
	@JoinColumn(name="id_organization_type", nullable = false)
	private OrganizationTypeEntity organizationType;

	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	@Column(length = 50)
	private String address;

	@Column(length = 50)
	private String phone;

	@Column(length = 50)
	private String email;

	public OrganizationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationEntity(Long idOrganization, String identifier, boolean active,
			OrganizationTypeEntity organizationType, String name, String address, String phone, String email) {
		super();
		this.idOrganization = idOrganization;
		this.identifier = identifier;
		this.active = active;
		this.organizationType = organizationType;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public Long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
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

	public OrganizationTypeEntity getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationTypeEntity organizationType) {
		this.organizationType = organizationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public int hashCode() {
		return Objects.hash(active, address, email, idOrganization, identifier, name, organizationType, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationEntity other = (OrganizationEntity) obj;
		return active == other.active && Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(idOrganization, other.idOrganization) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(name, other.name) && Objects.equals(organizationType, other.organizationType)
				&& Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "OrganizationEntity [idOrganization=" + idOrganization + ", identifier=" + identifier + ", active="
				+ active + ", organizationType=" + organizationType + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + "]";
	}

}
