package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServiceTypeDto implements Serializable {
	private static final long serialVersionUID = -6990637932958010110L;

	@NotNull
	private Long idServiceType;

	@NotEmpty
	private String name;

	public ServiceTypeDto() {
		super();
	}

	public ServiceTypeDto(@NotNull Long idServiceType, @NotEmpty String name) {
		super();
		this.idServiceType = idServiceType;
		this.name = name;
	}

	public Long getIdServiceType() {
		return idServiceType;
	}

	public void setIdServiceType(Long idServiceType) {
		this.idServiceType = idServiceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idServiceType, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceTypeDto other = (ServiceTypeDto) obj;
		return Objects.equals(idServiceType, other.idServiceType) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ServiceTypeDto [idServiceType=" + idServiceType + ", name=" + name + "]";
	}

	
	
}
