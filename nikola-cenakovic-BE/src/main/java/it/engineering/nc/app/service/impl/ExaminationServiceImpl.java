package it.engineering.nc.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.dto.ExaminationDto;
import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.entity.ExaminationEntity;
import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.enums.ExaminationStatusType;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.mapper.ExaminationMapper;
import it.engineering.nc.app.repository.ExaminationRepository;
import it.engineering.nc.app.service.ExaminationService;
import it.engineering.nc.app.specification.GenericFilterSpecification;
import it.engineering.nc.app.specification.SearchCriteria;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	private final ExaminationMapper examinationMapper = Mappers.getMapper(ExaminationMapper.class);
	
	private final ExaminationRepository examinationRepository;
	
	public ExaminationServiceImpl(ExaminationRepository examinationRepository) {
		super();
		this.examinationRepository = examinationRepository;
	}
	
	@Override
	public List<ExaminationDto> findAll() {
		List<ExaminationEntity> entities = examinationRepository.findAll();
		return entities.stream().map(entity -> {
			return examinationMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public Long count(List<SearchCriteria> searchCriteria) {
		GenericFilterSpecification<ExaminationEntity> specification = new GenericFilterSpecification<ExaminationEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
        return examinationRepository.count(specification);
	}

	@Override
	public Page<ExaminationDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		Page<ExaminationEntity> entities = examinationRepository.findAll(pageable);
		return entities.map(examinationMapper::toDto);
	}
	
	@Override
	public Page<ExaminationDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		
		GenericFilterSpecification<ExaminationEntity> specification = new GenericFilterSpecification<ExaminationEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
       
        return examinationRepository.findAll(specification,pageable).map(examinationMapper::toDto);
	}
	
	@Override
	public Optional<ExaminationDto> findById(Long id) {
		Optional<ExaminationEntity> entity = examinationRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(examinationMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

	@Override
	public ExaminationDto save(ExaminationDto examinationDto) throws EntityExistsException {
		Optional<ExaminationEntity> entity = examinationRepository.findById(examinationDto.getIdExamination());
		if (entity.isPresent()) 
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		
		ExaminationEntity examination = examinationMapper.toEntity(examinationDto);
		examination = examinationRepository.save(examination);
		return examinationMapper.toDto(examination);
	}
	
	@Transactional
	@Override
	public void bulkImport(List<ExaminationDto> examinations) throws EntityExistsException {
		for(ExaminationDto examinationDto: examinations) {
			Optional<ExaminationEntity> entity = examinationRepository.findById(examinationDto.getIdExamination());
			if (entity.isPresent()) 
				throw new EntityExistsException(entity.get(), "Organization already exists!");
		
			ExaminationEntity examination = examinationMapper.toEntity(examinationDto);
			examination = examinationRepository.save(examination);
		}
	}

	@Override
	public ExaminationDto update(ExaminationDto examinationDto) throws InvalidEntityException {
		Optional<ExaminationEntity> entity = examinationRepository.findById(examinationDto.getIdExamination());
		if (!entity.isPresent()) {
			throw new InvalidEntityException("Organization with id "+examinationDto.getIdExamination() +" does not exist!");
		}
		ExaminationEntity examination = examinationMapper.toEntity(examinationDto);
		examination = examinationRepository.save(examination);
		return examinationMapper.toDto(examination);
	}

	@Transactional
	@Override
	public void delete(Long id) throws InvalidEntityException {
		Optional<ExaminationEntity> entity = examinationRepository.findById(id);
		if (!entity.isPresent()) {
			throw new InvalidEntityException("Organization with id "+id +" does not exist!");
		}
		entity.get().setStatus(ExaminationStatusType.ENTERED_IN_ERROR);
		examinationRepository.save(entity.get());
	}

	
}
