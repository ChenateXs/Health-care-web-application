package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class OrganizationDto implements Serializable {

	private static final long serialVersionUID = 7310932286512783543L;

	@NotNull
	private Long idOrganization;
	
	@Size(min = 5)
	private String identifier;

	@NotNull
	private OrganizationTypeDto organizationType;

	@NotEmpty
	@Size(min = 5)
	private String name;

	private String address;

	private String phone;

	@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;

	public OrganizationDto() {
		super();
	}

	public OrganizationDto(@NotNull Long idOrganization, @Size(min = 5) String identifier,
			@NotNull OrganizationTypeDto organizationType, @NotEmpty @Size(min = 5) String name, String address,
			String phone,
			@Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String email) {
		super();
		this.idOrganization = idOrganization;
		this.identifier = identifier;
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

	public OrganizationTypeDto getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationTypeDto organizationType) {
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
		return Objects.hash(address, email, idOrganization, identifier, name, organizationType, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationDto other = (OrganizationDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(idOrganization, other.idOrganization) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(name, other.name) && Objects.equals(organizationType, other.organizationType)
				&& Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "OrganizationDto [idOrganization=" + idOrganization + ", identifier=" + identifier
				+ ", organizationType=" + organizationType + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + "]";
	}

}
