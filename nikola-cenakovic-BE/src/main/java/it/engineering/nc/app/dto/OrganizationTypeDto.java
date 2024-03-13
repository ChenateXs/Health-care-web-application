package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrganizationTypeDto implements Serializable {
	private static final long serialVersionUID = 8452370469899568313L;

	@NotNull
	private Long idOrganizationType;

	@NotEmpty
	private String name;

	public OrganizationTypeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationTypeDto(@NotNull Long idOrganizationType, @NotEmpty String name) {
		super();
		this.idOrganizationType = idOrganizationType;
		this.name = name;
	}

	public Long getIdOrganizationType() {
		return idOrganizationType;
	}

	public void setIdOrganizationType(Long idOrganizationType) {
		this.idOrganizationType = idOrganizationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOrganizationType, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationTypeDto other = (OrganizationTypeDto) obj;
		return Objects.equals(idOrganizationType, other.idOrganizationType) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "OrganizationTypeDto [idOrganizationType=" + idOrganizationType + ", name=" + name + "]";
	}


}
