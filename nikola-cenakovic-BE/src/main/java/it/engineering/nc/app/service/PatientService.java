package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.dto.PatientDto;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.exception.MyProjectException;
import it.engineering.nc.app.specification.SearchCriteria;

public interface PatientService {

	List<PatientDto> findAll();
	
	Page<PatientDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy);
	
	Page<PatientDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria);
	
	Optional<PatientDto> findById(Long id);
	
	PatientDto save(PatientDto patientDto) throws EntityExistsException;

	PatientDto update(PatientDto patientDto) throws InvalidEntityException ;
	
	void delete(Long id) throws MyProjectException ;

	Optional<PatientDto> findByIdentifier(String identifier);

	void bulkImport(List<PatientDto> patients) throws EntityExistsException;

	Long count(List<SearchCriteria> searchCriteria);
}
