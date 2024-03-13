package it.engineering.nc.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.engineering.nc.app.dto.OrganizationTypeDto;
import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.OrganizationTypeEntity;
import it.engineering.nc.app.mapper.OrganizationTypeMapper;
import it.engineering.nc.app.repository.OrganizationTypeRepository;
import it.engineering.nc.app.service.OrganizationTypeService;


@Service
public class OrganizationTypeServiceImpl implements OrganizationTypeService{

	private final OrganizationTypeMapper organizationTypeMapper = Mappers.getMapper(OrganizationTypeMapper.class);
	
	private final OrganizationTypeRepository organizationTypeRepository;
	
	public OrganizationTypeServiceImpl(OrganizationTypeRepository organizationTypeRepository) {
		super();
		this.organizationTypeRepository = organizationTypeRepository;
	}

	@Override
	public List<OrganizationTypeDto> findAll() {
		List<OrganizationTypeEntity> entities = organizationTypeRepository.findAll();
		return entities.stream().map(entity -> {
			return organizationTypeMapper.toDto(entity);
		}).collect(Collectors.toList());	
	}

	@Override
	public Optional<OrganizationTypeDto> findById(Long id) {
		Optional<OrganizationTypeEntity> entity = organizationTypeRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(organizationTypeMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

}
