package it.engineering.nc.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_type")
public class ServiceTypeEntity implements Serializable {
	private static final long serialVersionUID = 9071298024511888538L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_service_type")
	private Long idServiceType;

	@Column(length = 50, nullable = false)
	private String name;

	public ServiceTypeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceTypeEntity(Long idServiceType, String name) {
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
		ServiceTypeEntity other = (ServiceTypeEntity) obj;
		return Objects.equals(idServiceType, other.idServiceType) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ServiceTypeEntity [idServiceType=" + idServiceType + ", name=" + name + "]";
	}
	
}
