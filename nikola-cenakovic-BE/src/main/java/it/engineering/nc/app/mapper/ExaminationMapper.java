package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.ExaminationDto;
import it.engineering.nc.app.entity.ExaminationEntity;


@Mapper
public interface ExaminationMapper {
	ExaminationEntity toEntity(ExaminationDto examinationDto);
	
	ExaminationDto toDto(ExaminationEntity examinationEntity);
}
