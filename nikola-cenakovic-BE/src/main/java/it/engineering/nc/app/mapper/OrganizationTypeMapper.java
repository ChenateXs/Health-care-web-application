package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.OrganizationTypeDto;
import it.engineering.nc.app.entity.OrganizationTypeEntity;

@Mapper
public interface OrganizationTypeMapper {
	OrganizationTypeEntity toEntity(OrganizationTypeDto organizationTypeDto);
	
	OrganizationTypeDto toDto(OrganizationTypeEntity organizationTypeEntity);
}
