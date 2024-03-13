package it.engineering.nc.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.dto.PatientDto;
import it.engineering.nc.app.dto.PractitionerDto;
import it.engineering.nc.app.entity.ExaminationEntity;
import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PatientEntity;
import it.engineering.nc.app.entity.PractitionerEntity;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.ExaminationStillRunningException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.mapper.PatientMapper;
import it.engineering.nc.app.repository.ExaminationRepository;
import it.engineering.nc.app.repository.PatientRepository;
import it.engineering.nc.app.service.PatientService;
import it.engineering.nc.app.specification.GenericFilterSpecification;
import it.engineering.nc.app.specification.SearchCriteria;
import it.engineering.nc.app.specification.SearchOperation;

@Service
public class PatientServiceImpl implements PatientService {
	private final PatientMapper patientMapper = Mappers.getMapper(PatientMapper.class);

	private final PatientRepository patientRepository;
	private final ExaminationRepository examinationRepository;

	public PatientServiceImpl(PatientRepository patientRepository,ExaminationRepository examinationRepository) {
		super();
		this.patientRepository = patientRepository;
		this.examinationRepository = examinationRepository;
	}

	@Override
	public List<PatientDto> findAll() {
		List<PatientEntity> entities = patientRepository.findAll();
		return entities.stream().map(entity -> {
			return patientMapper.toDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<PatientDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		Page<PatientEntity> entities = patientRepository.findAll(pageable);
		return entities.map(patientMapper::toDto);
	}
	
	@Override
	public Page<PatientDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		
		GenericFilterSpecification<PatientEntity> specification = new GenericFilterSpecification<PatientEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
       
        return patientRepository.findAll(specification,pageable).map(patientMapper::toDto);
	}

	@Override
	public Long count(List<SearchCriteria> searchCriteria) {
		GenericFilterSpecification<PatientEntity> specification = new GenericFilterSpecification<PatientEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
        return patientRepository.count(specification);
	}
	
	@Override
	public Optional<PatientDto> findById(Long id) {
		Optional<PatientEntity> entity = patientRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(patientMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}
	@Override
	public Optional<PatientDto> findByIdentifier(String identifier) {
		Optional<PatientEntity> entity = patientRepository.findByIdentifier(identifier);
		if(entity.isPresent())
			return Optional.of(patientMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

	@Override
	public PatientDto save(PatientDto patientDto) throws EntityExistsException {
		Optional<PatientEntity> entity = patientRepository.findById(patientDto.getIdPatient());
		if (entity.isPresent()) 
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		
		PatientEntity patient = patientMapper.toEntity(patientDto);
		patient.setActive(true);
		System.out.println(patient);
		patient = patientRepository.save(patient);
		return patientMapper.toDto(patient);
	}

	@Override
	public void bulkImport(List<PatientDto> patients) throws EntityExistsException {
		for(PatientDto patientDto: patients) {
			Optional<PatientEntity> entity = patientRepository.findById(patientDto.getIdPatient());
			if (entity.isPresent()) 
				throw new EntityExistsException(entity.get(), "Organization already exists!");
		
			PatientEntity patient = patientMapper.toEntity(patientDto);
			patient.setActive(true);
			patient = patientRepository.save(patient);
		}
	}
	
	@Override
	public PatientDto update(PatientDto patientDto) throws InvalidEntityException {
		Optional<PatientEntity> entity = patientRepository.findById(patientDto.getIdPatient());
		if (!entity.isPresent()) {
			throw new InvalidEntityException("Organization with id "+patientDto.getIdPatient() +" does not exist!");
		}
		PatientEntity practitioner = patientMapper.toEntity(patientDto);
		practitioner.setActive(true);
		practitioner = patientRepository.save(practitioner);
		return patientMapper.toDto(practitioner);
	}

	@Transactional
	@Override
	public void delete(Long id) throws InvalidEntityException, ExaminationStillRunningException{
		Optional<PatientEntity> entity = patientRepository.findById(id);
		if (!entity.isPresent()) 
			throw new InvalidEntityException("Patient with id "+id +" does not exist!");	
		
		GenericFilterSpecification<ExaminationEntity> specification = new GenericFilterSpecification<ExaminationEntity>();
		specification.add(new SearchCriteria("patient",id ,SearchOperation.EQUAL));
		boolean exists = examinationRepository.exists(specification);

		if(exists)
			throw new ExaminationStillRunningException("Examination is still running patient with id: "+id+" can not be deleted!");
		
		entity.get().setActive(false);
		patientRepository.save(entity.get());
	}

	

}
