package it.engineering.nc.app.mapper;

import org.mapstruct.Mapper;

import it.engineering.nc.app.dto.UserDto;
import it.engineering.nc.app.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity toEntity(UserDto userDto);
	
	UserDto toDto(UserEntity userEntity);
}
