package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.ServiceTypeDto;
import it.engineering.nc.app.entity.ServiceTypeEntity;

@Mapper
public interface ServiceTypeMapper {
	ServiceTypeEntity toEntity(ServiceTypeDto serviceTypeDto);
	
	ServiceTypeDto toDto(ServiceTypeEntity serviceTypeEntity);
}
