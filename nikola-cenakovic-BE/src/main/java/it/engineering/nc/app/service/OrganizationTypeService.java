package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import it.engineering.nc.app.dto.OrganizationTypeDto;


public interface OrganizationTypeService {
	List<OrganizationTypeDto> findAll();
	
	Optional<OrganizationTypeDto> findById(Long id);
}
