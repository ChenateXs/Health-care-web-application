package it.engineering.nc.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import it.engineering.nc.app.dto.ServiceTypeDto;
import it.engineering.nc.app.entity.OrganizationTypeEntity;
import it.engineering.nc.app.entity.ServiceTypeEntity;
import it.engineering.nc.app.mapper.ServiceTypeMapper;
import it.engineering.nc.app.repository.ServiceTypeRepository;
import it.engineering.nc.app.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

	private final ServiceTypeMapper serviceTypeMapper = Mappers.getMapper(ServiceTypeMapper.class);
	
	private final ServiceTypeRepository serviceTypeRepository;
	
	public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
		super();
		this.serviceTypeRepository = serviceTypeRepository;
	}
	
	@Override
	public List<ServiceTypeDto> findAll() {
		List<ServiceTypeEntity> entities = serviceTypeRepository.findAll();
		return entities.stream().map(entity -> {
			return serviceTypeMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<ServiceTypeDto> findById(Long id) {
		Optional<ServiceTypeEntity> entity = serviceTypeRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(serviceTypeMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

}
