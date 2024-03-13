package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.engineering.nc.app.dto.PractitionerDto;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.exception.MyProjectException;
import it.engineering.nc.app.specification.SearchCriteria;

public interface PractitionerService {

	List<PractitionerDto> findAll();
	
	List<PractitionerDto> findAllDoctorOfMadicine();
	
	List<PractitionerDto> findAllByIdentifiers(List<String> identifiers);
	
	Page<PractitionerDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy);
	
	Page<PractitionerDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria);
	
	Optional<PractitionerDto> findById(Long id);
	
	Optional<PractitionerDto> findByIdentifier(String identifier);
	
	PractitionerDto save(PractitionerDto practitionerDto) throws EntityExistsException;

	PractitionerDto update(PractitionerDto practitionerDto) throws InvalidEntityException ;
	
	void delete(Long id) throws MyProjectException ;

	void bulkImport(List<PractitionerDto> practitioners) throws EntityExistsException;

	Long count(List<SearchCriteria> searchCriteria);

}
