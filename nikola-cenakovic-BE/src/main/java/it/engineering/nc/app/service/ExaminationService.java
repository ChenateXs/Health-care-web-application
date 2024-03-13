package it.engineering.nc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import it.engineering.nc.app.dto.ExaminationDto;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.exception.MyProjectException;
import it.engineering.nc.app.specification.SearchCriteria;

public interface ExaminationService {
	List<ExaminationDto> findAll();
	
	Page<ExaminationDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy);
	
	Page<ExaminationDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria);
	
	Optional<ExaminationDto> findById(Long id);
	
	ExaminationDto save(ExaminationDto examinationDto) throws EntityExistsException;

	ExaminationDto update(ExaminationDto examinationDto) throws InvalidEntityException ;
	
	void delete(Long id) throws MyProjectException ;

	void bulkImport(List<ExaminationDto> examinations) throws EntityExistsException;

	Long count(List<SearchCriteria> searchCriteria);
}
