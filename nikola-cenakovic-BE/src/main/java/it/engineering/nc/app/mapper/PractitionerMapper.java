package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.PractitionerDto;
import it.engineering.nc.app.entity.PractitionerEntity;

@Mapper
public interface PractitionerMapper {
	PractitionerEntity toEntity(PractitionerDto practitionerDto);
	
	PractitionerDto toDto(PractitionerEntity practitionerEntity);

}
