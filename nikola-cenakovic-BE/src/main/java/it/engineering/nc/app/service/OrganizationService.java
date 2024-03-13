package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.exception.MyProjectException;
import it.engineering.nc.app.specification.SearchCriteria;

public interface OrganizationService {
	List<OrganizationDto> findAll();
	
	Page<OrganizationDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy);
	
	Page<OrganizationDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria);
	
	Optional<OrganizationDto> findById(Long id);
	
	OrganizationDto save(OrganizationDto organizationDto) throws EntityExistsException;

	OrganizationDto update(OrganizationDto organizationDto) throws InvalidEntityException ;
	
	void delete(Long id) throws MyProjectException ;

	void bulkImport(List<OrganizationDto> organizationDto) throws EntityExistsException;

	Optional<OrganizationDto> findByIdentifier(String identifier);

	Long count(List<SearchCriteria> searchCriteria);
}
