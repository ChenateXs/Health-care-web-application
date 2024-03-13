package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.PatientDto;
import it.engineering.nc.app.entity.PatientEntity;


@Mapper
public interface PatientMapper {
	PatientEntity toEntity(PatientDto patientDto);
	
	PatientDto toDto(PatientEntity patientEntity);
}
