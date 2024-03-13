package it.engineering.nc.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.nc.app.dto.OrganizationDto;
import it.engineering.nc.app.dto.PractitionerDto;
import it.engineering.nc.app.entity.ExaminationEntity;
import it.engineering.nc.app.entity.OrganizationEntity;
import it.engineering.nc.app.entity.PractitionerEntity;
import it.engineering.nc.app.enums.PractitionerQualificationType;
import it.engineering.nc.app.exception.EntityExistsException;
import it.engineering.nc.app.exception.ExaminationStillRunningException;
import it.engineering.nc.app.exception.InvalidEntityException;
import it.engineering.nc.app.mapper.PractitionerMapper;
import it.engineering.nc.app.repository.ExaminationRepository;
import it.engineering.nc.app.repository.PatientRepository;
import it.engineering.nc.app.repository.PractitionerRepository;
import it.engineering.nc.app.service.PractitionerService;
import it.engineering.nc.app.specification.GenericFilterSpecification;
import it.engineering.nc.app.specification.SearchCriteria;
import it.engineering.nc.app.specification.SearchOperation;

@Service
public class PractitionerServiceImpl implements PractitionerService{
	
	private final PractitionerMapper practitionerMapper = Mappers.getMapper(PractitionerMapper.class);
	
	private final PractitionerRepository practitionerRepository;
	private final ExaminationRepository examinationRepository;
	private final PatientRepository patientRepository;
	
	public PractitionerServiceImpl(
			PractitionerRepository practitionerRepository,
			ExaminationRepository examinationRepository,
			PatientRepository patientRepository) {
		super();
		this.practitionerRepository = practitionerRepository;
		this.examinationRepository = examinationRepository;
		this.patientRepository = patientRepository;
	}
	
	@Override
	public List<PractitionerDto> findAll() {
		List<PractitionerEntity> entities = practitionerRepository.findAll();
		return entities.stream().map(entity -> {
			return practitionerMapper.toDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<PractitionerDto> findAllDoctorOfMadicine() {
		GenericFilterSpecification<PractitionerEntity> specification = new GenericFilterSpecification<PractitionerEntity>();
		specification.add(new SearchCriteria("qualification",PractitionerQualificationType.DOCTOR_OF_MEDICINE ,SearchOperation.EQUAL));
		List<PractitionerEntity> entities = practitionerRepository.findAll(specification);
		return entities.stream().map(entity -> {
			return practitionerMapper.toDto(entity);
		}).collect(Collectors.toList());
	}


	@Override
	public List<PractitionerDto> findAllByIdentifiers(List<String> identifiers) {

		GenericFilterSpecification<PractitionerEntity> specification = new GenericFilterSpecification<PractitionerEntity>();
		for(String identifier :identifiers) {
			specification.add(new SearchCriteria("identifier",identifier ,SearchOperation.EQUAL));
		}
		List<PractitionerEntity> entities = practitionerRepository.findAll(specification);
		
		return entities.stream().map(entity -> {
			return practitionerMapper.toDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<PractitionerDto> getByPage(Integer pageNo, Integer pageSize,String direction, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		Page<PractitionerEntity> entities = practitionerRepository.findAll(pageable);
		return entities.map(practitionerMapper::toDto);
	}
	
	@Override
	public Page<PractitionerDto> getByPageFiltered(Integer pageNo, Integer pageSize,String direction, String sortBy, List<SearchCriteria> searchCriteria) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.valueOf(direction), sortBy);
		
		GenericFilterSpecification<PractitionerEntity> specification = new GenericFilterSpecification<PractitionerEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
       
        return practitionerRepository.findAll(specification,pageable).map(practitionerMapper::toDto);
	}
	
	@Override
	public Optional<PractitionerDto> findById(Long id) {
		Optional<PractitionerEntity> entity = practitionerRepository.findById(id);
		if(entity.isPresent())
			return Optional.of(practitionerMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}

	@Override
	public Long count(List<SearchCriteria> searchCriteria) {
		GenericFilterSpecification<PractitionerEntity> specification = new GenericFilterSpecification<PractitionerEntity>();
        searchCriteria.stream().map(searchCriterion -> new SearchCriteria(searchCriterion.getKey(), searchCriterion.getValue(), searchCriterion.getOperation())).forEach(specification::add);
        return practitionerRepository.count(specification);
	}

	@Override
	public Optional<PractitionerDto> findByIdentifier(String identifier) {
		Optional<PractitionerEntity> entity = practitionerRepository.findByIdentifier(identifier);
		if(entity.isPresent())
			return Optional.of(practitionerMapper.toDto(entity.get()));
		else 
			return Optional.empty();
	}
	
	@Override
	public PractitionerDto save(PractitionerDto practitionerDto) throws EntityExistsException {
		Optional<PractitionerEntity> entity = practitionerRepository.findById(practitionerDto.getIdPractitioner());
		if (entity.isPresent()) 
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		
		PractitionerEntity practitioner = practitionerMapper.toEntity(practitionerDto);
		practitioner.setActive(true);
		practitioner = practitionerRepository.save(practitioner);
		return practitionerMapper.toDto(practitioner);
	}

	@Override
	public void bulkImport(List<PractitionerDto> practitioners) throws EntityExistsException{
		for(PractitionerDto practitionerDto: practitioners) {
			Optional<PractitionerEntity> entity = practitionerRepository.findById(practitionerDto.getIdPractitioner());
			if (entity.isPresent()) 
				throw new EntityExistsException(entity.get(), "Organization already exists!");
		
			PractitionerEntity practitioner = practitionerMapper.toEntity(practitionerDto);
			practitioner.setActive(true);
			practitioner = practitionerRepository.save(practitioner);
		}
	}
	
	@Override
	public PractitionerDto update(PractitionerDto practitionerDto) throws InvalidEntityException {
		Optional<PractitionerEntity> entity = practitionerRepository.findById(practitionerDto.getIdPractitioner());
		if (!entity.isPresent()) {
			throw new InvalidEntityException("Organization with id "+practitionerDto.getIdPractitioner() +" does not exist!");
		}
		PractitionerEntity practitioner = practitionerMapper.toEntity(practitionerDto);
		practitioner.setActive(true);
		practitioner = practitionerRepository.save(practitioner);
		return practitionerMapper.toDto(practitioner);
	}

	@Transactional
	@Override
	public void delete(Long id) throws InvalidEntityException, ExaminationStillRunningException  {
		Optional<PractitionerEntity> entity = practitionerRepository.findById(id);
		if (!entity.isPresent()) 
			throw new InvalidEntityException("Organization with id "+id +" does not exist!");

		GenericFilterSpecification<ExaminationEntity> specification = new GenericFilterSpecification<ExaminationEntity>();
		specification.add(new SearchCriteria("practitioners",id ,SearchOperation.IN_COLLECTION));
		boolean exists = examinationRepository.exists(specification);
		
		if(exists)
			throw new ExaminationStillRunningException("Examination is still running practitioner with id: "+id+" can not be deleted!");
		
		patientRepository.removeAllPractitionersWithIdfromPatients(entity.get());
		
		entity.get().setActive(false);
		practitionerRepository.save(entity.get());
	}
	
}
