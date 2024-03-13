package it.engineering.nc.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name="organization_type")
public class OrganizationTypeEntity implements Serializable {
	private static final long serialVersionUID = 1638911439364544937L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_organization_type")
	private Long idOrganizationType;

	@Column(length = 50, nullable = false)
	private String name;

	public OrganizationTypeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationTypeEntity(Long idOrganizationType, String name) {
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
		OrganizationTypeEntity other = (OrganizationTypeEntity) obj;
		return Objects.equals(idOrganizationType, other.idOrganizationType) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "OrganizationTypeEntity [idOrganizationType=" + idOrganizationType + ", name=" + name + "]";
	}
	
}
