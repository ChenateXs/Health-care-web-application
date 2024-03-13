package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.entity.OrganizationEntity;

@Mapper
public interface OrganizationMapper {
	OrganizationEntity toEntity(OrganizationDto organizationDto);
	
	OrganizationDto toDto(OrganizationEntity organizationEntity);
}
