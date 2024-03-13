package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import it.engineering.nc.app.dto.ServiceTypeDto;


public interface ServiceTypeService {

	List<ServiceTypeDto> findAll();
	
	Optional<ServiceTypeDto> findById(Long id);
}
