package it.engineering.nc.app.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PatientEntity;
import it.engineering.nc.app.entity.PractitionerEntity;
import it.engineering.nc.app.entity.ServiceTypeEntity;
import it.engineering.nc.app.enums.ExaminationPriorityType;
import it.engineering.nc.app.enums.ExaminationStatusType;

public class ExaminationDto implements Serializable {
	private static final long serialVersionUID = -702147557844844261L;

	@NotNull
	private Long idExamination;

	@Size(min = 5)
	private String identifier;

	@NotNull
	private ExaminationStatusType status;

	@NotNull
	private ServiceTypeDto serviceType;
	
	private ExaminationPriorityType priority;
	
	private Date startDate;

	private Date endDate;
	
	private String diagnosis;

	private OrganizationDto organization;

	@JsonIgnoreProperties({ "careProvider",  "organization" })
	private PatientDto patient;

	@JsonIgnoreProperties({ "organization" })
	private List<PractitionerDto> practitioners;

	public ExaminationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExaminationDto(@NotNull Long idExamination, @Size(min = 5) String identifier,
			@NotNull ExaminationStatusType status, @NotNull ServiceTypeDto serviceType,
			ExaminationPriorityType priority, Date startDate, Date endDate, String diagnosis,
			OrganizationDto organization, PatientDto patient, List<PractitionerDto> practitioners) {
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

	public ServiceTypeDto getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceTypeDto serviceType) {
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

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public List<PractitionerDto> getPractitioners() {
		return practitioners;
	}

	public void setPractitioners(List<PractitionerDto> practitioners) {
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
		ExaminationDto other = (ExaminationDto) obj;
		return Objects.equals(diagnosis, other.diagnosis) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(idExamination, other.idExamination) && Objects.equals(identifier, other.identifier)
				&& Objects.equals(organization, other.organization) && Objects.equals(patient, other.patient)
				&& Objects.equals(practitioners, other.practitioners) && priority == other.priority
				&& Objects.equals(serviceType, other.serviceType) && Objects.equals(startDate, other.startDate)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "ExaminationDto [idExamination=" + idExamination + ", identifier=" + identifier + ", status=" + status
				+ ", serviceType=" + serviceType + ", priority=" + priority + ", startDate=" + startDate + ", endDate="
				+ endDate + ", diagnosis=" + diagnosis + ", organization=" + organization + ", patient=" + patient
				+ ", practitioners=" + practitioners + "]";
	}

}
