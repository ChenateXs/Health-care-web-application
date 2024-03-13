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

import it.engineering.nc.app.enums.ExaminationPriorityType;
import it.engineering.nc.app.enums.ExaminationStatusType;

@Entity
@Table(name="examination")
@Where(clause = "status!=6")
public class ExaminationEntity implements Serializable{
	private static final long serialVersionUID = -667846860144454944L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_examination")
	private Long idExamination;
	
	@Column(length = 50, unique = true)
	private String identifier;

	@Column(nullable = false)
	private ExaminationStatusType status;

	@ManyToOne
	@JoinColumn(name="id_service_type", nullable = false)
	private ServiceTypeEntity serviceType;
	
	private ExaminationPriorityType priority;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	

	@Column(length = 200)
	private String diagnosis;

	@ManyToOne
	@JoinColumn(name="id_organization")
	private OrganizationEntity organization;
	
	@ManyToOne
	@JoinColumn(name="id_patient")
	private PatientEntity patient;
	
	
    @ManyToMany
    @JoinTable(
	        name = "practitioner_examination", 
	        joinColumns = { @JoinColumn(name = "id_examination") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_practitioner") }
	    )
	private List<PractitionerEntity> practitioners;

	public ExaminationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExaminationEntity(Long idExamination, String identifier, ExaminationStatusType status,
			ServiceTypeEntity serviceType, ExaminationPriorityType priority, Date startDate, Date endDate,
			String diagnosis, OrganizationEntity organization, PatientEntity patient,
			List<PractitionerEntity> practitioners) {
		super();
		this.idExamination = idExamination;
		this.identifier = identifier;
		this.status = status;
		this.serviceType = serviceType;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.diagnosis = diagnosis;
		this.organization = organization;
		this.patient = patient;
		this.practitioners = practitioners;
	}

	public Long getIdExamination() {
		return idExamination;
	}

	public void setIdExamination(Long idExamination) {
		this.idExamination = idExamination;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ExaminationStatusType getStatus() {
		return status;
	}

	public void setStatus(ExaminationStatusType status) {
		this.status = status;
	}

	public ServiceTypeEntity getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceTypeEntity serviceType) {
		this.serviceType = serviceType;
	}

	public ExaminationPriorityType getPriority() {
		return priority;
	}

	public void setPriority(ExaminationPriorityType priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public List<PractitionerEntity> getPractitioners() {
		return practitioners;
	}

	public void setPractitioners(List<PractitionerEntity> practitioners) {
		this.practitioners = practitioners;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diagnosis, endDate, idExamination, identifier, organization, patient, practitioners,
				priority, serviceType, startDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExaminationEntity other = (ExaminationEntity) obj;
		return Objects.equals(diagnosis, other.diagnosis) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(idExamination, other.idExamination) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(organization, other.organization) && Objects.equals(patient, other.patient)
				&& Objects.equals(practitioners, other.practitioners) && priority == other.priority
				&& Objects.equals(serviceType, other.serviceType) && Objects.equals(startDate, other.startDate)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "ExaminationEntity [idExamination=" + idExamination + ", identifier=" + identifier + ", status=" + status
				+ ", serviceType=" + serviceType + ", priority=" + priority + ", startDate=" + startDate + ", endDate="
				+ endDate + ", diagnosis=" + diagnosis + ", organization=" + organization + ", patient=" + patient
				+ ", practitioners=" + practitioners + "]";
	}

}
